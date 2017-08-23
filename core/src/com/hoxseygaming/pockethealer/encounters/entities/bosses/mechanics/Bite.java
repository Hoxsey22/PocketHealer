package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class Bite extends Mechanic {

    public Bite(Boss owner) {
        super("Bite", 30, 2f, owner);
    }

    public Bite(String name, int damage, float speed, Boss owner)   {
        super(name, damage, speed, owner);
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                target.takeDamage(damage);
            }
        },speed, speed);
    }
}
