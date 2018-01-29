package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.BarrierEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.ChannelCast;

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
                35,
                3f,
                5f,
                0,
                assets);
        image = assets.getTexture(assets.penanceIcon);
    }

    @Override
    public void applySpell(RaidMember target) {
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
