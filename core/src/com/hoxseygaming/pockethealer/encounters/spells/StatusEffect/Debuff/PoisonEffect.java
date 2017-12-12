package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 12/10/2017.
 */

public class PoisonEffect extends Debuff {
    /**
     * A debuff is a negative status effect that is commonly from a boss and is
     * put on a raid member.
     *
     * @param owner       : The owner of the buff.
     */
    public PoisonEffect(Boss owner) {
        super(owner,
                2,
                "Poison",
                "Poisons the target taking constant damage until dispel or poison wearing off.",
                owner.assets.getTexture(owner.assets.poisonIcon),
                20f,
                2f,
                20,
                true);
        setType(HEALING_REDUCTION);
    }

    /**
     *
     * @param owner       : The owner of the buff.
     * @param duration    : The time of which the status effect will last.
     * @param speed       : The time of which the status effect will apply effect.
     * @param modValue    : The mod value that will change a specific stat.
     */
    public PoisonEffect(Boss owner, float duration, float speed, int modValue) {
        super(owner,
                2,
                "Poison",
                "Poisons the target taking constant damage and reduces healing the target takes until dispel or until poison wears off.",
                owner.assets.getTexture(owner.assets.poisonIcon),
                duration,
                speed,
                modValue,
                true);
        setType(HEALING_REDUCTION);
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
        return output - (int)((float)output*0.5f);
    }
}
