package biz.devspot.management.game.framework.data;

import biz.devspot.entity.framework.core.model.AbstractDataObject;
import biz.devspot.management.game.framework.model.AbstractClubTactics;
import biz.devspot.management.game.framework.model.AbstractDivision;
import biz.devspot.management.game.framework.model.AbstractManager;

public class AbstractClubDO<L extends AbstractDivision, M extends AbstractManager, CT extends AbstractClubTactics> extends AbstractDataObject {

    private String name;
    private L league;
    private M manager;
    private CT tactics;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public L getLeague() {
        return league;
    }

    public void setLeague(L league) {
        this.league = league;
    }

    public M getManager() {
        return manager;
    }

    public void setManager(M manager) {
        this.manager = manager;
    }

    public CT getTactics() {
        return tactics;
    }

    public void setTactics(CT tactics) {
        this.tactics = tactics;
    }
}
