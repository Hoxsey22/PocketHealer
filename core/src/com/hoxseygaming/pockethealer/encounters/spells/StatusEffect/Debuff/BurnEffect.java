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
                2,
                "Burn Effect",
                "Dispellable: Burns the target for moderate damage, but very quickly over time.",
                owner.assets.getTexture(owner.assets.burnIcon), //need to change the icon
                10f,
                1f,
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
        //setModValue(getModValue()+5);
    }

    @Override
    public int modifyOutput(int output) {
        return output;
    }
}
