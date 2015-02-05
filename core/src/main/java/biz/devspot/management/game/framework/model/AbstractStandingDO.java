package biz.devspot.management.game.framework.model;

import biz.devspot.entity.framework.core.annotation.AssociatedEntity;
import biz.devspot.entity.framework.core.model.AbstractDataObject;

public class AbstractStandingDO<D extends AbstractDivision, C extends AbstractClub> extends AbstractDataObject {

    @AssociatedEntity
    private C club;
    @AssociatedEntity
    private D division;
    private int played = 0;
    private int won = 0;
    private int drawn = 0;
    private int lost = 0;
    private int points = 0;
    private int scoreFor = 0;
    private int scoreAgainst = 0;

    public D getDivision() {
        return division;
    }

    public void setDivision(D division) {
        this.division = division;
    }

    public C getClub() {
        return club;
    }

    public void setClub(C club) {
        this.club = club;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getDrawn() {
        return drawn;
    }

    public void setDrawn(int drawn) {
        this.drawn = drawn;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getScoreFor() {
        return scoreFor;
    }

    public void setScoreFor(int scoreFor) {
        this.scoreFor = scoreFor;
    }

    public int getScoreAgainst() {
        return scoreAgainst;
    }

    public void setScoreAgainst(int scoreAgainst) {
        this.scoreAgainst = scoreAgainst;
    }
}
