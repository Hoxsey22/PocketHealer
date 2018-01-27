package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.SeedOfCorruptionEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class SeedOfCorruption extends Mechanic {

    public int numOfTargets;

    public SeedOfCorruption(Boss owner) {
        super("Seed of Corruption", owner.damage, 0.1f, owner);
        numOfTargets = 1;
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if(owner.getEnemies().getDebuffLessRaidMembers("Seed of Corruption Effect").size() == owner.getEnemies().raidMembers.size()) {
                    ArrayList<RaidMember> temp = owner.enemies.getRandomRaidMember(numOfTargets);

                    for (int i = 0; i < temp.size(); i++) {
                        temp.get(i).addStatusEffect(new SeedOfCorruptionEffect(owner));
                    }
                }

            }
        },5f, speed);
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
