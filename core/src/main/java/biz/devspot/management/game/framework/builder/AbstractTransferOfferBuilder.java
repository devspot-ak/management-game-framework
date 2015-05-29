package biz.devspot.management.game.framework.builder;

import biz.devspot.management.game.framework.model.AbstractClub;
import biz.devspot.management.game.framework.model.AbstractPlayer;
import biz.devspot.management.game.framework.model.AbstractTransferOffer;

public abstract class AbstractTransferOfferBuilder<B extends AbstractTransferOfferBuilder, TO extends AbstractTransferOffer> extends AbstractEntityBuilder<TO>{

    protected AbstractPlayer player;
    protected AbstractClub club;
    protected long price;
    
    public AbstractTransferOfferBuilder() {

    }
    
    public B setPlayer(AbstractPlayer player){
        this.player = player;
        return (B) this;
    }
    
    public B setClub(AbstractClub club){
        this.club = club;
        return (B) this;
    }
    
    public B setPrice(long price){
        this.price = price;
        return (B) this;
    }

}
