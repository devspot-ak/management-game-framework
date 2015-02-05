package biz.devspot.management.game.framework.model;

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
