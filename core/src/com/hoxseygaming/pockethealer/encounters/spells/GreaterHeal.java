package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class GreaterHeal extends Heal {


    public GreaterHeal(Player player, int index, Assets assets) {
        super(player, index, assets);
        setName("Greater Heal");
        setDescription("A very strong heal, but very slow cast time. Should be used to preemptively heal a target taking heavy damage. ");
        setImage(assets.getTexture(assets.greaterHealerIcon));
        setCost(25);
        setCastTime(2f);
        MIN_CAST_TIME = 2f;
        setOutput(60);
        levelRequirement = 4;
    }
}
