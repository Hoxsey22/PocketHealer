package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.PoisonEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class PoisonStab extends Mechanic {

    public int numOfTargets;

    public PoisonStab(Boss owner) {
        super("Poison Stab", owner.damage, 18f, owner);
        numOfTargets = 1;
        announce = true;
    }

    @Override
    public void action() {
        ArrayList<RaidMember> temp  = owner.enemies.getRandomRaidMember(numOfTargets);

        for (int i = 0; i < temp.size(); i++)   {
            temp.get(i).takeDamage(damage);
            temp.get(i).addStatusEffect(new PoisonEffect(owner, 5,0.2f));
        }
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
