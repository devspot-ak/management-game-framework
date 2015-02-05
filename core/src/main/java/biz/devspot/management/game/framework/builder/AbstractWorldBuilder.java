package biz.devspot.management.game.framework.builder;

import biz.devspot.management.game.framework.model.AbstractContinent;
import biz.devspot.management.game.framework.model.AbstractWorld;
import biz.devspot.management.game.framework.util.RandomAccessList;
import biz.devspot.management.game.framework.util.SequentialAccessList;

public abstract class AbstractWorldBuilder<B extends AbstractWorldBuilder, W extends AbstractWorld> extends AbstractEntityBuilder<W> {

    private final AbstractContinentBuilder continentBuilder;

    protected int seasonLength = 1;
    protected int continents = 1;

    public AbstractWorldBuilder(AbstractContinentBuilder continentBuilder) {
        super(true);
        this.continentBuilder = continentBuilder;
    }
    
    public B setSeasonLength(int weeks){
        this.seasonLength = weeks;
        return (B) this;
    }

    public B setNumberOfContinents(int continents) {
        this.continents = continents;
        return (B) this;
    }
    
    public B setContinentNames(SequentialAccessList<String> continentNames){
        continentBuilder.setNames(continentNames);
        return (B) this;
    }
    
    public B setNumberOfCountriesPerContinent(int countries) {
        continentBuilder.setNumberOfCountries(countries);
        return (B) this;
    }

    public B setCountryNames(SequentialAccessList<String> countryNames) {
        continentBuilder.setCountryNames(countryNames);
        return (B) this;
    }

    public B setNumberOfDivisions(int divisions) {
        continentBuilder.setNumberOfDivisions(divisions);
        return (B) this;
    }

    public B setNumberOfClubsPerDivision(int clubs) {
        continentBuilder.setNumberOfClubsPerDivision(clubs);
        return (B) this;
    }

    public B setLeagueMatchDay(int matchDay) {
        continentBuilder.setLeagueMatchDay(matchDay);
        return (B) this;
    }

    public B setClubNames(SequentialAccessList<String> clubNames) {
        continentBuilder.setClubNames(clubNames);
        return (B) this;
    }
    
    public B setNumberOfPlayersPerClub(int n){
        continentBuilder.setNumberOfPlayersPerClub(n);
        return (B) this;
    }
    
    public B setFirstNames(RandomAccessList<String> names) {
        continentBuilder.setFirstNames(names);
        return (B) this;
    }
    
    public B setSurnames(RandomAccessList<String> names) {
        continentBuilder.setSurnames(names);
        return (B) this;
    }

    @Override
    protected W create() {
        W world = doCreate();
        continentBuilder.setWorld(world);
        for(int i=0; i<continents; i++){
            AbstractContinent continent = (AbstractContinent) continentBuilder.build();
        }
        return world;
    }

    protected abstract W doCreate();
    
}
