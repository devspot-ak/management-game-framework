package biz.devspot.management.game.framework.model;

import biz.devspot.management.game.framework.data.AbstractPersonDO;

public abstract class AbstractPerson<DO extends AbstractPersonDO> extends MGFEntity<DO> {
    
    public AbstractPerson(String firstName, String surname) {
        data.setFirstName(firstName);
        data.setSurname(surname);
    }
    
    public String getFirstName(){
        return data.getFirstName();
    }
    
    public String getSurname(){
        return data.getSurname();
    }
    
    public String getName() {
        return getFirstName() + " " + getSurname();
    }
    
}
