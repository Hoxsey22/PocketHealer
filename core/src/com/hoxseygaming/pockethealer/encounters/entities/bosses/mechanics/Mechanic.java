package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 7/12/2017.
 */

public class Mechanic {

    public int id;
    public String name;
    public Boss owner;
    public Timer timer;
    public RaidMember target;
    public RaidMember mainTank;
    public RaidMember offTank;
    public Raid raid;
    public int damage;
    public float speed;
    public int duration;
    public boolean announce;
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
        announcementString = owner.getName()+" is about to "+name+".";
        create();
    }

    public void create()    {
        System.out.println("Mechanic created");
    }

    public void start()    {
        System.out.println("Timer started!");
        timer = new Timer();
        isActive = true;
        if(announce)    {
            startAnnouncementTimer();
        }
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
        if(timer != null)    {
            timer.stop();
            timer.clear();
        }
        if(announcementTimer != null) {
            announcementTimer.stop();
            announcementTimer.clear();
        }


    }

    public void applyMechanic()  {
        System.out.println("Mechanic applied");
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
        if(timer != null)   {
            timer.stop();
            timer.delay((long)speed);
            timer.start();
        }
        this.speed = speed;
    }

    public void setMainTank()    {
        mainTank = owner.getMainTank();
    }

    public void setOffTank()    {
        offTank = owner.getOffTank();
    }

    public Raid getRaid() {
        return owner.getEnemies();
    }

    public void setRaid(Raid raid) {
        this.raid = raid;
    }

    public boolean isAnnounce() {
        return announce;
    }

    public void setAnnounce(boolean announce) {
        this.announce = announce;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(Boss owner) {
        this.owner = owner;
    }

    public RaidMember getMainTank() {
        return owner.getMainTank();
    }

    public void setMainTank(RaidMember mainTank) {
        this.mainTank = mainTank;
    }

    public RaidMember getOffTank() {
        return owner.getOffTank();
    }

    public void setOffTank(RaidMember offTank) {
        this.offTank = offTank;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAnnouncementString() {
        return announcementString;
    }

    public void setAnnouncementString(String announcementString) {
        this.announcementString = announcementString;
    }

    public Timer getAnnouncementTimer() {
        return announcementTimer;
    }

    public void setAnnouncementTimer(Timer announcementTimer) {
        this.announcementTimer = announcementTimer;
    }
}
