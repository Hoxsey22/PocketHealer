package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 12/10/2017.
 */

public class PoisonEffect extends Debuff {

    public float healingReductionPercentage;


    /**
     * A debuff is a negative status effect that is commonly from a boss and is
     * put on a raid member.
     *
     * @param owner       : The owner of the buff.
     */
    public PoisonEffect(Boss owner) {
        super(owner,
                3,
                "Poison",
                "Poisons the target taking constant damage until dispel or poison wearing off.",
                owner.assets.getTexture(owner.assets.poisonIcon),
                20f,
                2f,
                15,
                true);
        healingReductionPercentage = 0.5f;
        setType(HEALING_REDUCTION);
    }

    /**
     *
     * @param owner       : The owner of the buff.
     * @param duration    : The time of which the status effect will last.
     * @param speed       : The time of which the status effect will apply effect.
     * @param modValue    : The mod value that will change a specific stat.
     * @param healingReductionPercentage    : The percentage of healing reduction.
     */
    public PoisonEffect(Boss owner, float duration, float speed, int modValue, float healingReductionPercentage) {
        super(owner,
                3,
                "Poison",
                "Poisons the target taking constant damage and reduces healing the target takes until dispel or until poison wears off.",
                owner.assets.getTexture(owner.assets.poisonIcon),
                duration,
                speed,
                modValue,
                true);
        this.healingReductionPercentage = healingReductionPercentage;
        setType(HEALING_REDUCTION);
    }

    /**
     *
     * @param owner       : The owner of the buff.
     * @param modValue    : The mod value that will change a specific stat.
     * @param healingReductionPercentage    : The percentage of healing reduction.
     */
    public PoisonEffect(Boss owner,int modValue, float healingReductionPercentage) {
        super(owner,
                3,
                "Poison",
                "Poisons the target taking constant damage and reduces healing the target takes until dispel or until poison wears off.",
                owner.assets.getTexture(owner.assets.poisonIcon),
                20f,
                2f,
                modValue,
                true);
        this.healingReductionPercentage = healingReductionPercentage;
        setType(HEALING_REDUCTION);
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
        return output - (int)((float)output*0.5f);
    }

    public void stopTimer() {
        getTimer().stop();
        getTimer().clear();
    }
}
