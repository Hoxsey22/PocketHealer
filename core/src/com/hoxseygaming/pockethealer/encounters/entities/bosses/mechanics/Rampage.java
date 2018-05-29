package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class Rampage extends Mechanic{

    private Timer channel;

    public Rampage(Boss owner) {
        super("Rampage", 10, 20f, owner);
        setAnnounce(true);
    }

    public Rampage(Boss owner, int damage, float speed) {
        super("Rampage", damage, speed, owner);
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
                if(count != 20) {
                    ArrayList<RaidMember> randoms  = getRaid().getRandomRaidMember(5);
                    count++;
                    for (int i = 0; i < randoms.size(); i++)    {
                        if(!randoms.get(i).isDead())    {
                            randoms.get(i).takeDamage(getDamage());
                        }
                    }
                }
                else    {
                    channel.stop();
                    channel.clear();
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
