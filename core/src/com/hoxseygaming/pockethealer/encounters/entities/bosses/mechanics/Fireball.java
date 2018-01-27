package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.BurnEffect;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hoxsey on 8/28/2017.
 */

public class Fireball extends Mechanic {

    private Random dice;

    public Fireball(Boss owner) {
        super("Fireball", 20, 2f, owner);
        dice = new Random();
    }

    public Fireball(Boss owner, float speed) {
        super("Fireball", 20, speed, owner);
        dice = new Random();
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ArrayList<RaidMember> random  = getRaid().getRandomRaidMember(1);
                random.get(0).takeDamage(damage);
                if(dice.nextInt(100)+0 > 95)    {
                    random.get(0).addStatusEffect(new BurnEffect(owner));
                }

            }
        },speed,speed);
    }
}
