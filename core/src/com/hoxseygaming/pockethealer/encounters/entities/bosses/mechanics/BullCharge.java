package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/4/2017.
 */

public class BullCharge extends Mechanic {


    public BullCharge(Boss owner) {
        super("Bull Charge", 50, 6f, owner);
        id = 8;
        announce = true;
    }

    public BullCharge(Boss owner, float speed) {
        super("Bull Charge", 50, speed, owner);
        id = 8;
        announce = true;
    }

    @Override
    public void action() {
        ArrayList<RaidMember> selected = getRaid().getRandomRaidMember(1);
        if(selected != null)    {
            selected.get(0).takeDamage(damage);
        }
    }
}
