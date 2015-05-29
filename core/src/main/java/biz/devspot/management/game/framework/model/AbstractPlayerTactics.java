package biz.devspot.management.game.framework.model;

import biz.devspot.management.game.framework.data.AbstractPlayerTacticsDO;

public abstract class AbstractPlayerTactics<DO extends AbstractPlayerTacticsDO, PO extends AbstractPlayerPosition, PL extends AbstractPlayer> extends MGFEntity<DO>{

    public AbstractPlayerTactics(PO position, PL player){
        data.setPosition(position);
        data.setPlayer(player);
    }
    
    public PO getPosition(){
        return (PO) data.getPosition();
    }
    
    public PL getPlayer(){
        return (PL) data.getPlayer();
    }
    
    public void setPlayer(PL player){
        data.setPlayer(player);
    }
    
}
