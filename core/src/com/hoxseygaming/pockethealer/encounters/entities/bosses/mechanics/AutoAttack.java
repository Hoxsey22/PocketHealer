package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 7/26/2017.
 */

public class AutoAttack extends Mechanic {

    public AutoAttack(Boss owner) {
        super(owner);
        id = 1;
        create();
    }

    @Override
    public void create() {
        super.create();
        name = "Auto Attack";
        damage = owner.getDamage();
        speed = 2f;
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {

                System.out.println("Boss attacked id["+owner.target.getId()+"] role:"+owner.target.getRole());
                if(owner.getTarget().isDead())
                    owner.nextThreat();
                if(!owner.getTarget().isDead()) {
                    owner.getTarget().takeDamage(damage);
                    if(owner.getTarget().isDead())
                        owner.nextThreat();
                }
                if (owner.getEnemies().isRaidDead()) {
                    System.out.println("RAID IS DEAD!");
                    System.out.println("Auto Attack Timer has stopped");
                    timer.stop();
                    timer.clear();
                    return;
                }
                System.out.println("AUTO ATTACK!");
            }
        },speed,speed);
    }

}
