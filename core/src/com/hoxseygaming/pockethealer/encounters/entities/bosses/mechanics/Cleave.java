package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class Cleave extends Mechanic {

    private int numOfTargets;

    public Cleave(Boss owner) {
        super("Cleave", 15, 5f, owner);
        numOfTargets = 2;
        setBgMech(true);
    }

    public Cleave(Boss owner, float speed) {
        super("Cleave", 15, speed, owner);
        numOfTargets = 2;
        setBgMech(true);
    }

    @Override
    public void action() {
        ArrayList<RaidMember> raidMembers = getRaid().getRandomRaidMember(numOfTargets);
        for(int i = 0; i < raidMembers.size(); i++)   {
            raidMembers.get(i).takeDamage(getDamage());
        }
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
