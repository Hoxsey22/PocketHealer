package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
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
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {

                if(owner.getTarget().isDead){
                    owner.setTarget(owner.getNextThreat());
                }
                owner.getTarget().takeDamage(damage);
                owner.getNextThreat().takeDamage(damage);
            }
        },speed,speed);
    }
}
