package biz.devspot.management.game.framework.model;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.entity.framework.core.query.QueryBuilder;
import biz.devspot.management.game.framework.match.MatchEngine;
import biz.devspot.management.game.framework.model.state.RoundStates;
import java.util.List;

public abstract class AbstractRound<DO extends AbstractRoundDO, C extends AbstractCompetition, F extends AbstractFixture> extends MGFEntity<DO>{

    public AbstractRound(C competition, int week, int day) {
        data.setCompetition(competition);
        data.setWeek(week);
        data.setDay(day);
    }

    public int getWeek() {
        return data.getWeek();
    }

    public int getDay() {
        return data.getDay();
    }

    public C getCompetition() {
        return (C) data.getCompetition();
    }
    
    public List<F> getFixtures(){
        return (List<F>) EntityManagerFactory.getManager().find(AbstractFixture.class, new QueryBuilder().where("round").isEqualTo(getId()).build());
    }
    
    public void play(MatchEngine matchEngine){
        for(F fixture: getFixtures()){
            fixture.play(matchEngine);
        }
        data.setStatus(RoundStates.COMPLETE);
    }
    
    public void reset(){
        data.setStatus(RoundStates.PENDING);
        for(F fixture: getFixtures()){
            fixture.reset();
        }
    }
    
}
