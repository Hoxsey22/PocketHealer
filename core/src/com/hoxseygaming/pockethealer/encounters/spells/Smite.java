package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Castable;

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
        super(player, "Smite",
                "A spell that will inflict damage onto the boss and will heal the lowest raid member for a fourth of the damage done to the boss. If talented, " +
                        "40% of smite damage will heal all ally units affected by atonement.",
                5,
                0.5f,
                EffectType.DAMAGEHEAL,
                10,
                0.5f,
                0.5f,
                assets.getSound(assets.healSFX),
                index,
                assets);

        image = assets.getTexture(assets.smiteIcon);
    }

    @Override
    public void checkTalents() {
        resetDefault();

        checkCriticalHealer();

        if(owner.getTalentTree().getTalent(TalentTree.DISCIPLINE).isSelected())    {
            output = output + 2;
        }
        checkHasteBuild();
    }

    @Override
    public void applySpell(RaidMember target) {
        RaidMember lowest = owner.getRaid().getRaidMemberWithLowestHp();

        int newOutput = owner.getBoss().takeDamage(output, criticalChance.isCritical());

        if(owner.getTalentTree().getTalent(owner.getTalentTree().CRITICAL_HEALER_II).isSelected()) {
            applyCriticalHealerII(lowest, newOutput);
            triggerAtonement(newOutput);
        }
    }


}
