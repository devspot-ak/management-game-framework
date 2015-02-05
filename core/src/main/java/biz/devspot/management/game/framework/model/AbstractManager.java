package biz.devspot.management.game.framework.model;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.entity.framework.core.query.QueryBuilder;

public abstract class AbstractManager<DO extends AbstractPersonDO, C extends AbstractClub> extends AbstractPerson<DO>{

    public AbstractManager(String firstName, String surname) {
        super(firstName, surname);
    }

    public C getClub(){
        return (C) EntityManagerFactory.getManager().findOne(AbstractClub.class, new QueryBuilder().where("manager").isEqualTo(getId()).build());
    }
    
}
