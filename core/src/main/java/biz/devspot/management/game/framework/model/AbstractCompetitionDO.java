package biz.devspot.management.game.framework.model;

import biz.devspot.entity.framework.core.model.AbstractDataObject;

public class AbstractCompetitionDO extends AbstractDataObject {

    private String name;
    private int roundIndex = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoundIndex() {
        return roundIndex;
    }

    public void setRoundIndex(int roundIndex) {
        this.roundIndex = roundIndex;
    }
}
