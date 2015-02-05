package biz.devspot.management.game.framework.model;

import biz.devspot.entity.framework.core.annotation.AssociatedEntity;
import java.util.List;

public class AbstractDivisionDO<L extends AbstractLeague> extends AbstractCompetitionDO {

    @AssociatedEntity
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
