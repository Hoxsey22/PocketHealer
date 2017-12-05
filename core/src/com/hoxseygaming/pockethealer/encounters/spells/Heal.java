package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.BarrierEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;

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
                40, 10, 0.5f,assets.getSound(assets.healSFX), index, assets);
        setImage(assets.getTexture(assets.healIcon));
        isSelectedCriticalHealerII = false;
        isSelectedResurgence = false;
        barriers = new ArrayList<>();
    }

    @Override
    public void applySpell(RaidMember target) {

        if(criticalChance.isCritical()){

            if(isSelectedCriticalHealerII)  {
                target.applyShield((int)(output/2f));
                target.addStatusEffect(new BarrierEffect(owner));
            }

            if(isSelectedResurgence) {
                owner.mana = owner.getMana() + (int)(getCost()/2f);
            }
            target.receiveHealing(output, true);
            return;
        }
        else {
            target.receiveHealing(output, false);
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
            castTime = castTime - 0.25f;
        }
        if(owner.getTalentTree().getTalent(TalentTree.RESURGENCE).isSelected())    {
            isSelectedResurgence = true;
        }
    }
}
