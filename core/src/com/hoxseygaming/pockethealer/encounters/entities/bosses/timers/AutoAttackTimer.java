package com.hoxseygaming.pockethealer.encounters.entities.bosses.timers;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 6/19/2017.
 */
public class AutoAttackTimer {

    public Boss owner;
    public Timer timer;
    public float speed;
    public boolean isActive;

    /**
     *
     * @param boss
     * @param speed
     */
    public AutoAttackTimer(Boss boss, float speed)    {
        owner = boss;
        this.speed = speed;
        isActive = false;
    }

    public void startTimer()    {
        timer = new Timer();
        isActive = true;
        timer.schedule(new Timer.Task() {
            @Override
            public void run() {

                System.out.println("Boss attacked id["+owner.target.getId()+"] role:"+owner.target.getRole());
                if(owner.target.isDead())
                    owner.nextThreat();
                if(!owner.target.isDead()) {
                    owner.target.takeDamage(owner.getDamage());
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

            }
        },3f,speed);


    }
}
