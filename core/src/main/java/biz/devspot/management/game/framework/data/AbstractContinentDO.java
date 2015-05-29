package biz.devspot.management.game.framework.data;

import biz.devspot.entity.framework.core.model.AbstractDataObject;
import biz.devspot.management.game.framework.model.AbstractWorld;

public class AbstractContinentDO<W extends AbstractWorld> extends AbstractDataObject {

    private String name;
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
