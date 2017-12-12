package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff;

import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 12/1/2017.
 */

public class BarrierEffect extends Buff {
    /**
     * @param owner       : The player that owns this status effect.
     */
    public BarrierEffect(Player owner) {
        super(owner, 1, "Barrier","An absorb shield.", owner.getAssets().getTexture(owner.getAssets().barrierIcon), 45f, 0.1f, 0, false);
    }

    @Override
    public void additionalConditions() {

    }

    @Override
    public void applyEffect() {
        if(getTarget().getShield() < 1)   {
            remove();
        }
    }

    @Override
    public int modifyOutput(int output) {
        return output;
    }
}
