package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.PoisonEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class PoisonSpit extends Mechanic {

    int numOfTargets;
    boolean poisoned;

    public PoisonSpit(Boss owner) {
        super("Poison Spit", 20, 5f, owner);
        numOfTargets = 3;
    }

    public PoisonSpit(Boss owner, float speed, int numOfTargets, boolean poisoned) {
        super("Poison Spit", 20, speed, owner);
        this.poisoned = poisoned;
        this.numOfTargets = numOfTargets;
    }

    @Override
    public void start() {
        super.start();


        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ArrayList<RaidMember> raidMembers = getRaid().getRandomRaidMember(numOfTargets);
                for(int i = 0; i < raidMembers.size(); i++)   {
                    raidMembers.get(i).takeDamage(damage);
                    if(poisoned)
                        raidMembers.get(i).addStatusEffect(new PoisonEffect(owner, 5, 0.2f));
                }
            }
        },speed, speed);
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
