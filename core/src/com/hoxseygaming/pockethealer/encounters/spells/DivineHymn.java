package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.ChannelCast;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class DivineHymn extends ChannelCast {

    public DivineHymn(Player player, Assets assets) {
        super(player, "Divine Hymn",
                "A glorious hymn is rang throughout the raid, healing all ally units several times.",
                7,
                4f,
                4,
                20,
                5f,
                100f,
                assets);
        image = assets.getTexture(assets.divineHymnIcon);
    }

    @Override
    public void applySpell(RaidMember target) {
        ArrayList<RaidMember> randoms = owner.getRaid().getRaidMembersWithLowestHp(8/*(int)(owner.getRaid().raidMembers.size()*0.7f)*/);

        for(int i = 0; i < randoms.size(); i++)   {
            if(owner.getTalentTree().getTalent(TalentTree.MASTERING_HEALING).isSelected())   {
                applyMasteringHealing(randoms.get(i), output);
            }
            else {
                randoms.get(i).receiveHealing(output, criticalChance.isCritical());
            }
        }
    }

    @Override
    public void useMana() {
        if (owner.getTalentTree().getTalent(TalentTree.HOLY_FOCUS).isSelected())
            owner.receiveMana(getCost());
        else
            super.useMana();
    }

    @Override
    public void checkTalents() {
    }
}
