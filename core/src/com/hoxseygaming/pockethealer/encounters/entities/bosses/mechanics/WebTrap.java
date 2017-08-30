package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 8/23/2017.
 */

public class WebTrap extends Mechanic {

    int numOfTargets;

    public WebTrap(Boss owner) {
        super("Web Trap", 0, 2f, owner);
        numOfTargets = 1;
    }

    public WebTrap(Boss owner, float speed) {
        super("Web Trap", 0, speed, owner);
        numOfTargets = 1;
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                RaidMember [] temp = owner.getEnemies().getRandomRaidMember(numOfTargets);
                for(int i = 0; i < temp.length; i++)   {
                    Disease disease = new Disease(owner);
                    disease.setTarget(temp[i]);
                    disease.start();
                }
            }
        },speed,speed);
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTarget(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
