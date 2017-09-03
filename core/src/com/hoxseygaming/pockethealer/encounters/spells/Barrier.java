package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.audio.Sound;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Barrier extends InstantCast {

    public Sound sfx;

    public Barrier(Player player, int index, Assets assets) {
        super(player,"Barrier", "An absorption shield.", EffectType.SHIELD, 1, 60, 35, 4f,assets.getSound(assets.barrierSFX), index,assets);
        image = this.assets.getTexture("barrier_icon.png");
    }

    @Override
    public void applySpell(RaidMember target) {
        target.applyShield(output);
    }
}
