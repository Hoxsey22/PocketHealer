package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 7/26/2017.
 */

public class ZombieAttack extends Mechanic {

    public ZombieAttack(Boss owner) {
        super("Auto Attack", 0, 2.5f, owner);
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
        if(owner.getHpPercent() > 0.89f)    {
            return 10;
        }
        if(owner.getHpPercent() > 0.79f)    {
            return 9;
        }
        if(owner.getHpPercent() > 0.69f)    {
            return 8;
        }
        if(owner.getHpPercent() > 0.59f)    {
            return 7;
        }
        if(owner.getHpPercent() > 0.49f)    {
            return 6;
        }
        if(owner.getHpPercent() > 0.39f)    {
            return 5;
        }
        if(owner.getHpPercent() > 0.29f)    {
            return 4;
        }
        if(owner.getHpPercent() > 0.19f)    {
            return 3;
        }
        if(owner.getHpPercent() > 0.09f)    {
            return 2;
        }
        if(owner.getHpPercent() > 0.0f)    {
            return 1;
        }
        return 2;
    }

}
