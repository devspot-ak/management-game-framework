package biz.devspot.management.game.framework.model;

public abstract class AbstractPlayer<DO extends AbstractPlayerDO, CL extends AbstractClub> extends AbstractPerson<DO> {

    public AbstractPlayer(String firstName, String surname, CL club) {
        super(firstName, surname);
        data.setClub(club);
    }

    public CL getClub() {
        return (CL) data.getClub();
    }
    
    public void release(){
        System.out.println("release");
        data.setClub(null);
        System.out.println("release done");
    }
    
    public void rollover(){
        
    }

}
