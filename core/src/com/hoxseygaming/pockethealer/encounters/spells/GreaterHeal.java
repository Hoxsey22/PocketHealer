package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 6/18/2017.
 */
class GreaterHeal extends Heal {

    public GreaterHeal(Player player, Assets assets) {
        super(player, assets);
        setName("Greater Heal");
        setOutput(60);

        setMIN_OUTPUT(60);

        setDescription("Heals an ally unit for "+getOutput()+"hp.");

        setImage(getAssets().getTexture(getAssets().greaterHealerIcon));

        setCostPercentage(2.2f);

        setMIN_COST(2.2f);

        setMIN_CAST_TIME(2f);

        setCastTime(getMIN_CAST_TIME());

        setLevelRequirement(6);
    }
}
