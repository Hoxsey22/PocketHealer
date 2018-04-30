package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class Massacre extends Mechanic{

    public Massacre(Boss owner) {
        super("Massacre", 0, 35f, owner);
        announce = true;
    }

    public Massacre(Boss owner, float speed) {
        super("Massacre", 0, speed, owner);
        announce = true;
    }

    @Override
    public void action() {
        for(int i = 0; i < getRaid().raidMembers.size(); i++)   {
            getRaid().getRaidMember(i).takeDamage(100);
        }
    }
}
