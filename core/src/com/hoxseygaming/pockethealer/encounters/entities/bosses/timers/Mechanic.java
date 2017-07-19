package com.hoxseygaming.pockethealer.encounters.entities.bosses.timers;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 7/12/2017.
 */

public class Mechanic {

    public enum Debuff  {
        BLEED, SUNDER;
    }

    public int id;
    public Boss owner;
    public Timer timer;


    public Mechanic(int id, Boss owner)   {
        this.id = id;
        this.owner = owner;
        timer = new Timer();
    }

    public void startTimer()    {
        System.out.println("Timer started!");
    }

    public void applyMechanic()  {
        System.out.println("Mechanic applied");
    }



}
