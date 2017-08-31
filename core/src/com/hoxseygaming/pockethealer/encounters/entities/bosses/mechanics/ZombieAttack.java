package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 7/26/2017.
 */

public class ZombieAttack extends Mechanic {

    public ZombieAttack(Boss owner) {
        super("Auto Attack", 0, 2f, owner);
        id = 1;
    }

    public ZombieAttack(Boss owner, float speed) {
        super("Auto Attack", 0, speed, owner);
        id = 1;
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                owner.getEnemies().takeDamage(getZombieDamage());
            }
        },speed,speed);
    }

    public int getZombieDamage()    {
        if(owner.getHpPercent() > 0.79f)    {
            return 10 + owner.getDamage();
        }
        if(owner.getHpPercent() > 0.59f)    {
            return 8 + owner.getDamage();
        }
        if(owner.getHpPercent() > 0.39f)    {
            return 6 + owner.getDamage();
        }
        if(owner.getHpPercent() > 0.19f)    {
            return 4 + owner.getDamage();
        }
        return 2 + owner.getDamage();
    }

}
