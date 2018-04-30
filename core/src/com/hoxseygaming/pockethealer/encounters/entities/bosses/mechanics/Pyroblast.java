package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.CriticalDice;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.BurnEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/4/2017.
 */

public class Pyroblast extends Mechanic {


    public Pyroblast(Boss owner) {
        super("Pyroblast", 50, 6f, owner);
        id = 8;
        announce = true;
    }

    public Pyroblast(Boss owner, float speed) {
        super("Pyroblast", 50, speed, owner);
        id = 8;
        announce = true;
    }

    @Override
    public void action() {
        ArrayList<RaidMember> selected = getRaid().getRandomRaidMember(1);
        if(selected != null)    {
            selected.get(0).takeDamage(damage);
            if(CriticalDice.roll(35,100,0))    {
                selected.get(0).addStatusEffect(new BurnEffect(owner));
            }
        }
    }
}
