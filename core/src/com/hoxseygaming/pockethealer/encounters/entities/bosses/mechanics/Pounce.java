package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 7/26/2017.
 */

public class Pounce extends Mechanic {

    public Pounce(Boss owner) {
        super(owner);
        id = 2;
        name = "Pounce";
        damage = 30;
        speed = 4f;
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                RaidMember temp [] = owner.enemies.getRandomRaidMember(3);
                /*
                target.takeDamage(damage);
                Bleed bleed = new Bleed(owner);
                bleed.start();
                */

                for (int i = 0; i < temp.length; i++)   {
                    temp[i].takeDamage(damage);
                    Bleed bleeds = new Bleed(owner,temp[i]);
                    bleeds.start();
                }

            }
        },speed, speed);
    }

    @Override
    public void applyMechanic() {
        super.applyMechanic();
    }
}
