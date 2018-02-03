package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.PrayerOfMendingEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Castable;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class PrayerOfMending extends Castable {

    public PrayerOfMending(Player player, int index, Assets assets) {
        super(player,
                "Prayer of Mending",
                "When the target takes damage, the target will be healed and Prayer of Mending",
                0,
                1.5f,
                EffectType.HEAL,
                35,
                2f,
                8,
                assets.getSound(assets.healSFX),
                index,
                assets);
        setImage(assets.getTexture(assets.prayerOfMendingIcon));
    }

    @Override
    public void applySpell(RaidMember target) {
        target.addStatusEffect(new PrayerOfMendingEffect(owner, 20));
    }


    @Override
    public void checkTalents() {
        resetDefault();

        checkCriticalHealer();
        checkHasteBuild();
    }
}
