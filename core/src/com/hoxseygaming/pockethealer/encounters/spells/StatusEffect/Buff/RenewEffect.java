package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff;

import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.spells.CriticalDice;

/**
 * Created by Hoxsey on 12/1/2017.
 */

public class RenewEffect extends Buff {
    /**
     * @param owner       : The player that owns this status effect.
     * @param duration    : The time of which the status effect will last.
     * @param speed       : The time of which the status effect will apply effect.
     * @param modValue    : The mod value that will change a specific stat.
     */
    public RenewEffect(Player owner, float duration, float speed, int modValue) {
        super(owner, 1, "Renew","Renew is a periodic heal.", owner.getAssets().getTexture(owner.getAssets().renewIcon), duration, speed, modValue, false);
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