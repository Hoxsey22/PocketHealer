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
        name = "Auto Attack";
        damage = owner.damage;
        speed = 2f;

    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {

                System.out.println("Boss attacked id["+owner.target.getId()+"] role:"+owner.target.getRole());
                if(owner.target.isDead())
                    owner.nextThreat();
                if(!owner.target.isDead()) {
                    owner.target.takeDamage(damage);
                    if(owner.target.isDead())
                        owner.nextThreat();
                }
                if (owner.enemies.isRaidDead()) {
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
