package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.audio.Sound;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class HolyNova extends InstantCast {

    public Sound sfx;

    public HolyNova(Player player, int index,Assets assets) {
        super(player, "Holy Nova", "", EffectType.HEALMULTIPLE, 3, 30, 35, 3f,assets.getSound(assets.healSFX), index,assets);
        image = assets.getTexture(assets.holyNovaIcon);
        sfx = assets.getSound(assets.hotSFX);
        setCriticalChance(30);
    }

}
