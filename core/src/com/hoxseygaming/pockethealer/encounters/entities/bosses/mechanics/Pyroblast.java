package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.CriticalDice;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.BurnEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/4/2017.
 */

public class Pyroblast extends Mechanic {


    public Pyroblast(Boss owner) {
        super("Pyroblast", 50, 6f, owner);
        id = 8;
    }

    public Pyroblast(Boss owner, float speed) {
        super("Pyroblast", 50, speed, owner);
        id = 8;
    }

    @Override
    public void start() {
        super.start();
        startAnnouncementTimer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ArrayList<RaidMember> selected = getRaid().getRandomRaidMember(1);
                if(selected != null)    {
                    selected.get(0).takeDamage(damage);
                    if(CriticalDice.roll(35,100,0))    {
                        selected.get(0).addStatusEffect(new BurnEffect(owner));
                    }
                }
            }
        },speed,speed);
    }

    @Override
    public void stop() {
        super.stop();
    }
}
