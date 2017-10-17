package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 8/28/2017.
 */

public class Decimate extends Mechanic {

    public Decimate(Boss owner) {
        super("Decimate", 0, 30f, owner);
    }

    public Decimate(Boss owner, float speed) {
        super("Decimate", 0, speed, owner);
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                for(int i = 0; i <  getRaid().raidMembers.size(); i++)   {
                    if(getRaid().getRaidMember(i).getHpPercent() <  0.1)    {
                        getRaid().getRaidMember(i).takeDamage(100);
                    }
                    else    {
                        getRaid().getRaidMember(i).takeDamage(getRaid().getRaidMember(i).getHp() - (int)(getRaid().getRaidMember(i).getMaxHp()*0.1));
                    }
                }
            }
        },speed,speed);
    }
}
