package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.PoisonEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/21/2017.
 */

public class Sting extends Mechanic {

    private int numOfTargets;

    public Sting(Boss owner) {
        super("Sting", 15, 10f, owner);
        numOfTargets = 1;
        setAnnounce(true);
    }

    @Override
    public void action() {
        ArrayList<RaidMember> temp  = getOwner().getEnemies().getRandomRaidMember(numOfTargets);

        for (int i = 0; i < temp.size(); i++)   {
            if(temp.get(i) != null) {
                temp.get(i).takeDamage(getDamage());
                temp.get(i).addStatusEffect(new PoisonEffect(getOwner()));
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
