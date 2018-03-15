package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.audio.Sound;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Castable;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class HolyNova extends Castable {

    public Sound sfx;
    public final int MIN_NUMOFTARGETS = 3;

    public HolyNova(Player player, Assets assets) {
        super(player,
                "Holy Nova",
                "An explosion of holy light that heals several ally units for a moderate amount.",
                3,
                2f,
                25,
                3.5f,
                1f,assets.getSound(assets.healSFX),assets);
        image = assets.getTexture(assets.holyNovaIcon);
        sfx = assets.getSound(assets.hotSFX);
        numOfTargets = MIN_NUMOFTARGETS;
    }

    @Override
    public void checkTalents() {
        resetDefault();

        checkCriticalHealer();
        if(owner.getTalentTree().getTalent(TalentTree.SUPER_NOVA).isSelected())    {
            numOfTargets = 4;
        }
    }

    @Override
    public void applySpell(RaidMember target)    {
        // main tar
        getRandomTargets();

        if(owner.getTalentTree().getTalent(owner.getTalentTree().CRITICAL_HEALER_II).isSelected())    {
            for(int i = 0; i < targets.size(); i++) {
                applyCriticalHealerII(targets.get(i),output);
            }
            applyCriticalHealerII(target, output);

            for(int i = 0; i < targets.size(); i++) {
                applyAtonement(targets.get(i));
            }
            applyAtonement(target);
        }
        else if(owner.getTalentTree().getTalent(owner.getTalentTree().RENEWING_NOVA).isSelected())  {
            for(int i = 0; i < targets.size(); i++) {
                applyRenewingNova(targets.get(i));
                targets.get(i).receiveHealing(output, criticalChance.isCritical());
            }
            applyRenewingNova(target);
            target.receiveHealing(output, criticalChance.isCritical());
        }
        else if(owner.getTalentTree().getTalent(owner.getTalentTree().MASTERING_HEALING).isSelected())   {
            for(int i = 0; i < targets.size(); i++) {
                applyMasteringHealing(targets.get(i),output);
            }
            applyMasteringHealing(target, output);

        }
        else    {
            target.receiveHealing(output, criticalChance.isCritical());
            for(int i = 0; i < targets.size(); i++) {
                targets.get(i).receiveHealing(output, criticalChance.isCritical());
            }
        }


        System.out.println("checking renew");
    }

    @Override
    public void resetDefault()  {
        super.resetDefault();
        numOfTargets = MIN_NUMOFTARGETS;
    }
}
