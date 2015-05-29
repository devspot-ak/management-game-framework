package biz.devspot.management.game.framework.data;

import biz.devspot.management.game.framework.model.AbstractClub;

public class AbstractPlayerDO<CL extends AbstractClub> extends AbstractPersonDO {

    private int age;
    private String status;
    private long value;
    private long askingPrice;
    private CL club;
    private int fitness;
    private int stamina;
    private String type;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(long askingPrice) {
        this.askingPrice = askingPrice;
    }

    public CL getClub() {
        return club;
    }

    public void setClub(CL club) {
        this.club = club;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
}
