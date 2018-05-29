package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 8/18/2017.
 */

public class Earthquake extends Mechanic {

    private int numOfTargets;
    private Timer channel;

    public Earthquake(Boss owner) {
        super("Earthquake", 8, 15f, owner);
        setAnnounce(true);
    }

    public Earthquake(Boss owner, float speed) {
        super("Earthquake", 8, speed, owner);
        setAnnounce(true);
    }

    @Override
    public void action() {
        startChannel();
        getTimer().stop();
    }

    public void startChannel()  {
        channel = new Timer();

        channel.schedule(new Timer.Task() {
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

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
