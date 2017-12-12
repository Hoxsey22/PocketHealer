package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.PoisonEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class PoisonBite extends Mechanic {

    int numOfTargets;

    public PoisonBite(Boss owner) {
        super("Poison Potion", 20, 2f, owner);
        numOfTargets = 1;
    }

    public PoisonBite(Boss owner, float speed) {
        super("Poison Potion", 20, speed, owner);
        numOfTargets = 1;
    }

    @Override
    public void start() {
        super.start();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ArrayList<RaidMember> temp = getRaid().getRandomRaidMember(numOfTargets);
                for(int i = 0; i < temp.size(); i++) {
                    temp.get(i).takeDamage(damage);
                    temp.get(i).addStatusEffect(new PoisonEffect(owner));
                    /*
                    Poison poison = new Poison(owner);
                    poison.setTarget(temp.get(i));
                    poison.start();
                    */
                }
            }
        },speed,speed);
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
