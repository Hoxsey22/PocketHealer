package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.EncounterData;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class FlashHeal extends Heal {


    public FlashHeal(int position, Player player, Assets assets) {
        super(position, player, assets);
        name = "Flash Heal";
        image = this.assets.getTexture("flash_heal_icon.png");
        setCost(25);
        setCastTime(0.7f);
        setCriticalChance(20);
    }

    @Override
    public void castSpell() {
        super.castSpell();
    }
}
