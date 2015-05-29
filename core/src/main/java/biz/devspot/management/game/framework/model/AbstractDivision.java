package biz.devspot.management.game.framework.model;

import biz.devspot.management.game.framework.data.AbstractDivisionDO;
import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.entity.framework.core.query.QueryBuilder;
import biz.devspot.management.game.framework.match.MatchEngine;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractDivision<DO extends AbstractDivisionDO, L extends AbstractLeague, S extends AbstractStanding, R extends AbstractRound, C extends AbstractClub> extends AbstractCompetition<DO, R> {

    public AbstractDivision(L league, int tier) {
        data.setLeague(league);
        data.setTier(tier);
    }

    public int getTier() {
        return data.getTier();
    }

    public L getLeague() {
        return (L) data.getLeague();
    }

    public List<S> getStandings() {
        return (List<S>) EntityManagerFactory.getManager().find(AbstractStanding.class, new QueryBuilder().where("division").isEqualTo(getId()).build());
    }

    public List<C> getClubs() {
        return (List<C>) EntityManagerFactory.getManager().find(AbstractClub.class, new QueryBuilder().where("league").isEqualTo(getId()).build());
    }

    public S getStanding(C club) {
        return (S) EntityManagerFactory.getManager().findOne(AbstractStanding.class, new QueryBuilder().where("division").isEqualTo(getId()).and("club").isEqualTo(club.getId()).build());
    }

    public String getName() {
        return getLeague().getName() + " Division " + getTier();
    }

    @Override
    public void playRound(MatchEngine matchEngine) {
        R round = getCurrentRound();
        super.playRound(matchEngine);
        for (Iterator it = round.getFixtures().iterator(); it.hasNext();) {
            AbstractFixture fixture = (AbstractFixture) it.next();
            AbstractClub homeClub = fixture.getHomeClub();
            AbstractStanding homeStanding = getStanding((C) homeClub);
            AbstractClub awayClub = fixture.getAwayClub();
            AbstractStanding awayStanding = getStanding((C) awayClub);
            homeStanding.applyFixtureResult(fixture);
            awayStanding.applyFixtureResult(fixture);
        }
    }

    public void rollover() {
        for (S standing : getStandings()) {
            standing.reset();
        }
        for (C club : getClubs()) {
            club.rollover();
        }
        for (R round : getRounds()) {
            round.reset();
        }
        data.setRoundIndex(0);
    }

}
