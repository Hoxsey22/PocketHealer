package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 11/29/2017.
 */

public class BleedEffect extends Debuff {

    /**
     */
    public BleedEffect(Boss owner) {
        super(owner,
                5,
                "BleedEffect",
                "A bleed will cause the target to take damage over time and all damage done to the target will be increased.",
                owner.assets.getTexture(owner.assets.bleedIcon),
                300f,
                2f,
                10,
                false);
        setType(DAMAGE_AMPLIFIER);
    }

    @Override
    public void additionalConditions() {
        if(getTarget().getHealthPercent() > 0.89)   {
            remove();
        }
    }

    @Override
    public void applyEffect() {
        getTarget().takeDamage(getModValue());
        //setModValue(getModValue()+5);
    }

    @Override
    public int modifyOutput(int output) {
        return output + (int)((float)output*0.2f);
    }
}
