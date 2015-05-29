package biz.devspot.management.game.framework.builder;

import biz.devspot.management.game.framework.model.AbstractPerson;
import biz.devspot.management.game.framework.util.RandomAccessList;

public abstract class AbstractPersonBuilder<B extends AbstractPersonBuilder, P extends AbstractPerson> extends AbstractEntityBuilder<P> {

    protected RandomAccessList<String> firstNames;
    protected RandomAccessList<String> surnames;

    protected String firstName;
    protected String surname;

    public AbstractPersonBuilder() {
    }

    public B setFirstName(String firstName) {
        this.firstName = firstName;
        return (B) this;
    }

    public B setFirstNames(RandomAccessList<String> firstNames) {
        this.firstNames = firstNames;
        return (B) this;
    }

    public B setSurname(String surname) {
        this.surname = surname;
        return (B) this;
    }

    public B setSurnames(RandomAccessList<String> surnames) {
        this.surnames = surnames;
        return (B) this;
    }

    protected String getFirstName(){
        if(firstName!=null){
            return firstName;
        }
        return firstNames.get();
    }
    
    protected String getSurname(){
        if(surname!=null){
            return surname;
        }
        return surnames.get();
    }

}
