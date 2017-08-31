package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class GreaterHeal extends Heal {


    public GreaterHeal(int position, Player player, Assets assets) {
        super(position, player, assets);
        name = "Greater Heal";
        image = assets.getTexture(assets.greaterHealerIcon);
        setCost(25);
        setCastTime(2f);
        setCriticalChance(20);
        setOutput(60);
    }

    @Override
    public void castSpell() {
        super.castSpell();
    }
}
