package com.hoxseygaming.pockethealer.reformat.Spells;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Barrier extends Spell{

    public Barrier(int index) {
        super("Barrier", "An absorption shield.", EffectType.SHIELD, 60, 35, 4f, index);
    }

    public void applySpell()    {
        if(isCastable())    {
            startCooldownTimer();
            owner.getTarget().applyShield(output);
        }
    }
}
