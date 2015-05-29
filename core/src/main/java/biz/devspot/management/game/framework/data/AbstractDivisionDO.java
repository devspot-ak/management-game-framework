package biz.devspot.management.game.framework.data;

import biz.devspot.management.game.framework.model.AbstractLeague;

public class AbstractDivisionDO<L extends AbstractLeague> extends AbstractCompetitionDO {

    private L league;
    private int tier;

    public L getLeague() {
        return league;
    }

    public void setLeague(L league) {
        this.league = league;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }
}
