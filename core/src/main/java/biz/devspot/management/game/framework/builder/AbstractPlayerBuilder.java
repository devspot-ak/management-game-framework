package biz.devspot.management.game.framework.builder;

import biz.devspot.management.game.framework.model.AbstractClub;
import biz.devspot.management.game.framework.model.AbstractPlayer;

public abstract class AbstractPlayerBuilder<B extends AbstractPlayerBuilder, P extends AbstractPlayer> extends AbstractPersonBuilder<B, P>{

    protected AbstractClub club;

    public AbstractPlayerBuilder() {
    }
    
    public B setClub(AbstractClub club){
        this.club = club;
        return (B) this;
    }
    
}
