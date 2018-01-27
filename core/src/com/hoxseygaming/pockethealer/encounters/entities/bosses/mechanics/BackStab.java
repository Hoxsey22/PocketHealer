package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.BleedEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class BackStab extends Mechanic {

    public int numOfTargets;

    public BackStab(Boss owner) {
        super("Back Stab", owner.damage*3, 15f, owner);
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
                    BleedEffect bleedEffect = new BleedEffect(owner);
                    bleedEffect.setModValue(5);

                    temp.get(i).takeDamage(damage);
                    temp.get(i).addStatusEffect(bleedEffect);
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
