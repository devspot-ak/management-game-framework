package biz.devspot.management.game.framework.model;

import biz.devspot.entity.framework.core.annotation.AssociatedEntity;
import biz.devspot.entity.framework.core.model.AbstractDataObject;

public class AbstractContinentDO<W extends AbstractWorld> extends AbstractDataObject {

    private String name;
    @AssociatedEntity
    private W world;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public W getWorld() {
        return world;
    }

    public void setWorld(W world) {
        this.world = world;
    }
}
