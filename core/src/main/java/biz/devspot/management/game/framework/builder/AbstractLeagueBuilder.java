package biz.devspot.management.game.framework.builder;

import biz.devspot.management.game.framework.model.AbstractDivision;
import biz.devspot.management.game.framework.model.AbstractLeague;
import biz.devspot.management.game.framework.util.RandomAccessList;
import biz.devspot.management.game.framework.util.SequentialAccessList;

public abstract class AbstractLeagueBuilder<B extends AbstractLeagueBuilder, L extends AbstractLeague> extends AbstractEntityBuilder<L> {

    private final AbstractDivisionBuilder divisionBuilder;
    
    protected String name;
    protected int numberOfDivisions = 1;
    
    public AbstractLeagueBuilder(AbstractClubBuilder clubBuilder, AbstractDivisionBuilder divisionBuilder) {
        super(true);
        this.divisionBuilder = divisionBuilder;
    }
    
    public B setName(String name){
        this.name = name;
        return (B) this;
    }
    
    public B setNumberOfDivisions(int divisions) {
        this.numberOfDivisions = divisions;
        return (B) this;
    }

    public B setNumberOfClubsPerDivision(int clubs) {
        divisionBuilder.setNumberOfClubs(clubs);
        return (B) this;
    }
    
    public B setMatchDay(int matchDay){
        divisionBuilder.setMatchDay(matchDay);
        return (B) this;
    }
    
    public B setClubNames(SequentialAccessList<String> clubNames) {
        divisionBuilder.setClubNames(clubNames);
        return (B) this;
    }
    
    public B setNumberOfPlayersPerClub(int n){
        divisionBuilder.setNumberOfPlayersPerClub(n);
        return (B) this;
    }
    
    public B setFirstNames(RandomAccessList<String> names) {
        divisionBuilder.setFirstNames(names);
        return (B) this;
    }
    
    public B setSurnames(RandomAccessList<String> names) {
        divisionBuilder.setSurnames(names);
        return (B) this;
    }

    @Override
    protected L create(){
        L league = doCreate();
        divisionBuilder.setLeague(league);
        for (int i = 0; i < numberOfDivisions; i++) {
            AbstractDivision division = (AbstractDivision) divisionBuilder.setTier(i+1).setName("Division " + (i+1)).build();
        }
        return league;
    }
    
    protected abstract L doCreate();

}
