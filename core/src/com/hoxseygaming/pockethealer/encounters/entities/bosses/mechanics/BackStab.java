package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.BleedEffect;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.PoisonEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class BackStab extends Mechanic {

    public ArrayList<Bleed> bleeds;
    public int numOfTargets;

    public BackStab(Boss owner) {
        super("Back Stab", owner.damage*3, 10f, owner);
        bleeds = new ArrayList<>();
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
                    if(temp.get(i) != null) {
                        PoisonEffect poisonEffect = new PoisonEffect(owner);
                        poisonEffect.setModValue(0);

                        temp.get(i).takeDamage(damage);
                        temp.get(i).addStatusEffect(new BleedEffect(owner));
                        temp.get(i).addStatusEffect(poisonEffect);
                    }
                }

            }
        },speed, speed);
    }

    @Override
    public void stop() {
        super.stop();
        for(int i = 0; i < bleeds.size(); i++)   {
            bleeds.get(i).stop();
        }
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
