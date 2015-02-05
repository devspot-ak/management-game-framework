package biz.devspot.management.game.framework.builder;

import biz.devspot.management.game.framework.model.MGFEntity;

public interface EntityBuilder<E extends MGFEntity> {

    public E build();
}
