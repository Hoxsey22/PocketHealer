package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 7/26/2017.
 */

public class Pounce extends Mechanic {

    ArrayList<Bleed> bleeds;
    int numOfTargets;

    public Pounce(Boss owner) {
        super("Pounce",30,4f,owner);
        id = 4;
        numOfTargets = 3;
        bleeds = new ArrayList<>();
    }

    public Pounce(Boss owner, float speed) {
        super("Pounce",30,speed,owner);
        id = 4;
        bleeds = new ArrayList<>();
    }

    @Override
    public void start() {
        super.start();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ArrayList<RaidMember> temp  = getRaid().getRandomRaidMember(3);

                for (int i = 0; i < temp.size(); i++)   {
                    if(temp.get(i) != null) {
                        temp.get(i).takeDamage(damage);
                        Bleed bleed = new Bleed(owner);
                        bleed.setTarget(temp.get(i));
                        bleeds.add(bleed);
                        bleed.start();
                    }
                }

            }
        },speed, speed);
    }

    @Override
    public void applyMechanic() {
        super.applyMechanic();
    }

    @Override
    public void stop() {
        super.stop();
        for(int i = 0; i < bleeds.size(); i++)   {
            bleeds.get(i).stop();
        }
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
