package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class Flurry extends Mechanic{


    public Timer channel;
    private Random dice;

    public Flurry(Boss owner) {
        super("Flurry", 10, 20f, owner);
        dice = new Random();
    }

    public Flurry(Boss owner, int damage, float speed) {
        super("Flurry", damage, speed, owner);
        dice = new Random();
    }

    @Override
    public void start() {
        super.start();
        startAnnouncementTimer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                startChannel();
                announcementTimer.stop();
                timer.stop();
            }
        },speed, speed);

    }

    public void startChannel()  {
        channel = new Timer();

        channel.schedule(new Timer.Task() {
            int count =  0;
            ArrayList<RaidMember> random  = getRaid().getRandomRaidMember(1);
            @Override
            public void run() {
                if(count != 10) {
                    count++;
                    if(random.get(0).isDead)
                        random = getRaid().getRandomRaidMember(1);
                    random.get(0).takeDamage(damage);
                    //getRaid().takeDamage(damage);
                }
                else    {
                    channel.stop();
                    channel.clear();
                    timer.start();
                }
            }
        },0.5f,0.5f,10);
    }


}