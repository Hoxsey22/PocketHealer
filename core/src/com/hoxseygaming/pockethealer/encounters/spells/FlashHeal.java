package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 6/18/2017.
 */
class FlashHeal extends Heal {


    public FlashHeal(Player player, Assets assets) {
        super(player, assets);
        setName("Flash Heal");
        setDescription("Heals an ally unit quickly for "+getOutput()+"hp, but at the cost of more mana.");
        setImage(getAssets().getTexture(getAssets().flashIcon));
        setMIN_CAST_TIME(0.7f);
        setCostPercentage(2.2f);
        setCastTime(getMIN_CAST_TIME());
        setCriticalChance(getMIN_CRITICAL());
        setLevelRequirement(2);
    }
}
