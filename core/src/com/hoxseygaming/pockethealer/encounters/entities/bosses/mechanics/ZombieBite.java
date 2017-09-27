package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class ZombieBite extends Mechanic {

    int numOfTargets;

    public ZombieBite(Boss owner) {
        super("Zombie Bite", 20, 2f, owner);
        numOfTargets = 1;
    }

    public ZombieBite(Boss owner, float speed) {
        super("Zombie Bite", 20, speed, owner);
        numOfTargets = 1;
    }

    @Override
    public void start() {
        super.start();
        startAnnouncementTimer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ArrayList<RaidMember> temp = getRaid().getRandomRaidMember(numOfTargets);
                for(int i = 0; i < temp.size(); i++) {
                    temp.get(i).takeDamage(damage);
                    Disease disease = new Disease(owner);
                    disease.setTarget(temp.get(i));
                    disease.start();
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
