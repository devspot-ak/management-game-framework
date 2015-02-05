package biz.devspot.management.game.framework.match;

import biz.devspot.management.game.framework.model.AbstractFixture;
import biz.devspot.management.game.framework.model.FixtureResult;

public interface MatchEngine<F extends AbstractFixture, R extends FixtureResult> {

    public R play(F fixture);
    
}
