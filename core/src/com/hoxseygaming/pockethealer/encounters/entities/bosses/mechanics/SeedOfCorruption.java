package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.AudioManager;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.SeedOfCorruptionEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class SeedOfCorruption extends Mechanic {

    private int numOfTargets;

    public SeedOfCorruption(Boss owner) {
        super("Seed of Corruption", owner.getDamage(), 0.1f, owner);
        numOfTargets = 1;
        setAnnounce();
    }

    @Override
    public void action() {
        AudioManager.playSFX(getAssets().getSound(getAssets().debuffSFX), false);
        if(getOwner().getEnemies().getDebuffLessRaidMembers("Seed of Corruption Effect").size() == getOwner().getEnemies().getNumOfAlive()) {
            ArrayList<RaidMember> temp = getOwner().getEnemies().getRandomRaidMember(
                    numOfTargets,getOwner().getEnemies().getDebuffLessRaidMembers("Seed of Corruption Effect"));

            for (int i = 0; i < temp.size(); i++) {
                SeedOfCorruptionEffect seedOfCorruptionEffect = new SeedOfCorruptionEffect(getOwner());
                temp.get(i).addStatusEffect(seedOfCorruptionEffect);
            }
        }
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
