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
        setDescription("A flash of light that heals an ally unit and great speeds.");
        setImage(getAssets().getTexture(getAssets().flashIcon));
        setMIN_CAST_TIME(0.7f);
        setCostPercentage(2.2f);
        setCastTime(getMIN_CAST_TIME());
        setCriticalChance(getMIN_CRITICAL());
        setLevelRequirement(2);
    }
}
