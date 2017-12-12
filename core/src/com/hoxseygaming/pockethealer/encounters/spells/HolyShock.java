package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.BarrierEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;

/**
 * Created by Hoxsey on 12/6/2017.
 */

public class HolyShock extends InstantCast {

    public boolean isSelectedCriticalHealerII;
    public boolean isSelectedResurgence;
    /**
     * @param player
     * @param assets
     */
    public HolyShock(Player player, Assets assets) {
        super(player, "Holy Shock",
                "A strong instant heal that also increase the next single target heal by 50%.",
                0,
                EffectType.HEAL,
                1,
                50,
                25,
                12f,
                assets.getSound(assets.healSFX),
                0,
                assets);

        setImage(assets.getTexture(assets.criticalHealer2Icon));
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

        owner.holyShockIncrease = true;

    }

    public void resetDefault()  {
        isSelectedResurgence = false;
        isSelectedCriticalHealerII = false;
        setCriticalChance(MIN_CRITICAL);
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
        if(owner.getTalentTree().getTalent(TalentTree.RESURGENCE).isSelected())    {
            isSelectedResurgence = true;
        }
    }
}
