package biz.devspot.management.game.framework.builder;

import biz.devspot.management.game.framework.model.AbstractClub;
import biz.devspot.management.game.framework.model.AbstractDivision;
import biz.devspot.management.game.framework.model.AbstractStanding;

public abstract class AbstractStandingBuilder<B extends AbstractStandingBuilder, S extends AbstractStanding> extends AbstractEntityBuilder<S> {

    protected AbstractClub club;
    protected AbstractDivision division;

    public AbstractStandingBuilder() {
        super(true);
    }

    public B setClub(AbstractClub club) {
        this.club = club;
        return (B) this;
    }
    
    public B setDivision(AbstractDivision division){
        this.division = division;
        return (B) this;
    }

}
