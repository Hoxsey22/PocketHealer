package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect;

import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.spells.CriticalDice;

/**
 * Created by Hoxsey on 12/1/2017.
 */

public class LifeboomEffect extends Buff {

    private int totalBoom;
    /**
     * @param owner       : The player that owns this status effect.
     * @param duration    : The time of which the status effect will last.
     * @param speed       : The time of which the status effect will apply effect.
     * @param modValue    : The mod value that will change a specific stat.
     */
    public LifeboomEffect(Player owner, float duration, float speed, int modValue) {
        super(owner, 1, "Lifeboom","Renew is a periodic heal.", owner.getAssets().getTexture(owner.getAssets().lifeboomIcon), duration, speed, modValue, false);
    }

    @Override
    public void additionalConditions() {

    }

    @Override
    public void applyEffect() {
        totalBoom =+ getTarget().receiveHealing(getModValue(), CriticalDice.roll(getOwner().criticalChance));
    }

    @Override
    public void remove() {
        getTarget().receiveHealing((int)((float)totalBoom/3f), CriticalDice.roll(getOwner().criticalChance));
        super.remove();
    }
}
