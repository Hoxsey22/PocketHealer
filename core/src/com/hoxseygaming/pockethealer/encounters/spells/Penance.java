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
                "A volley of holy light.",
                7,
                2.5f,
                4,
                EffectType.HEALALL,
                15,
                3f,
                8f,
                0,
                assets);
        image = assets.getTexture(assets.penanceIcon);
    }

    @Override
    public void applySpell(RaidMember target) {
        if(!owner.getTalentTree().getTalent(TalentTree.DISCIPLINE).isSelected())    {
            target.receiveHealing(output, criticalChance.isCritical());
        }
        else {
            RaidMember lowest = owner.getRaid().getRaidMemberWithLowestHp();
            int newOutput = owner.getBoss().takeDamage(output, criticalChance.isCritical());

            if (owner.getTalentTree().getTalent(TalentTree.CRITICAL_HEALER_II).isSelected()) {
                applyCriticalHealerII(lowest, newOutput);
                triggerAtonement(newOutput);
            }
        }
    }

    @Override
    public void checkTalents() {
        resetDefault();

        checkCriticalHealer();
        if(owner.getTalentTree().getTalent(TalentTree.HASTE_BUILD).isSelected())    {
            castTime = MIN_CAST_TIME - 0.25f;
            ticksPerCast = 5;
        }
    }
}
