package com.hoxseygaming.pockethealer.reformat.entities.bosses.boss.timers;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.reformat.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.reformat.entities.bosses.Boss;

/**
 * Created by Hoxsey on 6/19/2017.
 */
public class CleaveTimer {

    public Boss owner;
    public Timer timer;
    public float speed;
    public int damage;
    public int numOfTargets;
    public boolean isActive;

    /**
     *
     * @param boss
     * @param speed
     */
    public CleaveTimer(Boss boss, float speed)    {
        owner = boss;
        this.speed = speed;
        damage = owner.damage;
        numOfTargets = 2;
        isActive = false;
    }

    public CleaveTimer(Boss boss, int damage, float speed)    {
        owner = boss;
        this.speed = speed;
        this.damage = damage;
        numOfTargets = 2;
        isActive = false;
    }

    public CleaveTimer(Boss boss, int damage, float speed, int numOfTargets)    {
        owner = boss;
        this.speed = speed;
        this.damage = damage;
        this.numOfTargets = numOfTargets;
        isActive = false;
    }

    public void startTimer()    {

        isActive = true;
        timer.schedule(new Timer.Task() {
            RaidMember [] targets = new RaidMember[2];

            @Override
            public void run() {
                targets = owner.enemies.getRandomRaidMember(numOfTargets);
                for (int i = 0; i < numOfTargets; i++) {
                    if (targets[i] != null)
                        targets[i].receiveDamage(owner.damage);
                        //owner.dealDamage(targets[i]);
                }
            }
        },6.5f,speed);


    }
}
