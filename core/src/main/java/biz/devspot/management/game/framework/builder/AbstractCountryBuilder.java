package biz.devspot.management.game.framework.builder;

import biz.devspot.management.game.framework.model.AbstractContinent;
import biz.devspot.management.game.framework.model.AbstractCountry;
import biz.devspot.management.game.framework.model.AbstractLeague;
import biz.devspot.management.game.framework.util.RandomAccessList;
import biz.devspot.management.game.framework.util.SequentialAccessList;

public abstract class AbstractCountryBuilder<B extends AbstractCountryBuilder, C extends AbstractCountry> extends AbstractEntityBuilder<C>{

    private final AbstractLeagueBuilder leagueBuilder;
    
    private SequentialAccessList<String> countryNames;
    
    protected String name;
    protected AbstractContinent continent;
    
    public AbstractCountryBuilder(AbstractLeagueBuilder leagueBuilder) {
        this.leagueBuilder = leagueBuilder;
    }
    
    public B setName(String name){
        this.name = name;
        return (B) this;
    }
    
    public B setCountryNames(SequentialAccessList<String> countryNames){
        this.countryNames = countryNames;
        return (B) this;
    }
    
    public B setContinent(AbstractContinent continent){
        this.continent = continent;
        return (B) this;
    }
    
    public B setNumberOfDivisions(int divisions){
        leagueBuilder.setNumberOfDivisions(divisions);
        return (B) this;
    }
    
    public B setNumberOfClubsPerDivision(int clubs){
        leagueBuilder.setNumberOfClubsPerDivision(clubs);
        return (B) this;
    }
    
    public B setLeagueMatchDay(int matchDay){
        leagueBuilder.setMatchDay(matchDay);
        return (B) this;
    }
    
    public B setClubNames(SequentialAccessList<String> clubNames) {
        leagueBuilder.setClubNames(clubNames);
        return (B) this;
    }
    
    public B setNumberOfPlayersPerClub(int n){
        leagueBuilder.setNumberOfPlayersPerClub(n);
        return (B) this;
    }
    
    public B setFirstNames(RandomAccessList<String> names) {
        leagueBuilder.setFirstNames(names);
        return (B) this;
    }
    
    public B setSurnames(RandomAccessList<String> names) {
        leagueBuilder.setSurnames(names);
        return (B) this;
    }
    
    protected String getName(){
        if(name == null){
            return countryNames.get();
        }
        return name;
    }

    @Override
    protected C create() {
        AbstractLeague nationalLeague = (AbstractLeague) leagueBuilder.setName("National League").build();
        return doCreate(nationalLeague);
    }
    
    protected abstract C doCreate(AbstractLeague nationalLeague);
    
}
