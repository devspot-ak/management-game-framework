package biz.devspot.management.game.framework.model;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.entity.framework.core.query.QueryBuilder;
import java.util.List;

public abstract class AbstractContinent<DO extends AbstractContinentDO, W extends AbstractWorld, C extends AbstractCountry> extends MGFEntity<DO> {

    public AbstractContinent(W world, String name) {
        data.setWorld(world);
        data.setName(name);
    }

    public String getName() {
        return data.getName();
    }

    public W getWorld() {
        return (W) data.getWorld();
    }

    public List<C> getCountries() {
        return (List<C>) EntityManagerFactory.getManager().find(AbstractCountry.class, new QueryBuilder().where("continent").isEqualTo(getId()).build());
    }
    
    public void rollover(){
        for(C country: getCountries()){
            country.rollover();
        }
    }

}
