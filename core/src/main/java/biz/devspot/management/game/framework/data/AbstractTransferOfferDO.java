package biz.devspot.management.game.framework.data;

import biz.devspot.entity.framework.core.model.AbstractDataObject;
import biz.devspot.management.game.framework.model.AbstractClub;
import biz.devspot.management.game.framework.model.AbstractPlayer;

public class AbstractTransferOfferDO<P extends AbstractPlayer, C extends AbstractClub> extends AbstractDataObject {

    private P player;
    private C club;
    private long amount;

    public P getPlayer() {
        return player;
    }

    public void setPlayer(P player) {
        this.player = player;
    }

    public C getClub() {
        return club;
    }

    public void setClub(C club) {
        this.club = club;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

}
