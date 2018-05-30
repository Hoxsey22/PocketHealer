package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.IgniteEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class Ignite extends Mechanic {

    private int numOfTargets;

    public Ignite(Boss owner, float speed) {
        super("Ignite", 0, 18f, owner);
        numOfTargets = 1;
        setBgMech();
    }


    @Override
    public void action() {
        ArrayList<RaidMember> temp  = getOwner().getEnemies().getRandomRaidMember(numOfTargets);

        for (int i = 0; i < temp.size(); i++)   {
            temp.get(i).addStatusEffect(new IgniteEffect(getOwner()));
        }
    }

}
