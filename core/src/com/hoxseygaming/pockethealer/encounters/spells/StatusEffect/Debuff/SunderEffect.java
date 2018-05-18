package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 12/10/2017.
 */

public class SunderEffect extends Debuff {

    /**
     * A debuff is a negative status effect that is commonly from a boss and is
     * put on a raid member.
     *
     * @param owner       : The owner of the buff.
     */
    public SunderEffect(Boss owner) {
        super(owner,
                3,
                "Sunder Effect",
                "Ally unit's armor is heavily damaged and will take addition damage.",
                owner.assets.getTexture(owner.assets.sunderIcon),
                14f,
                14f,
                0,
                false);
        setType(DAMAGE_AMPLIFIER);
    }

    /**
     * A debuff is a negative status effect that is commonly from a boss and is
     * put on a raid member.
     *
     * @param owner       : The owner of the buff.
     */
    public SunderEffect(Boss owner, float duration) {
        super(owner,
                3,
                "Sunder Effect",
                "Ally unit's armor is heavily damaged and will take addition damage.",
                owner.assets.getTexture(owner.assets.sunderIcon),
                duration,
                duration,
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