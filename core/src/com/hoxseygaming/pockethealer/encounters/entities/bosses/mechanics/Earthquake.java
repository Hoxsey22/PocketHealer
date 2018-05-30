package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 8/18/2017.
 */

public class Earthquake extends Mechanic {

    private int numOfTargets;
    private Timer channel;

    @SuppressWarnings("unused")
    public Earthquake(Boss owner) {
        super("Earthquake", 8, 15f, owner);
        setAnnounce();
    }

    public Earthquake(Boss owner, float speed) {
        super("Earthquake", 8, speed, owner);
        setAnnounce();
    }

    @Override
    public void action() {
        startChannel();
        getTimer().stop();
    }

    private void startChannel()  {
        channel = new Timer();

        channel.scheduleTask(new Timer.Task() {
            int count =  0;
            @Override
            public void run() {
                if(count != 3) {
                    count++;
                    getRaid().takeDamage(getDamage());
                }
                else    {
                    channel.stop();
                    channel.clear();
                    getTimer().start();
                }
            }
        },0.5f,0.5f,5);
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
