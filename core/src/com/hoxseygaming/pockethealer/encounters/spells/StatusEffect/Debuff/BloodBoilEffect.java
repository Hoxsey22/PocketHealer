package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 11/29/2017.
 */

public class BloodBoilEffect extends Debuff {

    /**
     */
    public BloodBoilEffect(Boss owner) {
        super(owner,
                5,
                "Blood Boil Effect",
                "Blood Boil was do little raid damage when the target is healed to full.",
                owner.assets.getTexture(owner.assets.boilIcon),
                20f,
                0.1f,
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
        if(getTarget().getHealthPercent() > 0.99)    {
            getOwner().getEnemies().takeDamage(getModValue());
        }
    }

    @Override
    public int modifyOutput(int output) {
        return output;
    }
}
