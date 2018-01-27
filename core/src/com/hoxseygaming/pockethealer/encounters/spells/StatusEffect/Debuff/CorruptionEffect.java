package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 11/29/2017.
 */

public class CorruptionEffect extends Debuff {

    /**
     */
    public CorruptionEffect(Boss owner) {
        super(owner,
                4,
                "Corruption Effect",
                "Agony will cause the target to take a huge amount of damage over time.",
                owner.assets.getTexture(owner.assets.corruptionIcon),
                400f,
                3f,
                10,
                true);
    }

    @Override
    public void startConditions() {

    }

    @Override
    public void additionalConditions() {
    }

    @Override
    public void applyEffect() {
        getTarget().takeDamage(getModValue());
    }

    @Override
    public int modifyOutput(int output) {
        return output;
    }
}
