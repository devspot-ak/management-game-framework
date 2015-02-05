package biz.devspot.management.game.framework.builder;

import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.management.game.framework.model.MGFEntity;

public abstract class AbstractEntityBuilder<E extends MGFEntity> implements EntityBuilder<E>{

    private boolean managed;

    public AbstractEntityBuilder(boolean managed) {
        this.managed = managed;
    }
    
    @Override
    public E build() {
        E entity = create();
        if(managed){
            EntityManagerFactory.getManager().manage(entity);
        }
        return entity;
    }
    
    protected abstract E create();

}
