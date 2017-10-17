package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/28/2017.
 */

public class Swipe extends Mechanic {

    int tankDeaths = 0;

    public Swipe(Boss owner) {
        super("Swipe", owner.getDamage(), 1.5f, owner);
    }

    public Swipe(Boss owner, float speed) {
        super("Swipe", owner.getDamage(), speed, owner);
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {

                if(getMainTank().isDead() && getOffTank().isDead())    {
                    ArrayList<RaidMember> randoms = getRaid().getRandomRaidMember(2);
                    checkDeathProtection(randoms.get(0), damage);
                    checkDeathProtection(randoms.get(1), (int)(damage * 0.5f));
                    return;
                }

                if(getMainTank().isDead())    {
                    checkDeathProtection(getOffTank(), damage);
                    checkDeathProtection(getRaid().getRandomRaidMember(1).get(0), (int)(damage * 0.5f));
                    return;
                }

                if(getOffTank().isDead())    {
                    checkDeathProtection(getMainTank(), damage);
                    checkDeathProtection(getRaid().getRandomRaidMember(1).get(0), (int)(damage * 0.5f));
                    return;
                }

                checkDeathProtection(getMainTank(), damage);
                checkDeathProtection(getOffTank(), (int)(damage * 0.5f));

            }
        },speed,speed);
    }

    private void checkDeathProtection(RaidMember target, int dmg) {

        if(target.getHpPercent() > 0.1) {
            if(target.getHp() < dmg)    {
                target.takeDamage(target.getHp()-1);
                return;
            }
        }
        target.takeDamage(dmg);

    }
}
