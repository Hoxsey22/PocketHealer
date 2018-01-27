package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 11/29/2017.
 */

public class SeedOfCorruptionEffect extends Debuff {

    public int numOfTargets;
    /**
     */
    public SeedOfCorruptionEffect(Boss owner) {
        super(owner,
                6,
                "Seed of Corruption Effect",
                "Seed of Corruption will do progressive damage until dispelled. Once dispelled, " +
                        "a few raid members will take explosion damage and spread corruption to two other " +
                        "raid members.",
                owner.assets.getTexture(owner.assets.seedOfCorruptionIcon),
                300f,
                1.5f,
                2,
                true);

        numOfTargets = 2;
    }

    @Override
    public void startConditions() {

    }

    @Override
    public void remove() {
        super.remove();
        ArrayList<RaidMember> group = getRandomGroup();

        getOwner().getEnemies().takeDamage(15);

        for(int i = 0; i < group.size(); i++)   {
            CorruptionEffect corruptionEffect = new CorruptionEffect(getOwner());
            corruptionEffect.setModValue(7);

            group.get(i).addStatusEffect(corruptionEffect);
        }

    }

    @Override
    public void additionalConditions() {
    }

    @Override
    public void applyEffect() {
        getTarget().takeDamage(getModValue());
        setModValue(getModValue()+2);
    }

    @Override
    public int modifyOutput(int output) {
        return output;
    }

    public ArrayList<RaidMember> getRandomGroup()   {
        return getOwner().getEnemies().getRandomRaidMember(numOfTargets,getOwner().getEnemies().getDebuffLessRaidMembers(this.getName()));
    }
}
