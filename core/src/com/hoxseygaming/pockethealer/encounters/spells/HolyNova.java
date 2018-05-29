package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Castable;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class HolyNova extends Castable {

    public final int MIN_NUM_OF_TARGETS = 3;

    public HolyNova(Player player, Assets assets) {
        super(player,
                "Holy Nova",
                "An explosion of holy light that heals several ally units for a moderate amount.",
                5,
                2f,
                25,
                3.5f,
                1f,
                assets.getSound(assets.hotSFX),
                assets);
        setImage(getAssets().getTexture(getAssets().holyNovaIcon));
        setNumOfTargets(MIN_NUM_OF_TARGETS);
    }

    @Override
    public void checkTalents() {
        resetDefault();

        checkCriticalHealer();
        if(getOwner().getTalentTree().getTalent(TalentTree.SUPER_NOVA).isSelected())    {
            setNumOfTargets(5);
        }
    }

    @Override
    public void applySpell(RaidMember target)    {
        // main tar
        getRandomTargets();

        if(getOwner().getTalentTree().getTalent(getOwner().getTalentTree().CRITICAL_HEALER_II).isSelected())    {
            for(int i = 0; i < getTargets().size(); i++) {
                applyCriticalHealerII(getTargets().get(i),getOutput());
            }
            applyCriticalHealerII(target, getOutput());

            for(int i = 0; i < getTargets().size(); i++) {
                applyAtonement(getTargets().get(i));
            }
            applyAtonement(target);
        }
        else if(getOwner().getTalentTree().getTalent(getOwner().getTalentTree().RENEWING_NOVA).isSelected())  {
            for(int i = 0; i < getTargets().size(); i++) {
                applyRenewingNova(getTargets().get(i));
                getTargets().get(i).receiveHealing(getOutput(), getCriticalChance().isCritical());
            }
            applyRenewingNova(target);
            target.receiveHealing(getOutput(), getCriticalChance().isCritical());
        }
        else if(getOwner().getTalentTree().getTalent(getOwner().getTalentTree().MASTERING_HEALING).isSelected())   {
            for(int i = 0; i < getTargets().size(); i++) {
                applyMasteringHealing(getTargets().get(i),getOutput());
            }
            applyMasteringHealing(target, getOutput());

        }
        else    {
            target.receiveHealing(getOutput(), getCriticalChance().isCritical());
            for(int i = 0; i < getTargets().size(); i++) {
                getTargets().get(i).receiveHealing(getOutput(), getCriticalChance().isCritical());
            }
        }


        System.out.println("checking renew");
    }

    @Override
    public void resetDefault()  {
        super.resetDefault();
        setNumOfTargets(MIN_NUM_OF_TARGETS);
    }
}
