package biz.devspot.management.game.framework.model;

import biz.devspot.entity.framework.core.model.AbstractDataObject;

public class AbstractWorldDO extends AbstractDataObject{

    private int seasonLength;

    public int getSeasonLength() {
        return seasonLength;
    }

    public void setSeasonLength(int seasonLength) {
        this.seasonLength = seasonLength;
    }
    
}
