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
                "Corruption will cause the target to take a moderate amount of damage over time.",
                owner.assets.getTexture(owner.assets.corruptionIcon),
                400f,
                3f,
                10,
                false);
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
