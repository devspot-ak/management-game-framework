package biz.devspot.management.game.framework.model;

import biz.devspot.management.game.framework.data.AbstractTransferOfferDO;

public abstract class AbstractTransferOffer<DO extends AbstractTransferOfferDO, P extends AbstractPlayer, C extends AbstractClub> extends MGFEntity<DO> {

    public AbstractTransferOffer(P player, C club, long amount) {
        data.setPlayer(player);
        data.setClub(club);
        data.setAmount(amount);
    }
    
    public P getPlayer(){
        return (P) data.getPlayer();
    }
    
    public C getClub(){
        return (C) data.getClub();
    }
    
    public long getAmount(){
        return data.getAmount();
    }

}
