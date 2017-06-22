package com.hoxseygaming.pockethealer.encounters.entities.bosses.timers;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

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

        isActive = true;
       timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                System.out.println("Boss attacked id["+owner.target.getId()+"] role:"+owner.target.getRole());
                owner.target.takeDamage(owner.getDamage());
            }
        },3f,speed);


    }
}
