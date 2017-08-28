package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 7/12/2017.
 */

public class Disease extends Mechanic {


    public Disease(Boss owner) {
        super("Disease", 0, 0, owner);
        debuff = Debuff.DISEASE;
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
            }
        },0.1f,0.1f);
    }

    @Override
    public void applyMechanic() {
        if(!target.containsEffects(debuff))
            super.applyMechanic();
        target.applyHealingAbsorb(50);
    }
}
