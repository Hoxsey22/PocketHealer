package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 7/12/2017.
 */

public class Poison extends Mechanic {


    public Poison(Boss owner) {
        super("Poison", 10, 2f, owner);
        debuff = Debuff.POISON;
    }

    public Poison(Boss owner, float speed) {
        super("Poison", 10, speed, owner);
        debuff = Debuff.POISON;
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
                if(!tar.containsEffects(debuff))    {
                    stop();
                }
                if(count % (speed/0.01f)   == 0)    {
                    tar.takeDamage(damage);
                }
            }
        },0.01f,0.01f);
    }

    @Override
    public void applyMechanic() {
        super.applyMechanic();
    }

    public void stop()  {
        timer.stop();
        timer.clear();
    }
}
