package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/18/2017.
 */

public class RockThrow extends Mechanic {

    int numOfTargets;

    public RockThrow(Boss owner) {
        super("Rock Throw", 50, 2f, owner);
    }

    public RockThrow(Boss owner, float speed) {
        super("Rock Throw", 50, speed, owner);
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ArrayList<RaidMember> tempEnemies  = getRaid().getRandomRaidMember(numOfTargets);

                for(int i = 0; i < tempEnemies.size(); i++)   {
                    tempEnemies.get(i).takeDamage(damage);
                }

            }
        },speed,speed);
        startAnnouncementTimer();
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
