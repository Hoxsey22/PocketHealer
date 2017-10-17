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
        super("Earthquake", 5, 15f, owner);
    }

    public Earthquake(Boss owner, float speed) {
        super("Earthquake", 5, speed, owner);
    }

    @Override
    public void start() {
        super.start();


        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                startChannel();
                timer.stop();
            }
        },speed, speed);

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

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}