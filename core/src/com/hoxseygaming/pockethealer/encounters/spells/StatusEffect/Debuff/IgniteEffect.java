package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 11/29/2017.
 */

public class IgniteEffect extends Debuff {

    int count = 0;
    /**
     */
    public IgniteEffect(Boss owner) {
        super(owner,
                2,
                "Ignite Effect",
                "Burns the target until dispelled. Once dispelled, the target will combust.",
                owner.assets.getTexture(owner.assets.igniteIcon), //need to change the icon
                10f,
                2f,
                10,
                true);
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
        count++;
        //setModValue(getModValue()+5);
    }

    @Override
    public int modifyOutput(int output) {
        return output;
    }

    @Override
    public void remove() {
        if(!getTarget().isDead())
            getTarget().takeDamage((6-count)*getModValue()/2);
        super.remove();
    }
}
