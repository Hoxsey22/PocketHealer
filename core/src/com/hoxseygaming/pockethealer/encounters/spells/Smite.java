package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 8/31/2017.
 */

public class Smite extends Castable {

    /**
     * @param player
     * @param index
     * @param assets
     */
    public Smite(Player player, int index, Assets assets) {
        super(player, "Smite", "", 0.5f, EffectType.DAMAGEHEAL, 5, 5, 0.5f, assets.getSound(assets.healSFX), index, assets);
        image = assets.getTexture(assets.smiteIcon);
    }

    @Override
    public void applySpell(RaidMember target) {
        owner.getBoss().takeDamage(output);
        owner.raid.getRaidMemberWithLowestHp().receiveHealing(output);
    }
}
