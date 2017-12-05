package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 11/29/2017.
 */

public class BleedEffect extends Debuff {

    /**
     */
    public BleedEffect(Boss owner) {
        super(owner, 5, "BleedEffect","BleedEffect damages a target over time and will increase until target is above 90% hp.", owner.assets.getTexture(owner.assets.bleedIcon), 1f, 2f, 5, false);
    }

    @Override
    public void additionalConditions() {

    }

    @Override
    public void applyEffect() {
        getTarget().takeDamage(getModValue());
        setModValue(getModValue()+5);
    }
}
