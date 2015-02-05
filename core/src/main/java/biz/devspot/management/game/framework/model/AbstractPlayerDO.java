package biz.devspot.management.game.framework.model;

import biz.devspot.entity.framework.core.annotation.AssociatedEntity;

public class AbstractPlayerDO<CL extends AbstractClub> extends AbstractPersonDO {

    @AssociatedEntity
    private CL club;

    public CL getClub() {
        System.out.println("getClub: " + club);
        return club;
    }

    public void setClub(CL club) {
        System.out.println("setClub: " + club);
        this.club = club;
    }
}
