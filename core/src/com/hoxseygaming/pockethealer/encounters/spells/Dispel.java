package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 12/6/2017.
 */

public class Dispel extends InstantCast {
    /**
     * @param player
     * @param assets
     */
    public Dispel(Player player, Assets assets) {
        super(player, "Dispel",
                "Dispels all magical effects off the target.",
                0,
                EffectType.HEAL,
                1,
                0,
                50,
                8f,
                assets.getSound(assets.healSFX),
                0,
                assets);
        setImage(assets.getTexture(assets.dispelIcon));
    }

    @Override
    public void applySpell(RaidMember target) {
        target.getStatusEffectList().dispel();
    }

    public void resetDefault()  {
    }


    @Override
    public void checkTalents() {
    }
}
