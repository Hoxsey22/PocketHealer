package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 7/26/2017.
 */

public class AutoAttack extends Mechanic {

    public AutoAttack(Boss owner) {
        super("Auto Attack", owner.getDamage(), 2f, owner);
        id = 1;
        damage = owner.getDamage();
        bgMech = true;
    }

    public AutoAttack(Boss owner, float speed) {
        super("Auto Attack", owner.getDamage(), speed, owner);
        id = 1;
        damage = owner.getDamage();
        bgMech = true;
    }

    @Override
    public void action() {
        System.out.println("Owner: "+owner+"\n Target: "+owner.getTarget());
        if(owner.getTarget().isDead())
            owner.nextThreat();
        if(!owner.getTarget().isDead()) {
            owner.getTarget().takeDamage(damage);
            if(owner.getTarget().isDead())
                owner.nextThreat();
        }
        if (owner.getEnemies().isRaidDead()) {
            stop();
        }
    }

}
