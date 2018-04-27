package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class UnstablePyroblast extends Mechanic {

    public int numOfTargets;

    public UnstablePyroblast(Boss owner) {
        super("Unstable Pyroblast", 0, 20f, owner);
        numOfTargets = 1;
    }

    public UnstablePyroblast(Boss owner, float speed) {
        super("Unstable Pyroblast", 0, speed, owner);
        numOfTargets = 1;
    }

    @Override
    public void start() {
        super.start();
        startAnnouncementTimer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                numOfTargets = new Random().nextInt(3)*1;
                ArrayList<RaidMember> temp  = owner.enemies.getRandomRaidMember(numOfTargets);

                for (int i = 0; i < temp.size(); i++)   {
                    temp.get(i).takeDamage(new Random().nextInt(50)*1);
                }

            }
        },speed, speed);
    }

    @Override
    public void stop() {
        super.stop();
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
