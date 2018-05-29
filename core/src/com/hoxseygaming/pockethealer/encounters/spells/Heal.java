package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Castable;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Heal extends Castable {


    public Heal(Player player, Assets assets) {
        super(player,
                "Heal",
                "An efficient heal that heal an ally unit for a moderate amount.",
                0,
                1.5f,
                40,
                1f,
                0.5f,
                assets.getSound(assets.healSFX),
                assets);
        setImage(getAssets().getTexture(getAssets().healIcon));
    }

    @Override
    public void applySpell(RaidMember target) {
        int currentOutput = getOutput();
        System.out.println(getName()+"'s output: "+getOutput());
        if(getOwner().isHolyShockIncrease())   {
            currentOutput = getOutput() + (int)(getOutput()*0.5);
            getOwner().setHolyShockIncrease(false);
        }

        if(getOwner().getTalentTree().getTalent(TalentTree.CRITICAL_HEALER_II).isSelected())    {
            applyCriticalHealerII(target, currentOutput);
            applyAtonement(target);
        }
        else if(getOwner().getTalentTree().getTalent(TalentTree.MASTERING_HEALING).isSelected())   {
            applyMasteringHealing(target, currentOutput);
        }
        else    {
            target.receiveHealing(currentOutput, getCriticalChance().isCritical());
        }

    }


    @Override
    public void checkTalents() {
        resetDefault();

        checkCriticalHealer();
        checkHasteBuild();
    }
}
