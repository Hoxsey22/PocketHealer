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
     * @param assets
     */
    public Smite(Player player, Assets assets) {
        super(player, "Smite",
                "Smites a boss for a small amount of damage that will heal the most injured ally unit for a faction of the damage.",
                72,
                1.25f,
                5,
                0.5f,
                0.5f,
                assets.getSound(assets.healSFX),
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
        else {
            lowest.receiveHealing(newOutput,criticalChance.isCritical());
        }
    }


}
