package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.EncounterData;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Barrier extends Spell {

    public Barrier(int index, Player player) {
        super("Barrier", "An absorption shield.", EffectType.SHIELD, 60, 35, 4f, index);
        owner = player;
        image = EncounterData.barrierIconImage;
    }

    @Override
    public void castSpell() {
        if(isCastable())    {
            useMana();
            applySpell();
        }
    }

    public void applySpell()    {
        startCooldownTimer();
        owner.getTarget().applyShield(output);
    }
}
