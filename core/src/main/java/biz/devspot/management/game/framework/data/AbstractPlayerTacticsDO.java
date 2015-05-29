package biz.devspot.management.game.framework.data;

import biz.devspot.entity.framework.core.model.AbstractDataObject;
import biz.devspot.management.game.framework.model.AbstractPlayer;
import biz.devspot.management.game.framework.model.AbstractPlayerPosition;

public abstract class AbstractPlayerTacticsDO<PO extends AbstractPlayerPosition, PL extends AbstractPlayer> extends AbstractDataObject{

    private PO position;
    private PL player;

    public AbstractPlayerTacticsDO() {
    }

    public PO getPosition() {
        return position;
    }

    public void setPosition(PO position) {
        this.position = position;
    }

    public PL getPlayer() {
        return player;
    }

    public void setPlayer(PL player) {
        this.player = player;
    }
    
}
