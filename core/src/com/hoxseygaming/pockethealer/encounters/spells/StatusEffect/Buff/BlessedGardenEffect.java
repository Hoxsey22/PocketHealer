package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff;

import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.spells.CriticalDice;

/**
 * Created by Hoxsey on 12/1/2017.
 */

public class BlessedGardenEffect extends Buff {
    /**
     * @param owner       : The player that owns this status effect.
     */
    public BlessedGardenEffect(Player owner) {
        super(owner,
                1,
                "Blessed Garden Effect",
                "Renew is a periodic heal.",
                owner.getAssets().getTexture(owner.getAssets().renewIcon),
                5f,
                1f,
                12,
                false);
    }

    @Override
    public void startConditions() {

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
