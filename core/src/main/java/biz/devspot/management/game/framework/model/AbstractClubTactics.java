package biz.devspot.management.game.framework.model;

import biz.devspot.management.game.framework.data.AbstractClubTacticsDO;
import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.entity.framework.core.query.QueryBuilder;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractClubTactics<DO extends AbstractClubTacticsDO, C extends AbstractClub, P extends AbstractPlayer, PT extends AbstractPlayerTactics> extends MGFEntity<DO> {

    public AbstractClubTactics() {
        
    }
    
    public C getClub(){
        return (C) EntityManagerFactory.getManager().findOne(AbstractClub.class, new QueryBuilder().where("tactics").isEqualTo(getId()).build());
    }
    
    public abstract List<PT> getPlayerTactics();
    
    public List<P> getPlayers(){
        List<P> players = new LinkedList<P>();
        for(PT pt: getPlayerTactics()){
            players.add((P) pt.getPlayer());
        }
        return players;
    }

}
