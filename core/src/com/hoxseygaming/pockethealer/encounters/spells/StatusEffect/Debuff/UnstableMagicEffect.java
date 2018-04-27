package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 11/29/2017.
 */

public class UnstableMagicEffect extends Debuff {

    /**
     */
    public UnstableMagicEffect(Boss owner) {
        super(owner,
                5,
                "Unstable Magic Effect",
                "A surge of uncontrollable magic increases damage by 300%, but greatly injuries the user.",
                owner.assets.getTexture(owner.assets.perseveranceIcon),
                600f,
                2f,
                10,
                false);
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

    @Override
    public void startConditions() {
        getTarget().setDamage(getTarget().getDamage() * 3);
    }
}
