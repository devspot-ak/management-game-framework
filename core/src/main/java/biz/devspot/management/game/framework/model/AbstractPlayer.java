package biz.devspot.management.game.framework.model;

import biz.devspot.management.game.framework.data.AbstractPlayerDO;
import biz.devspot.entity.framework.core.EntityManagerFactory;
import biz.devspot.entity.framework.core.query.QueryBuilder;
import biz.devspot.management.game.framework.model.state.PlayerStates;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractPlayer<DO extends AbstractPlayerDO, CL extends AbstractClub, TO extends AbstractTransferOffer> extends AbstractPerson<DO> {

    public AbstractPlayer(String firstName, String surname, int age, CL club, long value, String type, int stamina) {
        super(firstName, surname);
        data.setAge(age);
        data.setStatus(PlayerStates.UNDER_CONTRACT);
        data.setValue(value);
        data.setAskingPrice(value);
        data.setClub(club);
        data.setType(type);
        data.setFitness(100);
        data.setStamina(stamina);
    }
    
    public int getAge(){
        return data.getAge();
    }
    
    public String getStatus(){
        return data.getStatus();
    }
    
    public long getValue(){
        return data.getValue();
    }
    
    public long getAskingPrice(){
        return data.getAskingPrice();
    }

    public CL getClub() {
        return (CL) data.getClub();
    }
    
    public String getType(){
        return data.getType();
    }
    
    public List<TO> getTransferOffers(){
        return (List<TO>) EntityManagerFactory.getManager().find(AbstractTransferOffer.class, new QueryBuilder().where("club").isEqualTo(getId()).build());
    }
    
    public int getFitness(){
        return data.getFitness();
    }
    
    public int getStamina(){
        return data.getStamina()/100;
    }
    
    public void release(){
        data.setClub(null);
        data.setStatus(PlayerStates.FREE_AGENT);
    }
    
    public void transferList(long askingPrice){
        data.setAskingPrice(askingPrice);
        data.setStatus(PlayerStates.TRANSFER_LISTED);
    }
    
    public void removeFromTransferList(){
        data.setAskingPrice(getValue());
        data.setStatus(PlayerStates.UNDER_CONTRACT);
    }
    
    public abstract List<PlayerAttribute> getAttributes();
    
    public int getAttributeValue(PlayerAttribute attribute){
        return getAttributeValue(attribute.getName());
    }
    
    public int getAttributeValue(String name){
        try {
            Method method = getClass().getMethod("get" + name);
            return (Integer)method.invoke(this);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        } catch (SecurityException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void rollover(){
        
    }

}
