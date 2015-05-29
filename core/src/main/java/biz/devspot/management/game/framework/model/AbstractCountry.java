package biz.devspot.management.game.framework.model;

import biz.devspot.management.game.framework.data.AbstractCountryDO;

public abstract class AbstractCountry<DO extends AbstractCountryDO, CO extends AbstractContinent, L extends AbstractLeague> extends MGFEntity<DO> {

    public AbstractCountry(CO continent, String name, L league) {
        data.setContinent(continent);
        data.setName(name);
        data.setLeague(league);
    }

    public String getName() {
        return data.getName();
    }

    public CO getContinent() {
        return (CO) data.getContinent();
    }

    public L getLeague() {
        return (L) data.getLeague();
    }
    
    public void rollover(){
        getLeague().rollover();
    }

}
