package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class Rampage extends Mechanic{


    public Timer channel;
    private Random dice;

    public Rampage(Boss owner) {
        super("Rampage", 10, 20f, owner);
        dice = new Random();
        announce = true;
    }

    public Rampage(Boss owner, int damage, float speed) {
        super("Rampage", damage, speed, owner);
        dice = new Random();
        announce = true;
    }

    @Override
    public void action() {
        //pausePhase();
        startChannel();
        //timer.stop();
        pause();
    }

    public void startChannel()  {
        channel = new Timer();

        channel.schedule(new Timer.Task() {
            int count =  0;

            @Override
            public void run() {
                if(count != 20) {
                    ArrayList<RaidMember> randoms  = getRaid().getRandomRaidMember(5);
                    count++;
                    for (int i = 0; i < randoms.size(); i++)    {
                        if(!randoms.get(i).isDead)    {
                            randoms.get(i).takeDamage(damage);
                        }
                    }
                }
                else    {
                    channel.stop();
                    channel.clear();
                    //timer.start();
                    resume();
                }
            }
        },1f,1f,20);
    }

    @Override
    public void stop() {
        super.stop();
        channel.stop();
        channel.clear();
    }
}
