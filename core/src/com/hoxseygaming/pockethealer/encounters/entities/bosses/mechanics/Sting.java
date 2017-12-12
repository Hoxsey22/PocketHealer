package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.PoisonEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/21/2017.
 */

public class Sting extends Mechanic {

    public ArrayList<Poison> poisons;
    int numOfTargets;

    public Sting(Boss owner) {
        super("Sting", 20, 10f, owner);
        poisons = new ArrayList<>();
        numOfTargets = 1;
    }

    @Override
    public void start() {
        super.start();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ArrayList<RaidMember> temp  = owner.getEnemies().getRandomRaidMember(numOfTargets);

                for (int i = 0; i < temp.size(); i++)   {
                    if(temp.get(i) != null) {
                        temp.get(i).takeDamage(damage);
                        temp.get(i).addStatusEffect(new PoisonEffect(owner));
                        /*
                        Poison poison = new Poison(owner);
                        poison.setTarget(temp.get(i));
                        poisons.add(poison);
                        poison.start();
                        */
                    }
                }

            }
        },speed, speed);

    }

    @Override
    public void stop() {
        super.stop();
        for (int i = 0; i < poisons.size(); i++)  {
            poisons.get(i).stop();
        }
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
