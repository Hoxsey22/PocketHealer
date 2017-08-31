package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 8/31/2017.
 */

public class Smite extends Spell {

    /**
     * @param player
     * @param index
     * @param assets
     */
    public Smite(Player player, int index, Assets assets) {
        super(player, "Smite", "", EffectType.HEAL, 2, 5, 0.5f, index, assets);
    }
}
