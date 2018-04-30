package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class SwarmingShadow extends Mechanic{


    public Timer channel;

    public SwarmingShadow(Boss owner) {
        super("Swarming Shadow", 10, 15f, owner);
        announce = true;
    }

    public SwarmingShadow(Boss owner, int damage, float speed) {
        super("Swarming Shadow", damage, speed, owner);
        announce = true;
    }

    @Override
    public void action() {
        startChannel();
        pause();
    }

    public void startChannel()  {
        channel = new Timer();

        channel.schedule(new Timer.Task() {
            int count =  0;
            @Override
            public void run() {
                if(count != 4) {
                    count++;
                    for(int i = 0; i <  owner.getEnemies().raidMembers.size(); i++)   {
                        owner.getEnemies().getRaidMember(i).takeDamage(damage);
                    }
                }
                else    {
                    channel.stop();
                    channel.clear();
                    timer.start();
                }
            }
        },0.5f,0.5f,4);
    }

    @Override
    public void stop() {
        super.stop();
    }
}
