package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class Flurry extends Mechanic{


    private Timer channel;

    @SuppressWarnings("unused")
    public Flurry(Boss owner) {
        super("Flurry", 10, 20f, owner);
        setAnnounce();
    }

    public Flurry(Boss owner, int damage, float speed) {
        super("Flurry", damage, speed, owner);
        setAnnounce();
    }

    @Override
    public void action() {
        //pausePhase();
        startChannel();
        //getTimer().stop();
        pause();
    }

    private void startChannel()  {
        channel = new Timer();

        channel.scheduleTask(new Timer.Task() {
            int count =  0;
            ArrayList<RaidMember> random  = getRaid().getRandomRaidMember(1);
            @Override
            public void run() {
                if(count != 10) {
                    count++;
                    if(random.get(0).isDead()) {
                        random = getRaid().getRandomRaidMember(1);
                    }
                    if(random != null) {
                        random.get(0).takeDamage(getDamage());
                    }
                }
                else    {
                    channel.stop();
                    channel.clear();
                    resume();
                }
            }
        },0.5f,0.5f,10);
    }


}
