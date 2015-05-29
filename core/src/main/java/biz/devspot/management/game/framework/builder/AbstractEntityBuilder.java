package biz.devspot.management.game.framework.builder;

import biz.devspot.management.game.framework.model.MGFEntity;

public abstract class AbstractEntityBuilder<E extends MGFEntity> implements EntityBuilder<E>{

    public AbstractEntityBuilder() {
    }
    
    @Override
    public E build() {
        E entity = create();
        return entity;
    }
    
    protected abstract E create();

}
