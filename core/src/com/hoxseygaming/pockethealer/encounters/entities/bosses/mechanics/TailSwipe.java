package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.CriticalDice;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.BleedEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class TailSwipe extends Mechanic {

    int numOfTargets;

    public TailSwipe(Boss owner) {
        super("Tail Swipe", 20, 5f, owner);
        numOfTargets = 2;
        setAnnounce(true);
    }

    public TailSwipe(Boss owner, float speed) {
        super("Tail Swipe", 20, speed, owner);
        numOfTargets = 5;
        setAnnounce(true);
    }

    @Override
    public void action() {
        ArrayList<RaidMember> raidMembers = getRaid().getRandomRaidMember(numOfTargets);
        for(int i = 0; i < raidMembers.size(); i++)   {
            raidMembers.get(i).takeDamage(getDamage());
            if(CriticalDice.roll(60,100,0)) {
                raidMembers.get(i).addStatusEffect(new BleedEffect(getOwner(), 3f, 5));
            }
        }
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
