package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.PrayerOfMendingEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Castable;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class PrayerOfMending extends Castable {

    public PrayerOfMending(Player player, Assets assets) {
        super(player,
                "Prayer of Mending",
                "A ward is placed on an ally unit that heals the ally unit when damaged and will jump to a new target.",
                5,
                1.5f,
                35,
                2f,
                8,
                assets.getSound(assets.healSFX),
                assets);
        setImage(assets.getTexture(assets.prayerOfMendingIcon));
        numOfTargets = 5;
    }

    @Override
    public void applySpell(RaidMember target) {
        target.addStatusEffect(new PrayerOfMendingEffect(owner, 20, numOfTargets));
    }

    @Override
    public void resetDefault() {
        super.resetDefault();
        numOfTargets = 5;
    }

    @Override
    public void checkTalents() {
        resetDefault();

        checkCriticalHealer();
        checkHasteBuild();
    }

    public void checkSuperNova()    {
        if(owner.getTalentTree().getTalent(TalentTree.SUPER_NOVA).isSelected())    {
            numOfTargets = 6;
        }
    }
}
