package biz.devspot.management.game.framework.builder;

import biz.devspot.management.game.framework.model.AbstractClub;
import biz.devspot.management.game.framework.model.AbstractDivision;
import biz.devspot.management.game.framework.util.RandomAccessList;
import biz.devspot.management.game.framework.util.SequentialAccessList;

public abstract class AbstractClubBuilder<B extends AbstractClubBuilder, CL extends AbstractClub> extends AbstractEntityBuilder<CL>{

    protected final AbstractPlayerBuilder playerBuilder;
    
    protected SequentialAccessList<String> clubNames;
    
    protected String name;
    protected AbstractDivision league;
    protected int numberOfPlayers = 5;

    public AbstractClubBuilder(AbstractPlayerBuilder playerBuilder) {
        super(true);
        this.playerBuilder = playerBuilder;
    }
    
    public B setName(String name){
        this.name = name;
        return (B) this;
    }
    
    public B setClubNames(SequentialAccessList<String> clubNames){
        this.clubNames = clubNames;
        return (B) this;
    }
    
    public B setLeague(AbstractDivision league){
        this.league = league;
        return (B) this;
    }
    
    public B setNumberOfPlayers(int players){
        this.numberOfPlayers = players;
        return (B) this;
    }
    
    public B setFirstNames(RandomAccessList<String> names) {
        playerBuilder.setFirstNames(names);
        return (B) this;
    }
    
    public B setSurnames(RandomAccessList<String> names) {
        playerBuilder.setSurnames(names);
        return (B) this;
    }
    
    protected String getName(){
        if(name == null){
            return clubNames.get();
        }
        return name;
    }

    @Override
    protected CL create(){
        CL club = doCreate();
        for(int i=0; i<numberOfPlayers; i++){
            playerBuilder.setClub(club).build();
        }
        return club;
    }
    
    protected abstract CL doCreate();
    
}
