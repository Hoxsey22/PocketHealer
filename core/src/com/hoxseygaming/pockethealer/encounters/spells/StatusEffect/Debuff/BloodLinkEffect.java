package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 11/29/2017.
 */

public class BloodLinkEffect extends Debuff {

    /**
     */
    public BloodLinkEffect(Boss owner) {
        super(owner,
                1,
                "Blood Link Effect",
                "A bleed will cause the target to take damage over time and all damage done to the target will be increased.",
                owner.assets.getTexture(owner.assets.bleedIcon),
                300f,
                2f,
                owner.damage,
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
    }

    @Override
    public int modifyOutput(int output) {
        return output;
    }
}
