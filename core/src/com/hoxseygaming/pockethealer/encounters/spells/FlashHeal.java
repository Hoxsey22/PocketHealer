package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class FlashHeal extends Heal {


    public FlashHeal(Player player,int index, Assets assets) {
        super(player, index, assets);
        name = "Flash Heal";
        setImage(assets.getTexture(assets.flashIcon));
        setCost(25);
        setCastTime(0.7f);
        MIN_CAST_TIME = 0.7f;
        setCriticalChance(player.criticalChance);
    }
}
