package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.PoisonEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class PoisonStab extends Mechanic {

    public int numOfTargets;

    public PoisonStab(Boss owner) {
        super("Poison Stab", owner.damage, 18f, owner);
        numOfTargets = 1;
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ArrayList<RaidMember> temp  = owner.enemies.getRandomRaidMember(numOfTargets);

                for (int i = 0; i < temp.size(); i++)   {
                    temp.get(i).takeDamage(damage);
                    temp.get(i).addStatusEffect(new PoisonEffect(owner, 5,0.2f));
                }

            }
        },speed, speed);
    }

    @Override
    public void stop() {
        super.stop();
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
