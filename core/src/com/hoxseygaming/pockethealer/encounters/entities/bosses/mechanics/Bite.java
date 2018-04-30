package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class Bite extends Mechanic {

    public Bite(Boss owner) {
        super("Bite", 20, 2f, owner);
    }

    public Bite(Boss owner, float speed)   {
        super("Bite", 20, speed, owner);
        announce = true;
    }

    @Override
    public void action() {
        ArrayList<RaidMember> random  = getRaid().getRandomRaidMember(1);
        random.get(0).takeDamage(damage);
    }
}
