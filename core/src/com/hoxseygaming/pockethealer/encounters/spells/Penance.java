package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.BarrierEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.ChannelCast;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Penance extends ChannelCast {

    public boolean isSelectedCriticalHealerII;
    public boolean isSelectedResurgence;
    private boolean isSelectedDiscipline;

    public Penance(Player player, Assets assets) {
        super(player, "Penance",
                "A volley of holy light.",
                7,
                2.5f,
                4,
                EffectType.HEALALL,
                25,
                4f,
                6f,
                0,
                assets);
        image = assets.getTexture(assets.penanceIcon);
    }

    @Override
    public void applySpell(RaidMember target) {

        RaidMember lowest = owner.getRaid().getRaidMemberWithLowestHp();
        int newOutput = owner.getBoss().takeDamage(output, criticalChance.isCritical());
        criticalHelper(lowest, newOutput);


        if(owner.getTalentTree().getTalent(owner.getTalentTree().CRITICAL_HEALER_II).isSelected()) {
            ArrayList<RaidMember> smiteBuffedMembers = owner.getRaid().getStatusEffectedRaidMembers("Atonement Effect");

            for(int i = 0; i < smiteBuffedMembers.size(); i++)   {
                criticalHelper(smiteBuffedMembers.get(i), (int)((float)newOutput*0.4f));
            }
        }



        /*
        if(criticalChance.isCritical()) {
            if(isSelectedResurgence){
                owner.receiveMana((int)((float)getCost()/(float)ticksPerCast));
            }
            int newOutput = target.receiveHealing(output, true);
            if(isSelectedCriticalHealerII)    {
                if(isSelectedCriticalHealerII) {
                    target.applyShield((int)((float)newOutput/2f));
                    target.addStatusEffect(new BarrierEffect(owner));
                }

            }
        }
        else {
            target.receiveHealing(output, false);
        }
        */
    }

    public void criticalHelper(RaidMember member, int output)    {
        if(criticalChance.isCritical()) {
            if(isSelectedResurgence){
                owner.receiveMana((int)((float)getCost()/(float)ticksPerCast));
            }
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
        isSelectedDiscipline = false;
        setCriticalChance(MIN_CRITICAL);
        setCastTime(MIN_CAST_TIME);
        ticksPerCast = 4;
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
            ticksPerCast = 5;
        }
        if(owner.getTalentTree().getTalent(TalentTree.RESURGENCE).isSelected())    {
            isSelectedResurgence = true;
        }
    }
}
