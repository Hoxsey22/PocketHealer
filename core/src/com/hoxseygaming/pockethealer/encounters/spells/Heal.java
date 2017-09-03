package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Heal extends Castable {

    public Heal(Player player, int index, Assets assets) {
        super(player, "Heal","An efficient slow powerful single target heal.",1.5f, EffectType.HEAL,
                40, 10, 0.5f,assets.getSound(assets.healSFX), index, assets);
        setImage(assets.getTexture(assets.healIcon));
        setCriticalChance(10);
    }


}
