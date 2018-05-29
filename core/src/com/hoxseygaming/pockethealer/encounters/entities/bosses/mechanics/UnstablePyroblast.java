package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class UnstablePyroblast extends Mechanic {

    private int numOfTargets;

    public UnstablePyroblast(Boss owner) {
        super("Unstable Pyroblast", 0, 20f, owner);
        numOfTargets = 1;
        setAnnounce(true);
    }

    public UnstablePyroblast(Boss owner, float speed) {
        super("Unstable Pyroblast", 0, speed, owner);
        numOfTargets = 1;
        setAnnounce(true);
    }

    @Override
    public void action() {
        numOfTargets = new Random().nextInt(5)*1;
        ArrayList<RaidMember> temp  = getOwner().getEnemies().getRandomRaidMember(numOfTargets);

        for (int i = 0; i < temp.size(); i++)   {
            temp.get(i).takeDamage(new Random().nextInt(70)*1);
        }
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
