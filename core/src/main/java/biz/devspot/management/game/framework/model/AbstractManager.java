package biz.devspot.management.game.framework.model;

import biz.devspot.management.game.framework.data.AbstractPersonDO;
import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.entity.framework.core.query.QueryBuilder;
import java.util.List;

public abstract class AbstractManager<DO extends AbstractPersonDO, C extends AbstractClub> extends AbstractPerson<DO>{

    public AbstractManager(String firstName, String surname) {
        super(firstName, surname);
    }

    public C getClub(){
        return (C) EntityManagerFactory.getManager().findOne(AbstractClub.class, new QueryBuilder().where("manager").isEqualTo(getId()).build());
    }
    
    public List<Message> getUnreadMessages(){
        return (List<Message>) EntityManagerFactory.getManager().find(Message.class, new QueryBuilder().where("read").isEqualTo(false).sortBy("time").descending().build());
    }
    
    public List<Message> getReadMessages(int limit){
        return (List<Message>) EntityManagerFactory.getManager().find(Message.class, new QueryBuilder().where("read").isEqualTo(true).sortBy("time").descending().limit(limit).build());
    }
    
    public List<Message> getMessages(int limit){
        return (List<Message>) EntityManagerFactory.getManager().find(Message.class, new QueryBuilder().sortBy("time").descending().limit(limit).build());
    }
    
}
