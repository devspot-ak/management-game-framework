package biz.devspot.management.game.framework.builder;

import biz.devspot.management.game.framework.model.AbstractContinent;
import biz.devspot.management.game.framework.model.AbstractWorld;
import biz.devspot.management.game.framework.util.RandomAccessList;
import biz.devspot.management.game.framework.util.SequentialAccessList;

public abstract class AbstractContinentBuilder<B extends AbstractContinentBuilder, C extends AbstractContinent> extends AbstractEntityBuilder<C> {

    private final AbstractCountryBuilder countryBuilder;

    private SequentialAccessList<String> names;

    protected AbstractWorld world;
    protected String name;
    protected int countries = 1;

    public AbstractContinentBuilder(AbstractCountryBuilder countryBuilder) {
        super(true);
        this.countryBuilder = countryBuilder;
    }

    public B setWorld(AbstractWorld world) {
        this.world = world;
        return (B) this;
    }

    public B setName(String name) {
        this.name = name;
        return (B) this;
    }

    public B setNames(SequentialAccessList<String> names) {
        this.names = names;
        return (B) this;
    }

    public B setNumberOfCountries(int countries) {
        this.countries = countries;
        return (B) this;
    }

    public B setCountryNames(SequentialAccessList<String> countryNames) {
        countryBuilder.setCountryNames(countryNames);
        return (B) this;
    }

    public B setNumberOfDivisions(int divisions) {
        countryBuilder.setNumberOfDivisions(divisions);
        return (B) this;
    }

    public B setNumberOfClubsPerDivision(int clubs) {
        countryBuilder.setNumberOfClubsPerDivision(clubs);
        return (B) this;
    }

    public B setLeagueMatchDay(int matchDay) {
        countryBuilder.setLeagueMatchDay(matchDay);
        return (B) this;
    }

    public B setClubNames(SequentialAccessList<String> clubNames) {
        countryBuilder.setClubNames(clubNames);
        return (B) this;
    }

    public B setNumberOfPlayersPerClub(int n) {
        countryBuilder.setNumberOfPlayersPerClub(n);
        return (B) this;
    }

    public B setFirstNames(RandomAccessList<String> names) {
        countryBuilder.setFirstNames(names);
        return (B) this;
    }

    public B setSurnames(RandomAccessList<String> names) {
        countryBuilder.setSurnames(names);
        return (B) this;
    }
    
    protected String getName(){
        if(name == null){
            return names.get();
        }
        return name;
    }

    @Override
    protected C create(){
        C continent = doCreate();
        countryBuilder.setContinent(continent);
        for (int i = 0; i < countries; i++) {
            countryBuilder.build();
        }
        return continent;
    }
    
    protected abstract C doCreate();

}
