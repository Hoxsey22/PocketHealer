package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class BackStab extends Mechanic {

    public ArrayList<Bleed> bleeds;

    public BackStab(Boss owner) {
        super("Back Stab", 60, 10f, owner);
        bleeds = new ArrayList<>();
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                RaidMember temp [] = owner.enemies.getRandomRaidMember(1);

                for (int i = 0; i < temp.length; i++)   {
                    if(temp[i] != null) {
                        temp[i].takeDamage(damage);
                        Bleed bleed = new Bleed(owner);
                        bleed.setTarget(temp[i]);
                        bleeds.add(bleed);
                        bleed.start();
                    }
                }

            }
        },speed, speed);

        startAnnouncementTimer();
    }

    @Override
    public void stop() {
        super.stop();
        for(int i = 0; i < bleeds.size(); i++)   {
            bleeds.get(i).stop();
        }
    }
}
