package biz.devspot.management.game.framework.data;

import biz.devspot.entity.framework.core.model.AbstractDataObject;
import biz.devspot.management.game.framework.model.AbstractCompetition;
import biz.devspot.management.game.framework.model.state.RoundStates;

public class AbstractRoundDO<C extends AbstractCompetition> extends AbstractDataObject {

    private String status = RoundStates.PENDING;
    private int week;
    private int day;
    private C competition;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public C getCompetition() {
        return competition;
    }

    public void setCompetition(C competition) {
        this.competition = competition;
    }
}
