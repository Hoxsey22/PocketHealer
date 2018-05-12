package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 8/18/2017.
 */

public class Earthquake extends Mechanic {

    public int numOfTargets;
    public Timer channel;

    public Earthquake(Boss owner) {
        super("Earthquake", 8, 15f, owner);
        announce = true;
    }

    public Earthquake(Boss owner, float speed) {
        super("Earthquake", 8, speed, owner);
        announce = true;
    }

    @Override
    public void action() {
        startChannel();
        timer.stop();
    }

    public void startChannel()  {
        channel = new Timer();

        channel.schedule(new Timer.Task() {
            int count =  0;
            @Override
            public void run() {
                if(count != 3) {
                    count++;
                    getRaid().takeDamage(damage);
                }
                else    {
                    channel.stop();
                    channel.clear();
                    timer.start();
                }
            }
        },0.5f,0.5f,5);
    }

    @Override
    public void stop() {
        super.stop();
        channel.stop();
        channel.clear();
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
