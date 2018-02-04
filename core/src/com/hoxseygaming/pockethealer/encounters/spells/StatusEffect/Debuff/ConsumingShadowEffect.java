package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 12/10/2017.
 */

public class ConsumingShadowEffect extends Debuff {

    /**
     * A debuff is a negative status effect that is commonly from a boss and is
     * put on a raid member.
     *
     * @param owner       : The owner of the buff.
     */
    public ConsumingShadowEffect(Boss owner) {
        super(owner,
                3,
                "Consuming Shadow Effect",
                "Consuming Shadow will increase all damage towards the target,",
                owner.assets.getTexture(owner.assets.swarmingShadowIcon),
                300f,
                0.1f,
                0,
                true);
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
        return output + (int)((float)output*0.7f);
    }

    public void stopTimer() {
        getTimer().stop();
        getTimer().clear();
    }
}
