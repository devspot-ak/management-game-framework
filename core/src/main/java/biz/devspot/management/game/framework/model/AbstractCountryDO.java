package biz.devspot.management.game.framework.model;

import biz.devspot.entity.framework.core.annotation.AssociatedEntity;
import biz.devspot.entity.framework.core.model.AbstractDataObject;

public class AbstractCountryDO<CO extends AbstractContinent, L extends AbstractLeague> extends AbstractDataObject {

    private String name;
    @AssociatedEntity
    private CO continent;
    @AssociatedEntity
    private L league;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CO getContinent() {
        return continent;
    }

    public void setContinent(CO continent) {
        this.continent = continent;
    }

    public L getLeague() {
        return league;
    }

    public void setLeague(L league) {
        this.league = league;
    }
}
