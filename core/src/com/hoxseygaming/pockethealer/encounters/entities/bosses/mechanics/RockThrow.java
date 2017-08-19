package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 8/18/2017.
 */

public class RockThrow extends Mechanic {

    int numOfTargets;

    public RockThrow(Boss owner) {
        super(owner);
        speed = 2f;
        create();
    }

    public RockThrow(Boss owner, float speed) {
        super(owner);
        this.speed = speed;
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 50;
        name = "Rock Throw";
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                RaidMember tempEnemies [] = owner.getEnemies().getRandomRaidMember(numOfTargets);

                for(int i = 0; i < tempEnemies.length-1; i++)   {
                    tempEnemies[i].takeDamage(damage);
                }

            }
        },speed,speed);
        startAnnouncementTimer();
    }
}
