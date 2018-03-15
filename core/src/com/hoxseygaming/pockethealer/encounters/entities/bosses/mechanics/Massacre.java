package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class Massacre extends Mechanic{


    public Timer channel;

    public Massacre(Boss owner) {
        super("Massacre", 0, 35f, owner);
    }

    public Massacre(Boss owner, float speed) {
        super("Massacre", 0, speed, owner);
    }

    @Override
    public void start() {
        super.start();
        startAnnouncementTimer();


        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                for(int i = 0; i < getRaid().raidMembers.size(); i++)   {
                    getRaid().getRaidMember(i).takeDamage(100);
                }
            }
        },speed, speed);

    }
}
