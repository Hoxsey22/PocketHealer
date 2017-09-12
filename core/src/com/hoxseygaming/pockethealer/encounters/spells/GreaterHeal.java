package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class GreaterHeal extends Heal {


    public GreaterHeal(Player player, int index, Assets assets) {
        super(player, index, assets);
        name = "Greater Heal";
        setImage(assets.getTexture(assets.greaterHealerIcon));
        setCost(25);
        setCastTime(2f);
        MIN_CAST_TIME = 2f;
        setOutput(60);
    }
}
