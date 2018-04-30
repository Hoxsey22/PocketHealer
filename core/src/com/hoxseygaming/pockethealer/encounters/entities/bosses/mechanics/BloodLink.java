package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 8/4/2017.
 */

public class BloodLink extends Mechanic {

    public BloodLink(Boss owner) {
        super("Blood Link",owner.damage,2f,owner);
    }

    public BloodLink(Boss owner, float speed) {
        super("Blood Link",owner.damage,speed,owner);
        bgMech = true;
    }

    @Override
    public void action() {
        if(owner.getTarget().isDead){
            owner.setTarget(owner.getNextThreat());
        }
        owner.getTarget().takeDamage(damage);
        owner.getNextThreat().takeDamage(damage);
    }
}
