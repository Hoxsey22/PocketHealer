package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff;

import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.spells.CriticalDice;

/**
 * Created by Hoxsey on 12/1/2017.
 */

public class RenewingNovaEffect extends Buff {
    /**
     * @param owner       : The player that owns this status effect.
     */
    public RenewingNovaEffect(Player owner) {
        super(owner, 1, "Renewing Nova","A periodic heal.", owner.getAssets().getTexture(owner.getAssets().renewIcon), 6f, 2f, 5, false);
    }

    /**
     * @param owner       : The player that owns this status effect.
     * @param duration    : The time of which the status effect will last.
     * @param speed       : The time of which the status effect will apply effect.
     * @param modValue    : The mod value that will change a specific stat.
     */
    public RenewingNovaEffect(Player owner, float duration, float speed, int modValue) {
        super(owner, 1, "Renewing Nova","A periodic heal.", owner.getAssets().getTexture(owner.getAssets().renewIcon), duration, speed, modValue, false);
    }

    @Override
    public void additionalConditions() {

    }

    @Override
    public void applyEffect() {
        getTarget().receiveHealing(getModValue(), CriticalDice.roll(getOwner().criticalChance));
    }

    @Override
    public int modifyOutput(int output) {
        return output;
    }
}