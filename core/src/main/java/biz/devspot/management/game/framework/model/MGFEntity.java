package biz.devspot.management.game.framework.model;

import biz.devspot.entity.framework.core.model.AbstractIdentifiedDataBackedObject;
import biz.devspot.entity.framework.core.model.DataObject;

public abstract class MGFEntity<DO extends DataObject> extends AbstractIdentifiedDataBackedObject<DO>{

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + getId();
    }

}
