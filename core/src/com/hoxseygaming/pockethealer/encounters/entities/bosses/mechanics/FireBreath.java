package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class FireBreath extends Mechanic{


    public Timer channel;

    public FireBreath(Boss owner) {
        super("Fire Breath", 10, 20f, owner);
        announcementString = owner.getName()+" is about breath fire!";
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                startChannel();
                stop();
            }
        },speed, speed);
        startAnnouncementTimer();
    }

    public void startChannel()  {
        channel = new Timer();

        channel.schedule(new Timer.Task() {
            int count =  0;
            @Override
            public void run() {
                if(count != 3) {
                    count++;
                    owner.getEnemies().takeDamage(damage);
                }
                else    {
                    channel.stop();
                    channel.clear();
                    start();
                }
            }
        },0.5f,0.5f,3);
    }
}
