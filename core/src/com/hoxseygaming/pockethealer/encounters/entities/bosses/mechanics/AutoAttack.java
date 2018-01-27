package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 7/26/2017.
 */

public class AutoAttack extends Mechanic {

    public AutoAttack(Boss owner) {
        super("Auto Attack", owner.getDamage(), 2f, owner);
        id = 1;
        damage = owner.getDamage();
    }

    public AutoAttack(Boss owner, float speed) {
        super("Auto Attack", owner.getDamage(), speed, owner);
        id = 1;
        damage = owner.getDamage();
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {

                if(owner.getTarget().isDead())
                    owner.nextThreat();
                if(!owner.getTarget().isDead()) {
                    owner.getTarget().takeDamage(damage);
                    if(owner.getTarget().isDead())
                        owner.nextThreat();
                }
                if (owner.getEnemies().isRaidDead()) {
                    stop();
                    return;
                }
            }
        },speed,speed);
    }

}
