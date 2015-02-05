package biz.devspot.management.game.framework.model;

import biz.devspot.entity.framework.core.model.AbstractDataObject;

public class AbstractLeagueDO extends AbstractDataObject{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
