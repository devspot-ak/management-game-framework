package biz.devspot.management.game.framework.model;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.entity.framework.core.query.QueryBuilder;
import java.util.List;

public abstract class AbstractClub<DO extends AbstractClubDO, P extends AbstractPlayer, L extends AbstractDivision, M extends AbstractManager, F extends AbstractFixture> extends MGFEntity<DO>{

    public AbstractClub(String name, L league) {
        data.setName(name);
        data.setLeague(league);
    }

    public String getName() {
        return data.getName();
    }
    
    public List<P> getPlayers(){
        return (List<P>) EntityManagerFactory.getManager().find(AbstractPlayer.class, new QueryBuilder().where("club").isEqualTo(getId()).build());
    }

    public L getLeague() {
        return (L) data.getLeague();
    }

    public M getManager() {
        return (M) data.getManager();
    }
    
    public List<F> getFixtures(){
        return (List<F>) EntityManagerFactory.getManager().find(AbstractFixture.class, new QueryBuilder().where("homeClub").isEqualTo(getId()).or("awayClub").isEqualTo(getId()).build());
    }
    
    public void appointManager(M manager){
        data.setManager(manager);
    }
    
    public void rollover(){
        for(P player: getPlayers()){
            player.rollover();
        }
    }
    
}
