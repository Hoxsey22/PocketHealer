package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 8/28/2017.
 */

public class Fireball extends Mechanic {

    public Fireball(Boss owner) {
        super("Fireball", 20, 2f, owner);
    }

    public Fireball(Boss owner, float speed) {
        super("Fireball", 20, speed, owner);
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                RaidMember random [] = owner.getEnemies().getRandomRaidMember(1);
                random[0].takeDamage(damage);
            }
        },speed,speed);
    }
}
