package biz.devspot.management.game.framework.builder;

import biz.devspot.management.game.framework.model.AbstractCompetition;
import biz.devspot.management.game.framework.model.AbstractRound;

public abstract class AbstractRoundBuilder<B extends AbstractRoundBuilder, R extends AbstractRound> extends AbstractEntityBuilder<R>{
    
    protected int day;
    protected int week;
    protected AbstractCompetition competition;

    public AbstractRoundBuilder() {
    }
    
    public B setDay(int day){
        this.day = day;
        return (B) this;
    }
    
    public B setWeek(int week){
        this.week = week;
        return (B) this;
    }
    
    public B setCompetition(AbstractCompetition competition){
        this.competition = competition;
        return (B) this;
    }

}
