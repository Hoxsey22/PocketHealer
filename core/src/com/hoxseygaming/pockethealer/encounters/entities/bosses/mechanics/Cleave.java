package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class Cleave extends Mechanic {

    int numOfTargets;

    public Cleave(Boss owner) {
        super("Cleave", 15, 5f, owner);
        numOfTargets = 2;
    }

    public Cleave(Boss owner, float speed) {
        super("Cleave", 15, speed, owner);
        numOfTargets = 2;
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ArrayList<RaidMember> raidMembers = getRaid().getRandomRaidMember(numOfTargets);
                for(int i = 0; i < raidMembers.size(); i++)   {
                    raidMembers.get(i).takeDamage(damage);
                }
            }
        },speed, speed);
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
