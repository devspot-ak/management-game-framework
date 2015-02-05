package biz.devspot.management.game.framework.model;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.entity.framework.core.query.QueryBuilder;
import java.util.List;

public abstract class AbstractWorld<DO extends AbstractWorldDO, C extends AbstractContinent> extends MGFEntity<DO>{

    public AbstractWorld(int seasonLength){
        data.setSeasonLength(seasonLength);
    }
    
    public int getSeasonLength(){
        return data.getSeasonLength();
    }
    
    public List<C> getContinents(){
        return (List<C>) EntityManagerFactory.getManager().find(AbstractContinent.class, new QueryBuilder().where("world").isEqualTo(getId()).build());
    }
    
    public void rollover(){
        for(C continent: getContinents()){
            continent.rollover();
        }
    }
    
}
