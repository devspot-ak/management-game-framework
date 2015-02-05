package biz.devspot.management.game.framework.model;

import biz.devspot.entity.framework.core.annotation.AssociatedEntity;
import biz.devspot.entity.framework.core.model.AbstractDataObject;

public class AbstractClubDO<L extends AbstractDivision, M extends AbstractManager> extends AbstractDataObject {

    private String name;
    @AssociatedEntity
    private L league;
    @AssociatedEntity
    private M manager;

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
}
