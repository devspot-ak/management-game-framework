package biz.devspot.management.game.framework.data;

import biz.devspot.management.game.framework.model.AbstractPlayer;

public class TransferOfferMessageDO extends MessageDO{

    private AbstractPlayer player;

    public AbstractPlayer getPlayer() {
        return player;
    }

    public void setPlayer(AbstractPlayer player) {
        this.player = player;
    }
    
}
