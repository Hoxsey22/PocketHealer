package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 7/26/2017.
 */

public class Pounce extends Mechanic {

    ArrayList<Bleed> bleeds;

    public Pounce(Boss owner) {
        super(owner);
        id = 4;
        create();
    }

    @Override
    public void create() {
        super.create();
        name = "Pounce";
        damage = 30;
        speed = 4f;
        bleeds = new ArrayList<>();
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                RaidMember temp [] = owner.enemies.getRandomRaidMember(3);

                for (int i = 0; i < temp.length; i++)   {
                    if(temp[i] != null) {
                        temp[i].takeDamage(damage);
                        Bleed bleed = new Bleed(owner, temp[i]);
                        bleeds.add(bleed);
                        bleed.start();
                    }
                }

            }
        },speed, speed);

        startAnnouncementTimer();
    }

    @Override
    public void applyMechanic() {
        super.applyMechanic();
    }

    @Override
    public void stop() {
        super.stop();
        for(int i = 0; i < bleeds.size(); i++)   {
            bleeds.get(i).stop();
        }
    }
}
