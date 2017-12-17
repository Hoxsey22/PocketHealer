package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.Buff;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.LifeboomEffect;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.RenewEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Periodical;

/**
 * Created by Hoxsey on 12/2/2017.
 */

public class Renew2 extends Periodical {

    private Buff buff;
    private boolean isSelectedLifeboom;

    /**
     * @param player
     * @param index
     * @param assets
     */
    public Renew2(Player player, int index, Assets assets) {
        super(player, "Renew", "A small heal that is healed over time.",
                0, EffectType.HEALOVERTIME,
                1,
                7,
                1f,
                0.5f,
                10f,
                2f, assets.getSound(assets.hotSFX), index, assets);
        image = this.assets.getTexture(assets.renewIcon);

    }

    @Override
    public void applySpell(RaidMember target) {
        checkLifeboom();
        target.addStatusEffect(buff);
    }

    @Override
    public void checkTalents() {
        resetDefault();

        if(owner.getTalentTree().getTalent(TalentTree.LIFEBOOM).isSelected())    {
            isSelectedLifeboom = true;
        }
        if(owner.getTalentTree().getTalent(TalentTree.AOD).isSelected())    {
            output = 10;
            duration = 12;
            speed = 1.5f;
            setCostPercentage(1.5f);
        }
        if(owner.getTalentTree().getTalent(TalentTree.CRITICAL_HEALER).isSelected())    {
            setCriticalChance(30);
        }
        if(owner.getTalentTree().getTalent(TalentTree.HASTE_BUILD).isSelected())    {
            speed = speed - 0.25f;
        }
    }

    public void resetDefault()  {
        isSelectedLifeboom = false;
        numOfTargets = MIN_NUM_OF_TARGETS;
        output = MIN_OUTPUT;
        cost = MIN_COST;
        cooldown = MIN_COOLDOWN;
        duration = MIN_DURATION;
        speed = MIN_SPEED;
    }

    @Override
    public void checkLifeboom() {
        if(isSelectedLifeboom)  {
            buff = new LifeboomEffect(owner,duration,speed,output-2);
        }
        else    {
            buff = new RenewEffect(owner,duration,speed,output);
        }
    }
}
