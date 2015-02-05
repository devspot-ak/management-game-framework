package biz.devspot.management.game.framework.model;

public abstract class AbstractStanding<DO extends AbstractStandingDO, D extends AbstractDivision, C extends AbstractClub, F extends AbstractFixture> extends MGFEntity<DO> {
    
    public AbstractStanding(D division, C club) {
        data.setDivision(division);
        data.setClub(club);
    }
    
    public D getDivision(){
        return (D) data.getDivision();
    }
    
    public C getClub() {
        return (C) data.getClub();
    }
    
    public int getPlayed() {
        return data.getPlayed();
    }
    
    public int getWon() {
        return data.getWon();
    }
    
    public int getDrawn() {
        return data.getDrawn();
    }
    
    public int getLost() {
        return data.getLost();
    }
    
    public int getScoreFor() {
        return data.getScoreFor();
    }
    
    public int getScoreAgainst() {
        return data.getScoreAgainst();
    }
    public int getPoints() {
        return data.getPoints();
    }
    
    public void applyFixtureResult(F fixture){
        boolean isHomeTeam;
        if(data.getClub().equals(fixture.getHomeClub())){
            isHomeTeam = true;
        }else if(data.getClub().equals(fixture.getAwayClub())){
            isHomeTeam = false;
        }else{
            return;
        }
        data.setPlayed(data.getPlayed()+1);
        if(isHomeTeam){
            data.setScoreFor(data.getScoreFor() + fixture.getHomeScore());
            data.setScoreAgainst(data.getScoreAgainst() + fixture.getAwayScore());
            if(fixture.getResult() == FixtureResult.HOME_WIN){
                data.setPoints(data.getPoints()+3);
                data.setWon(data.getWon()+1);
            }else if(fixture.getResult() == FixtureResult.DRAW){
                data.setPoints(data.getPoints()+1);
                data.setDrawn(data.getDrawn()+1);
            }else{
                data.setLost(data.getLost()+1);
            }
        }else{
            data.setScoreFor(data.getScoreFor() + fixture.getAwayScore());
            data.setScoreAgainst(data.getScoreAgainst() + fixture.getHomeScore());
            if(fixture.getResult() == FixtureResult.AWAY_WIN){
                data.setPoints(data.getPoints()+3);
                data.setWon(data.getWon()+1);
            }else if(fixture.getResult() == FixtureResult.DRAW){
                data.setPoints(data.getPoints()+1);
                data.setDrawn(data.getDrawn()+1);
            }else{
                data.setLost(data.getLost()+1);
            }
        }
    }
    
    public void reset(){
        data.setPlayed(0);
        data.setWon(0);
        data.setDrawn(0);
        data.setLost(0);
        data.setScoreFor(0);
        data.setScoreAgainst(0);
        data.setPoints(0);
    }
    
}
