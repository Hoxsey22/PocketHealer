package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 8/4/2017.
 */

public class BullCharge extends Mechanic {


    public BullCharge(Boss owner) {
        super(owner);
        id = 8;
        create();

    }

    @Override
    public void create() {
        super.create();
        name = "Bull Charge";
        announcementString = "Proctor is about to charge someone!";
        damage = 50;
        speed = 6f;
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                RaidMember selected = owner.getEnemies().getRandomRaidMember(1)[0];
                if(selected != null)    {
                    selected.takeDamage(damage);
                }
            }
        },speed,speed);
        startAnnouncementTimer();
    }
}
