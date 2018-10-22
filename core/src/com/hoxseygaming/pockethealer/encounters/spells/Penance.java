package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.ChannelCast;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Penance extends ChannelCast {

    public Penance(Player player, Assets assets) {
        super(player, "Penance",
                "Throws a volley of holy light at an ally unit.",
                8,
                2.5f,
                4,
                15,
                3f,
                8f,
                assets);
        setDescription("Heals an ally unit for "+getOutput()+"hp 4 times.");
        setImage(getAssets().getTexture(getAssets().penanceIcon));
    }

    @Override
    public void applySpell(RaidMember target) {
        if(!getOwner().getTalentTree().getTalent(TalentTree.DISCIPLINE).isSelected())    {
            target.receiveHealing(getOutput(), getCriticalChance().isCritical());
        }
        else {
            RaidMember lowest = getOwner().getRaid().getRaidMemberWithLowestHp();
            int newOutput = getOwner().getBoss().takeDamage(getOutput(), getCriticalChance().isCritical());

            if (getOwner().getTalentTree().getTalent(TalentTree.CRITICAL_HEALER_II).isSelected()) {
                applyCriticalHealerII(lowest, newOutput);
            }
            else {
                lowest.receiveHealing(newOutput, getCriticalChance().isCritical());
            }

            if(getOwner().getTalentTree().getTalent(TalentTree.DISCIPLINE).isSelected())    {
                applyAtonement(target);
            }

            if(getOwner().getTalentTree().getTalent(TalentTree.DISCIPLINE).isSelected())    {
                for (int i = 0; i < getOwner().getRaid().getRaidMembers().size(); i++) {
                    if(getOwner().getRaid().getRaidMembers().get(i).getStatusEffectList().contains("Atonement Effect")) {
                        getOwner().getRaid().getRaidMembers().get(i).receiveHealing((int)(newOutput*0.4f), getCriticalChance().isCritical());
                    }
                }
            }
        }


    }

    @Override
    public void checkTalents() {
        resetDefault();

        checkCriticalHealer();
        if(getOwner().getTalentTree().getTalent(TalentTree.HASTE_BUILD).isSelected())    {
            setCastTime(getMIN_CAST_TIME() - 0.25f);
            setTicksPerCast(5);
        }
    }
}
