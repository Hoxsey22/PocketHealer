package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.BarrierEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Castable;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/31/2017.
 */

public class Smite extends Castable {


    private boolean isSelectedDiscipline;
    private boolean isSelectedCriticalHealerII;
    public ArrayList<Barrier> barriers;

    /**
     * @param player
     * @param index
     * @param assets
     */
    public Smite(Player player, int index, Assets assets) {
        super(player, "Smite",
                "A spell that will inflict damage onto the boss and will heal the lowest raid member for half the damage done to the boss.",
                5,
                0.5f,
                EffectType.DAMAGEHEAL,
                10,
                0.5f,
                0.5f,
                assets.getSound(assets.healSFX),
                index,
                assets);

        image = assets.getTexture(assets.smiteIcon);
        barriers = new ArrayList<>();
    }

    @Override
    public void checkTalents() {
        resetDefault();

        if(owner.getTalentTree().getTalent(TalentTree.CRITICAL_HEALER).isSelected())    {
            setCriticalChance(30);
        }
        if(owner.getTalentTree().getTalent(TalentTree.DISCIPLINE).isSelected())    {
            output = output + 2;
            isSelectedDiscipline = true;
        }
        if(owner.getTalentTree().getTalent(TalentTree.CRITICAL_HEALER_II).isSelected())    {
            isSelectedCriticalHealerII = true;
        }
        if(owner.getTalentTree().getTalent(TalentTree.HASTE_BUILD).isSelected())    {
            castTime = MIN_CAST_TIME - 0.25f;
            cooldown = MIN_COOLDOWN - 0.25f;
        }
    }

    public void resetDefault()  {
        isSelectedCriticalHealerII = false;
        setCriticalChance(MIN_CRITICAL);
        setCastTime(MIN_CAST_TIME);
    }

    @Override
    public void applySpell(RaidMember target) {
        RaidMember lowest = owner.getRaid().getRaidMemberWithLowestHp();
        if(criticalChance.isCritical()) {
            int newOutput = owner.getBoss().takeDamage(output, true);
            if(isSelectedDiscipline)    {
                lowest.receiveHealing(newOutput, false);
                if(isSelectedCriticalHealerII) {
                    lowest.applyShield(newOutput);
                    lowest.addStatusEffect(new BarrierEffect(owner));
                }
                else    {
                    lowest.applyShield(output);
                    lowest.addStatusEffect(new BarrierEffect(owner));
                }

            }
        }
        lowest.receiveHealing(output, false);
    }

}
