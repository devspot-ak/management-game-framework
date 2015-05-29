package biz.devspot.management.game.framework.data;

import biz.devspot.entity.framework.core.model.AbstractDataObject;
import biz.devspot.management.game.framework.model.AbstractContinent;
import biz.devspot.management.game.framework.model.AbstractLeague;

public class AbstractCountryDO<CO extends AbstractContinent, L extends AbstractLeague> extends AbstractDataObject {

    private String name;
    private CO continent;
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
