package biz.devspot.management.game.framework.builder;

import biz.devspot.management.game.framework.model.AbstractManager;

public abstract class AbstractManagerBuilder<B extends AbstractManagerBuilder, M extends AbstractManager> extends AbstractPersonBuilder<B, M>{

    public AbstractManagerBuilder() {
        super(true);
    }

}
