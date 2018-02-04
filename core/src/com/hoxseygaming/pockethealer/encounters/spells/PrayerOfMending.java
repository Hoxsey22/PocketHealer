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

    public PrayerOfMending(Player player, int index, Assets assets) {
        super(player,
                "Prayer of Mending",
                "When an ally unit has this buff, taking damage will heal the unit for a moderate " +
                        "amount. Once healed, the buff will jump to the next lowest ally. This will occur 5 times or" +
                        " 6 if talented.",
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
