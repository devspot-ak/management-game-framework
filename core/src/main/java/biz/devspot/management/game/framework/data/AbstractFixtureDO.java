package biz.devspot.management.game.framework.data;

import biz.devspot.entity.framework.core.model.AbstractDataObject;
import biz.devspot.management.game.framework.model.AbstractClub;
import biz.devspot.management.game.framework.model.AbstractCompetition;
import biz.devspot.management.game.framework.model.AbstractRound;
import biz.devspot.management.game.framework.model.state.FixtureStates;

public class AbstractFixtureDO<R extends AbstractRound, CL extends AbstractClub, CO extends AbstractCompetition> extends AbstractDataObject {

    private String status = FixtureStates.PENDING;
    private CL homeClub;
    private CL awayClub;
    private R round;
    private int week;
    private int day;
    private int homeScore = 0;
    private int awayScore = 0;
    private int result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CL getHomeClub() {
        return homeClub;
    }

    public void setHomeClub(CL homeClub) {
        this.homeClub = homeClub;
    }

    public CL getAwayClub() {
        return awayClub;
    }

    public void setAwayClub(CL awayClub) {
        this.awayClub = awayClub;
    }

    public R getRound() {
        return round;
    }

    public void setRound(R round) {
        this.round = round;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
