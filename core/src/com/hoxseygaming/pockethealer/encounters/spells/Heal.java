package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Castable;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Heal extends Castable {

    public ArrayList<Barrier> barriers;


    public Heal(Player player, int index, Assets assets) {
        super(player, "Heal","An efficient heal that heal an ally unit for a moderate amount.", 0,1.5f, EffectType.HEAL,
                40, 1f, 0.5f,assets.getSound(assets.healSFX), index, assets);
        setImage(assets.getTexture(assets.healIcon));
        barriers = new ArrayList<>();
    }

    @Override
    public void applySpell(RaidMember target) {
        int currentOutput = output;
        if(owner.holyShockIncrease)   {
            currentOutput = output + (int)(output*0.5);
            owner.holyShockIncrease = false;
        }

        if(owner.getTalentTree().getTalent(TalentTree.CRITICAL_HEALER_II).isSelected())    {
            applyCriticalHealerII(target, currentOutput);
            applyAtonement(target);
        }
        else if(owner.getTalentTree().getTalent(TalentTree.MASTERING_HEALING).isSelected())   {
            applyMasteringHealing(target, currentOutput);
        }
        else    {
            target.receiveHealing(currentOutput, criticalChance.isCritical());
        }

    }


    @Override
    public void checkTalents() {
        resetDefault();

        checkCriticalHealer();
        checkHasteBuild();
    }
}
