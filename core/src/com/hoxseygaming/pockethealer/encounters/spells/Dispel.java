package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Types.InstantCast;

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
                "Dispels all dispellable debuffs from the targeted ally unit. ",
                0,
                EffectType.HEAL,
                1,
                0,
                1.5f,
                1f,
                assets.getSound(assets.healSFX),
                0,
                assets);
        setImage(assets.getTexture(assets.dispelIcon));
    }

    @Override
    public void applySpell(RaidMember target) {
        target.getStatusEffectList().dispel();
    }

    @Override
    public void checkTalents() {
    }
}
