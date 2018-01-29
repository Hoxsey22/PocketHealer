package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.BarrierEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Castable;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Heal extends Castable {

    public ArrayList<Barrier> barriers;
    public boolean isSelectedCriticalHealerII;
    public boolean isSelectedResurgence;


    public Heal(Player player, int index, Assets assets) {
        super(player, "Heal","An efficient slow powerful single target heal.", 0,1.5f, EffectType.HEAL,
                40, 1f, 0.5f,assets.getSound(assets.healSFX), index, assets);
        setImage(assets.getTexture(assets.healIcon));
        isSelectedCriticalHealerII = false;
        isSelectedResurgence = false;
        barriers = new ArrayList<>();
    }

    @Override
    public void applySpell(RaidMember target) {
        int currentOutput = output;
        if(owner.holyShockIncrease)   {
            currentOutput = output + (int)(output*0.5);
            owner.holyShockIncrease = false;
        }

        if(criticalChance.isCritical()){

            if(isSelectedCriticalHealerII)  {
                target.applyShield((int)(currentOutput/2f));
                target.addStatusEffect(new BarrierEffect(owner));
            }

            if(isSelectedResurgence) {
                owner.mana = owner.getMana() + (int)(getCost()/2f);
            }
            target.receiveHealing(currentOutput, true);
        }
        else {
            target.receiveHealing(currentOutput, false);
        }

        if(owner.getTalentTree().getTalent(owner.getTalentTree().CRITICAL_HEALER_II).isSelected()) {
            ArrayList<RaidMember> smiteBuffedMembers = owner.getRaid().getStatusEffectedRaidMembers("Atonement Effect");

            for(int i = 0; i < smiteBuffedMembers.size(); i++)   {
                criticalHelper(smiteBuffedMembers.get(i), (int)((float)currentOutput*0.4f));
            }
        }

    }

    public void criticalHelper(RaidMember member, int output)    {
        if(criticalChance.isCritical()) {
            int newOutput = member.receiveHealing(output, true);
            if(isSelectedCriticalHealerII)    {
                if(isSelectedCriticalHealerII) {
                    member.applyShield((int)((float)newOutput/2f));
                    member.addStatusEffect(new BarrierEffect(owner));
                }

            }
        }
        else {
            member.receiveHealing(output, false);
        }
    }

    public void resetDefault()  {
        isSelectedResurgence = false;
        isSelectedCriticalHealerII = false;
        setCriticalChance(MIN_CRITICAL);
        setCastTime(MIN_CAST_TIME);
    }


    @Override
    public void checkTalents() {
        resetDefault();

        if(owner.getTalentTree().getTalent(TalentTree.CRITICAL_HEALER).isSelected())    {
            setCriticalChance(30);
        }
        if(owner.getTalentTree().getTalent(TalentTree.CRITICAL_HEALER_II).isSelected())    {
            isSelectedCriticalHealerII = true;
        }
        if(owner.getTalentTree().getTalent(TalentTree.HASTE_BUILD).isSelected())    {
            castTime = MIN_CAST_TIME - 0.25f;
        }
        if(owner.getTalentTree().getTalent(TalentTree.RESURGENCE).isSelected())    {
            isSelectedResurgence = true;
        }
    }
}
