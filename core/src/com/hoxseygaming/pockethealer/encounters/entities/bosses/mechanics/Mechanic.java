package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 7/12/2017.
 */

public class Mechanic {

    public enum Debuff  {
        BLEED, POISON, DISEASE, SUNDER, SOULSTEAL,
        MAIME, HEALINGABSORB, BITTEN, BOIL
    }

    public int id;
    public String name;
    public Boss owner;
    public Timer timer;
    public Debuff debuff;
    public RaidMember target;
    public RaidMember mainTank;
    public RaidMember offTank;
    public Raid raid;
    public int damage;
    public float speed;
    public int duration;
    public boolean isActive;
    public String announcementString;
    public Timer announcementTimer;


    public Mechanic(String name, int damage, float speed, Boss owner)   {
        this.owner = owner;
        this.name = name;
        this.damage = damage;
        this.speed = speed;

        target = owner.getTarget();
        isActive = false;
        announcementString = "";
        raid = owner.getEnemies();
        create();
    }

    public void create()    {
        System.out.println("Mechanic created");
        //timer = new Timer();
    }

    public void start()    {
        System.out.println("Timer started!");
        timer = new Timer();
        isActive = true;
    }

    public void startAnnouncementTimer()  {
        announcementTimer = new Timer();

        announcementTimer.scheduleTask(new Timer.Task() {

            int counter = 0;

            @Override
            public void run() {

                counter++;
                if(counter % (int)(speed *10) == 0)    {
                    owner.announcement.setText("");
                }
                if(counter % (int)((speed-1.5f) *10) == 0)    {
                    owner.announcement.setText(announcementString);
                }

            }
        },0.1f, 0.1f);
    }

    public void stop()  {
        System.out.println("Timer stopped!");
        isActive = false;
        timer.stop();
        timer.clear();
        if(announcementTimer != null) {
            announcementTimer.stop();
            announcementTimer.clear();
        }


    }

    public void applyMechanic()  {
        System.out.println("Mechanic applied");
        target.applyEffect(debuff);
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boss getOwner() {
        return owner;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Debuff getDebuff() {
        return debuff;
    }

    public void setDebuff(Debuff debuff) {
        this.debuff = debuff;
    }

    public RaidMember getTarget() {
        return target;
    }

    public void setTarget(RaidMember target) {
        this.target = target;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setMainTank()    {
        mainTank = owner.getMainTank();
    }

    public void setOffTank()    {
        offTank = owner.getOffTank();
    }


}
