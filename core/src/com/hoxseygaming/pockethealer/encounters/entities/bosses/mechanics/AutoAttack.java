package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 7/26/2017.
 */

public class AutoAttack extends Mechanic {

    public AutoAttack(Boss owner) {
        super("Auto Attack",owner.getDamage(), 2f, owner);
        setBgMech(true);
    }

    public AutoAttack(Boss owner, float speed) {
        super("Auto Attack", owner.getDamage(), speed, owner);
        setBgMech(true);
    }

    @Override
    public void action() {
        System.out.println("Owner: "+getOwner()+"\n Target: "+getOwner().getTarget());
        if(getOwner().getTarget().isDead())
            getOwner().nextThreat();
        if(!getOwner().getTarget().isDead()) {
            getOwner().getTarget().takeDamage(getDamage());
            if(getOwner().getTarget().isDead())
                getOwner().nextThreat();
        }
        if (getOwner().getEnemies().isRaidDead()) {
            stop();
        }
    }

}
