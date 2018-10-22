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
                6,
                1.25f,
                5,
                0.5f,
                0.5f,
                assets.getSound(assets.healSFX),
                assets);
        setDescription("Damages the enemy and heals an ally unit with the lowest health for the damage dealt to the enemy.");
        setImage(getAssets().getTexture(getAssets().smiteIcon));
    }

    @Override
    public void checkTalents() {
        resetDefault();

        checkCriticalHealer();

        if(getOwner().getTalentTree().getTalent(TalentTree.DISCIPLINE).isSelected())    {
            setOutput(getOutput()+5);
        }
        checkHasteBuild();
    }

    @Override
    public void applySpell(RaidMember target) {
        RaidMember lowest = getOwner().getRaid().getRaidMemberWithLowestHp();

        int newOutput = getOwner().getBoss().takeDamage(getOutput(), getCriticalChance().isCritical());

        if(getOwner().getTalentTree().getTalent(TalentTree.CRITICAL_HEALER_II).isSelected()) {
            applyCriticalHealerII(lowest, newOutput);
        }
        else {
            lowest.receiveHealing(newOutput,getCriticalChance().isCritical());
        }

        if(getOwner().getTalentTree().getTalent(TalentTree.DISCIPLINE).isSelected())    {
            for (int i = 0; i < getOwner().getRaid().getRaidMembers().size(); i++) {
                if(getOwner().getRaid().getRaidMembers().get(i).getStatusEffectList().contains("Atonement Effect")) {
                    getOwner().getRaid().getRaidMembers().get(i).receiveHealing(newOutput, getCriticalChance().isCritical());
                }
            }
        }
    }


}
