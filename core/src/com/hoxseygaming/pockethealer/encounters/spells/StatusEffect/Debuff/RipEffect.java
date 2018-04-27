package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 12/10/2017.
 */

public class RipEffect extends Debuff {

    /**
     * A debuff is a negative status effect that is commonly from a boss and is
     * put on a raid member.
     *
     * @param owner       : The owner of the buff.
     */
    public RipEffect(Boss owner) {
        super(owner,
                3,
                "Rip Effect",
                "Rip the target causing them to take periodic damage and increase damage taken.",
                owner.assets.getTexture(owner.assets.ripIcon),
                12f,
                2f,
                5,
                false);
        setType(DAMAGE_AMPLIFIER);
    }

    /**
     * A debuff is a negative status effect that is commonly from a boss and is
     * put on a raid member.
     *
     * @param owner       : The owner of the buff.
     */
    public RipEffect(Boss owner, float duration) {
        super(owner,
                3,
                "Rip Effect",
                "Rip the target causing them to take periodic damage and increase damage taken.",
                owner.assets.getTexture(owner.assets.ripIcon),
                duration,
                2f,
                10,
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
        getTarget().takeDamage(getModValue());
    }
    @Override
    public int modifyOutput(int output) {
        return output + (int)((float)output*0.5f);
    }



    public void stopTimer() {
        getTimer().stop();
        getTimer().clear();
    }
}
