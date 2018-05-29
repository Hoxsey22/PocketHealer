package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class SwarmingShadow extends Mechanic{


    private Timer channel;

    public SwarmingShadow(Boss owner) {
        super("Swarming Shadow", 10, 15f, owner);
        setAnnounce(true);
    }

    public SwarmingShadow(Boss owner, int damage, float speed) {
        super("Swarming Shadow", damage, speed, owner);
        setAnnounce(true);
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
                    for(int i = 0; i <  getOwner().getEnemies().getRaidMembers().size(); i++)   {
                        getOwner().getEnemies().getRaidMember(i).takeDamage(getDamage());
                    }
                }
                else    {
                    channel.stop();
                    channel.clear();
                    getTimer().start();
                }
            }
        },0.5f,0.5f,4);
    }

    @Override
    public void stop() {
        super.stop();
        if(channel != null) {
            channel.stop();
            channel.clear();
        }
    }
}
