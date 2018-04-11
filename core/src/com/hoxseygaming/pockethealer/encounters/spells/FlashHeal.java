package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class FlashHeal extends Heal {


    public FlashHeal(Player player, Assets assets) {
        super(player, assets);
        setName("Flash Heal");
        setDescription("A flash of light that heals an ally unit and great speeds.");
        setImage(assets.getTexture(assets.flashIcon));
        MIN_CAST_TIME = 0.7f;
        setCostPercentage(2.2f);
        setCastTime(MIN_CAST_TIME);
        setCriticalChance(MIN_CRITICAL);
        levelRequirement = 2;
    }
}
