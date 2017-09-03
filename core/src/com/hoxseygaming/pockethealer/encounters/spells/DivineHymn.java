package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class DivineHymn extends ChannelCast {

    public DivineHymn(Player player, int index, Assets assets) {
        super(player, "Divine Hymn", "", 4f, 5, EffectType.HEALALL, 25, 150, 45f, index, assets);
        image = assets.getTexture(assets.divineHymnIcon);
        setCriticalChance(0);
    }

}
