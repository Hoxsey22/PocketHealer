package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.InstantCast;

/**
 * Created by Hoxsey on 12/6/2017.
 */

public class HolyShock extends InstantCast {
    /**
     * @param player
     * @param assets
     */
    public HolyShock(Player player, Assets assets) {
        super(player, "Holy Shock",
                "Instantly shocks an ally unit with holy light for a moderate amount and also empowers the next Heal by 50%.",
                0,
                EffectType.HEAL,
                1,
                35,
                2f,
                12f,
                assets.getSound(assets.healSFX),
                0,
                assets);

        setImage(assets.getTexture(assets.criticalHealer2Icon));
    }

    @Override
    public void applySpell(RaidMember target) {

        if(owner.getTalentTree().getTalent(TalentTree.CRITICAL_HEALER_II).isSelected())    {
            applyCriticalHealerII(target, output);
        }
        else if(owner.getTalentTree().getTalent(owner.getTalentTree().MASTERING_HEALING).isSelected())   {
            applyMasteringHealing(target, output);
        }
        else    {
            target.receiveHealing(output, criticalChance.isCritical());
        }

        owner.holyShockIncrease = true;

    }


    @Override
    public void checkTalents() {
        resetDefault();

        checkCriticalHealer();
    }
}
