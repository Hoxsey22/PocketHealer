package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 7/12/2017.
 */

public class Mechanic {

    public enum Debuff  {
        BLEED, SUNDER, SOULSTEAL,
        MAIME, HEALINGABSORB
    }

    public int id;
    public String name;
    public Boss owner;
    public Timer timer;
    public Debuff debuff;
    public RaidMember target;
    public int damage;
    public float speed;
    public int duration;
    public boolean isActive;


    public Mechanic(Boss owner)   {
        this.owner = owner;
        target = owner.target;
        isActive = false;
        create();
    }

    public void create()    {
        System.out.println("Mechanic created");
        timer = new Timer();
    }

    public void start()    {
        System.out.println("Timer started!");
        isActive = true;
    }

    public void stop()  {
        System.out.println("Timer stopped!");
        isActive = false;
        timer.stop();
        timer.clear();


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
}
