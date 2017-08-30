package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class PoisonBite extends Mechanic {

    int numOfTargets;

    public PoisonBite(Boss owner) {
        super("Poison Potion", 20, 2f, owner);
        numOfTargets = 1;
    }

    public PoisonBite(Boss owner, float speed) {
        super("Poison Potion", 20, speed, owner);
        numOfTargets = 1;
    }

    @Override
    public void start() {
        super.start();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                RaidMember [] temp = owner.getEnemies().getRandomRaidMember(numOfTargets);
                for(int i = 0; i < temp.length; i++) {
                    temp[i].takeDamage(damage);
                    Poison poison = new Poison(owner);
                    poison.setTarget(temp[i]);
                    poison.start();
                }
            }
        },speed,speed);
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
