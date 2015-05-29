package biz.devspot.management.game.framework.model;

import biz.devspot.management.game.framework.data.AbstractFixtureDO;
import biz.devspot.management.game.framework.match.MatchEngine;
import biz.devspot.management.game.framework.model.state.FixtureStates;

public abstract class AbstractFixture<DO extends AbstractFixtureDO, R extends AbstractRound, CL extends AbstractClub, CO extends AbstractCompetition> extends MGFEntity<DO>{

    public AbstractFixture(R round, CL homeClub, CL awayClub) {
        data.setRound(round);
        data.setWeek(round.getWeek());
        data.setDay(round.getDay());
        data.setHomeClub(homeClub);
        data.setAwayClub(awayClub);
    }

    public String getStatus() {
        return data.getStatus();
    }

    public CL getHomeClub() {
        return (CL) data.getHomeClub();
    }

    public CL getAwayClub() {
        return (CL) data.getAwayClub();
    }

    public R getRound() {
        return (R) data.getRound();
    }

    public CO getCompetition() {
        return (CO) getRound().getCompetition();
    }

    public int getWeek() {
        return data.getWeek();
    }

    public int getDay() {
        return data.getDay();
    }
    
    public boolean isPending(){
        return getStatus().equals(FixtureStates.PENDING);
    }
    
    public boolean isComplete(){
        return getStatus().equals(FixtureStates.COMPLETE);
    }

    public int getResult() {
        return data.getResult();
    }

    public int getHomeScore() {
        return data.getHomeScore();
    }

    public int getAwayScore() {
        return data.getAwayScore();
    }
    
    public void play(MatchEngine matchEngine){
        FixtureResult result = matchEngine.play(this);
        data.setResult(result.getResult());
        data.setHomeScore(result.getHomeScore());
        data.setAwayScore(result.getAwayScore());
        data.setStatus(FixtureStates.COMPLETE);
    }
    
    public void reset(){
        data.setResult(FixtureResult.DRAW);
        data.setHomeScore(0);
        data.setAwayScore(0);
        data.setStatus(FixtureStates.PENDING);
    }
    
}
