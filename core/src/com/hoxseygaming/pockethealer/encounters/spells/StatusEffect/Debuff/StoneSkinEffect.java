package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 12/10/2017.
 */

public class StoneSkinEffect extends Debuff {

    /**
     * A debuff is a negative status effect that is commonly from a boss and is
     * put on a raid member.
     *
     * @param owner       : The owner of the buff.
     */
    public StoneSkinEffect(Boss owner) {
        super(owner,
                3,
                "Stone Skin Effect",
                "Prevent a target from dying if target is above 10% health.",
                owner.assets.getTexture(owner.assets.stoneSkinIcon),
                600f,
                0.1f,
                0,
                false);
        setType(DAMAGE_AMPLIFIER);
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
        if(output >= getTarget().getHp())    {
            if(getTarget().getHealthPercent() > 0.1f)    {
                return getTarget().hp - 1;
            }
        }
        return output;
    }

}
