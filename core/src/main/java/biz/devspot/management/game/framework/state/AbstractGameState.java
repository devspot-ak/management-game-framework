package biz.devspot.management.game.framework.state;

import biz.devspot.entity.framework.core.query.Query;
import biz.devspot.management.game.framework.model.AbstractManager;
import biz.devspot.management.game.framework.model.AbstractWorld;

public abstract class AbstractGameState<W extends AbstractWorld, M extends AbstractManager> {

    private String name;
    private W world;
    private M manager;
    private int season = 1;
    private int week = 1;
    private int day = 1;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public W getWorld() {
        return world;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setWorld(W world) {
        this.world = world;
    }

    public M getManager() {
        return manager;
    }

    public void setManager(M manager) {
        this.manager = manager;
    }

}
