package biz.devspot.management.game.framework.model;

import biz.devspot.management.game.framework.data.AbstractCompetitionDO;
import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.entity.framework.core.query.QueryBuilder;
import biz.devspot.management.game.framework.match.MatchEngine;
import java.util.List;

public abstract class AbstractCompetition<DO extends AbstractCompetitionDO, R extends AbstractRound> extends MGFEntity<DO> {
    
    public AbstractCompetition() {
    }
    
    public String getName() {
        return data.getName();
    }
    
    public List<R> getRounds() {
        return (List<R>) EntityManagerFactory.getManager().find(AbstractRound.class, new QueryBuilder().where("competition").isEqualTo(getId()).sortBy("week").ascending().build());
    }
    
    public R getCurrentRound() {
        return getRounds().get(data.getRoundIndex());
    }
    
    public R getPreviousRound() {
        int prevRound = data.getRoundIndex() - 1;
        if (prevRound < 0) {
            return null;
        } else {
            return getRounds().get(prevRound);
        }
    }
    
    public void playRound(MatchEngine matchEngine){
        getCurrentRound().play(matchEngine);
        data.setRoundIndex(data.getRoundIndex() + 1);
    }
    
}
