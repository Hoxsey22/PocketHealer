package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class GreaterHeal extends Heal {

    public GreaterHeal(Player player, Assets assets) {
        super(player, assets);
        setName("Greater Heal");
        setDescription("A focused powerful heal that heals an ally unit for a massive amount.");
        setImage(getAssets().getTexture(getAssets().greaterHealerIcon));
        setCostPercentage(2.2f);
        setMIN_CAST_TIME(2f);
        setCastTime(getMIN_CAST_TIME());
        setMIN_OUTPUT(60);
        setOutput(60);
        setLevelRequirement(6);
    }
}
