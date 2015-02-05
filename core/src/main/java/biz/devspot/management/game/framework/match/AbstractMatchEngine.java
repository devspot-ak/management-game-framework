package biz.devspot.management.game.framework.match;

import biz.devspot.management.game.framework.model.AbstractFixture;
import biz.devspot.management.game.framework.model.FixtureResult;

public abstract class AbstractMatchEngine<F extends AbstractFixture, R extends FixtureResult> implements MatchEngine<F, R>{

    @Override
    public R play(F fixture) {
        return doPlay(fixture);
    }
    
    public abstract R doPlay(F fixture);

}
