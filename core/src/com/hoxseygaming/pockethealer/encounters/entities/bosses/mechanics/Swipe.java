package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/28/2017.
 */

public class Swipe extends Mechanic {

    public Swipe(Boss owner) {
        super("Swipe", owner.getDamage(), 1.5f, owner);
    }

    public Swipe(Boss owner, float speed) {
        super("Swipe", owner.getDamage(), speed, owner);
    }

    @Override
    public void action() {
        if(getMainTank().isDead() && getOffTank().isDead())    {
            ArrayList<RaidMember> randoms = getRaid().getRandomRaidMember(2);
            randoms.get(0).takeDamage(damage);
            randoms.get(1).takeDamage((int)(damage/2));
            //checkDeathProtection(randoms.get(0), damage);
            //checkDeathProtection(randoms.get(1), (int)(damage * 0.5f));
            return;
        }

        if(getMainTank().isDead())    {

            getOffTank().takeDamage(damage);
            getRaid().getRandomRaidMember(1).get(0).takeDamage((int)(damage/2));

            //checkDeathProtection(getOffTank(), damage);
            //checkDeathProtection(getRaid().getRandomRaidMember(1).get(0), (int)(damage * 0.5f));
            return;
        }

        if(getOffTank().isDead())    {
            getMainTank().takeDamage(damage);
            getRaid().getRandomRaidMember(1).get(0).takeDamage((int)(damage/2));

            //checkDeathProtection(getMainTank(), damage);
            //checkDeathProtection(getRaid().getRandomRaidMember(1).get(0), (int)(damage * 0.5f));
            return;
        }
        getMainTank().takeDamage(damage);
        getOffTank().takeDamage((int)(damage/2));


        //checkDeathProtection(getMainTank(), damage);
        //checkDeathProtection(getOffTank(), (int)(damage * 0.5f));

    }
}
