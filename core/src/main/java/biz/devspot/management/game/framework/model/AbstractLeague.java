package biz.devspot.management.game.framework.model;

import biz.devspot.management.game.framework.data.AbstractLeagueDO;
import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.entity.framework.core.query.QueryBuilder;
import java.util.List;

public abstract class AbstractLeague<DO extends AbstractLeagueDO, L extends AbstractDivision> extends MGFEntity<DO>{

    public AbstractLeague(String name) {
        data.setName(name);
    }

    public List<L> getDivisions() {
        return (List<L>) EntityManagerFactory.getManager().find(AbstractDivision.class, new QueryBuilder().where("league").isEqualTo(getId()).build());
    }
    
    public String getName(){
        return data.getName();
    }
    
    public void rollover(){
        for(L division: getDivisions()){
            division.rollover();
        }
    }
    
}
