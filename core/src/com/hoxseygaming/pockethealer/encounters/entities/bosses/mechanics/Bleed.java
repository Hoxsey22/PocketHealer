package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 7/12/2017.
 */

public class Bleed extends Mechanic {

    public Bleed( Boss owner) {
        super("BleedEffect", 5, 3f, owner);
        debuff = Debuff.BLEED;
    }

    public Bleed(Boss owner, float speed) {
        super("BleedEffect", 5, speed, owner);
        debuff = Debuff.BLEED;
    }

    public void amplify()   {
        damage = damage + 5;
    }

    public void amplify(int amp)   {
        damage = damage + amp;
    }

    @Override
    public void start() {
        super.start();
        applyMechanic();

        final RaidMember tar = target;
        timer.scheduleTask(new Timer.Task() {
            int count = 0;

            @Override
            public void run() {
                count++;
                if(tar.getHpPercent() > 0.9)    {
                    stop();
                    tar.removeEffect(debuff);
                }

                if(count % (speed/0.01f)   == 0)    {
                    tar.takeDamage(damage);
                    amplify();
                }
            }
        },0.01f,0.01f);
    }
}
