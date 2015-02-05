package biz.devspot.management.game.framework.match;

import biz.devspot.management.game.framework.model.AbstractFixture;
import biz.devspot.management.game.framework.model.FixtureResult;
import biz.devspot.management.game.framework.util.Randomizer;

public class MockMatchEngine<F extends AbstractFixture> extends AbstractMatchEngine<F, FixtureResult> {

    @Override
    public FixtureResult doPlay(F fixture) {
        Randomizer randomizer = new Randomizer();
        int homeScore = randomizer.getRandomWeightedInteger(0, 6, 2);
        int awayScore = randomizer.getRandomWeightedInteger(0, 6, 1);
        int result = FixtureResult.DRAW;
        if(homeScore > awayScore){
            result = FixtureResult.HOME_WIN;
        }else if(awayScore > homeScore){
            result = FixtureResult.AWAY_WIN;
        }
        return new FixtureResult(result, homeScore, awayScore);
    }

}
