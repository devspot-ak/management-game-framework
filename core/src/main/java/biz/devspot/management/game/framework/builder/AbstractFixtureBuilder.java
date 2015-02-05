package biz.devspot.management.game.framework.builder;

import biz.devspot.management.game.framework.model.AbstractClub;
import biz.devspot.management.game.framework.model.AbstractCompetition;
import biz.devspot.management.game.framework.model.AbstractFixture;
import biz.devspot.management.game.framework.model.AbstractRound;

public abstract class AbstractFixtureBuilder<B extends AbstractFixtureBuilder, F extends AbstractFixture> extends AbstractEntityBuilder<F> {

    protected AbstractRound round;
    protected AbstractClub homeClub;
    protected AbstractClub awayClub;
    protected int week;
    protected int day;

    public AbstractFixtureBuilder() {
        super(true);
    }

    public B setRound(AbstractRound round) {
        this.round = round;
        return (B) this;
    }

    public B setHomeClub(AbstractClub club) {
        this.homeClub = club;
        return (B) this;
    }

    public B setAwayClub(AbstractClub club) {
        this.awayClub = club;
        return (B) this;
    }

    public B setWeek(int week) {
        this.week = week;
        return (B) this;
    }

    public B setDay(int day) {
        this.day = day;
        return (B) this;
    }

}
