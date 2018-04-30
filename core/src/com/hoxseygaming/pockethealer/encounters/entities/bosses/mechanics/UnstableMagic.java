package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.UnstableMagicEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class UnstableMagic extends Mechanic {

    public int numOfTargets;

    public UnstableMagic(Boss owner) {
        super("Unstable Magic", 0, 8f, owner);
        numOfTargets = 1;
        announce = true;
    }

    @Override
    public void action() {
        ArrayList<RaidMember> temp  = owner.enemies.getRandomRaidMember(numOfTargets,owner.getEnemies().getDebuffLessRaidMembers("unstable magic"));

        for (int i = 0; i < temp.size(); i++)   {
            temp.get(i).addStatusEffect(new UnstableMagicEffect(owner));
        }
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
