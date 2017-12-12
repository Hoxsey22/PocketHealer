package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 11/29/2017.
 */

public class BurnEffect extends Debuff {

    /**
     */
    public BurnEffect(Boss owner) {
        super(owner,
                5,
                "BurnEffect",
                "Burn the target increasing in damage each tick and will remain until the target is over 90% health",
                owner.assets.getTexture(owner.assets.burnIcon), //need to change the icon
                300f,
                2f,
                10,
                false);
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
        setModValue(getModValue()+5);
    }

    @Override
    public int modifyOutput(int output) {
        return output;
    }
}
