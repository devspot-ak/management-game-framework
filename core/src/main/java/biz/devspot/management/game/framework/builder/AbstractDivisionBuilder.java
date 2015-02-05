package biz.devspot.management.game.framework.builder;

import biz.devspot.fixture.generator.Fixture;
import biz.devspot.fixture.generator.FixtureGenerator;
import biz.devspot.management.game.framework.model.AbstractClub;
import biz.devspot.management.game.framework.model.AbstractDivision;
import biz.devspot.management.game.framework.model.AbstractLeague;
import biz.devspot.management.game.framework.model.AbstractRound;
import biz.devspot.management.game.framework.model.AbstractStanding;
import biz.devspot.management.game.framework.util.RandomAccessList;
import biz.devspot.management.game.framework.util.SequentialAccessList;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDivisionBuilder<B extends AbstractDivisionBuilder, D extends AbstractDivision> extends AbstractEntityBuilder<D> {

    private final AbstractClubBuilder clubBuilder;
    private final AbstractStandingBuilder standingBuilder;
    private final FixtureGenerator<AbstractClub> fixtureGenerator = new FixtureGenerator<AbstractClub>();
    private final AbstractRoundBuilder roundBuilder;
    private final AbstractFixtureBuilder fixtureBuilder;

    protected int numberOfClubs = 0;
    protected int matchDay = 7;
    protected int tier = 1;
    protected String name;
    protected AbstractLeague league;

    public AbstractDivisionBuilder(AbstractClubBuilder clubBuilder, AbstractStandingBuilder standingBuilder, AbstractRoundBuilder roundBuilder, AbstractFixtureBuilder fixtureBuilder) {
        super(true);
        this.clubBuilder = clubBuilder;
        this.standingBuilder = standingBuilder;
        this.roundBuilder = roundBuilder;
        this.fixtureBuilder = fixtureBuilder;
    }

    public B setTier(int tier) {
        this.tier = tier;
        return (B) this;
    }

    public B setName(String name) {
        this.name = name;
        return (B) this;
    }

    public B setLeague(AbstractLeague league) {
        this.league = league;
        return (B) this;
    }

    public B setMatchDay(int matchDay) {
        this.matchDay = matchDay;
        return (B) this;
    }
    
    public B setClubNames(SequentialAccessList<String> clubNames) {
        clubBuilder.setClubNames(clubNames);
        return (B) this;
    }
    
    public B setNumberOfClubs(int numberOfClubs){
        this.numberOfClubs = numberOfClubs;
        return (B) this;
    }
    
    public B setNumberOfPlayersPerClub(int n){
        clubBuilder.setNumberOfPlayers(n);
        return (B) this;
    }
    
    public B setFirstNames(RandomAccessList<String> names) {
        clubBuilder.setFirstNames(names);
        return (B) this;
    }
    
    public B setSurnames(RandomAccessList<String> names) {
        clubBuilder.setSurnames(names);
        return (B) this;
    }

    @Override
    protected D create() {
        D division = doCreate();
        List<AbstractClub> clubs = new LinkedList<AbstractClub>();
        for (int j = 0; j < numberOfClubs; j++) {
            AbstractClub club = (AbstractClub) clubBuilder.setLeague(division).build();
            clubs.add(club);
            standingBuilder.setDivision(division).setClub(club).build();
        }
        List<List<Fixture<AbstractClub>>> generatedRounds = fixtureGenerator.getFixtures(clubs, true);
        int roundNumber = 0;
        for (List<Fixture<AbstractClub>> generatedRound : generatedRounds) {
            roundNumber = roundNumber + 1;
            AbstractRound round = (AbstractRound) roundBuilder.setWeek(roundNumber).setDay(matchDay).setCompetition(division).build();
            for (Fixture<AbstractClub> generatedFixture : generatedRound) {
                fixtureBuilder.setHomeClub(generatedFixture.getHomeTeam()).setAwayClub(generatedFixture.getAwayTeam()).setRound(round).setWeek(roundNumber).setDay(matchDay).build();
            }
        }
        return division;
    }

    protected abstract D doCreate();

}
