package biz.devspot.management.game.framework.model;

import biz.devspot.management.game.framework.data.TransferOfferMessageDO;
import biz.devspot.entity.framework.core.annotation.TypeAlias;

@TypeAlias(Message.class)
public class TransferOfferMessage extends Message<TransferOfferMessageDO>{

    public TransferOfferMessage(AbstractTransferOffer offer) {
        super("Offer for " + offer.getPlayer().getName(), offer.getClub().getName() + " have made an offer of " + offer.getAmount() + " for " + offer.getPlayer().getName());
        data.setPlayer(offer.getPlayer());
    }
    
    public AbstractPlayer getPlayer(){
        return data.getPlayer();
    }

    @Override
    protected TransferOfferMessageDO createDataObject() {
        return new TransferOfferMessageDO();
    }

}
