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
        setDescription("Grants an ally unit a buff that will heal the ally unit with this buff for "+getOutput()+"hp and jumps to the most injured ally unit and does this 5 times.");
        setImage(getAssets().getTexture(getAssets().prayerOfMendingIcon));
        setNumOfTargets(5);
    }

    @Override
    public void applySpell(RaidMember target) {
        target.addStatusEffect(new PrayerOfMendingEffect(getOwner(), 20, getNumOfTargets()));
    }

    @Override
    public void resetDefault() {
        super.resetDefault();
        setNumOfTargets(5);
    }

    @Override
    public void checkTalents() {
        resetDefault();

        checkCriticalHealer();
        checkHasteBuild();
    }

    public void checkSuperNova()    {
        if(getOwner().getTalentTree().getTalent(TalentTree.SUPER_NOVA).isSelected())    {
            setNumOfTargets(6);
        }
    }
}
