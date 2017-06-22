package com.hoxseygaming.pockethealer.reformat.spells;

import com.hoxseygaming.pockethealer.reformat.EncounterData;
import com.hoxseygaming.pockethealer.reformat.player.Player;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class FlashHeal extends Heal {


    public FlashHeal(int position, Player player) {
        super(position, player);
        name = "Flash Heal";
        image = EncounterData.flashIconImage;
        setCost(25);
        setCastTime(0.7f);
    }

    @Override
    public void castSpell() {
        super.castSpell();
    }
}
