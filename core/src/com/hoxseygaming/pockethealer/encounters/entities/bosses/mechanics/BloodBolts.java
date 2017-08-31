package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class BloodBolts extends Mechanic{


    public Timer channel;

    public BloodBolts(Boss owner) {
        super("Blood Bolts", 10, 45f, owner);
        announcementString = owner.getName()+" is about to cast Blood Bolts!";
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                startChannel();
                stop();
            }
        },speed, speed);
        startAnnouncementTimer();
    }

    public void startChannel()  {
        channel = new Timer();

        channel.schedule(new Timer.Task() {
            int count =  0;
            @Override
            public void run() {
                if(count != 3) {
                    count++;
                    ArrayList<RaidMember> raidMembers = raid.getRandomRaidMember(4);
                    for(int i = 0; i < raidMembers.size(); i++)   {
                        raidMembers.get(i).takeDamage(damage);
                    }
                }
                else    {
                    channel.stop();
                    channel.clear();
                    start();
                }
            }
        },0.5f,0.5f,3);
    }
}
