package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.audio.Sound;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.BarrierEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Barrier extends InstantCast {

    public Sound sfx;

    public Barrier(Player player, int index, Assets assets) {
        super(player,"Barrier", "An absorption shield that will prevent the target from taking damage to its health.",2, EffectType.SHIELD, 1, 60, 35, 4f,assets.getSound(assets.barrierSFX), index,assets);
        image = this.assets.getTexture(assets.barrierIcon);
        checkTalents();
    }

    public Barrier(Player player, int output, int index, Assets assets) {
        super(player,"Barrier", "An absorption shield that will prevent the target from taking damage to its health.", 2,EffectType.SHIELD, 1, output, 35, 4f,assets.getSound(assets.barrierSFX), index,assets);
        image = this.assets.getTexture(assets.barrierIcon);
    }

    public void resetDefault()  {
        cooldown = MIN_COOLDOWN;
        cost =  MIN_COST;
        output = MIN_OUTPUT;
    }

    @Override
    public void checkTalents() {
        resetDefault();

        if(owner.getTalentTree().getTalent(TalentTree.BARRIER_MASTER).isSelected())    {
            cooldown = 1.0f;
            cost = 15;
        }
        if(owner.getTalentTree().getTalent(TalentTree.DISCIPLINE).isSelected())    {
            output = 80;
        }
    }

    @Override
    public void applySpell(RaidMember target) {
        target.applyShield(output);
        target.addStatusEffect(new BarrierEffect(owner));
    }
}
