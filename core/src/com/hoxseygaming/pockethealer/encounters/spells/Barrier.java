package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.BarrierEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.InstantCast;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Barrier extends InstantCast {

    public Barrier(Player player, Assets assets) {
        super(player,"Barrier", "Places a barrier around the ally unit, absorbing incoming damage.",
                6,
                1,
                60,
                2.3f,
                4f,
                assets.getSound(assets.barrierSFX),
                assets);
        setImage(this.getAssets().getTexture(getAssets().barrierIcon));
        checkTalents();
    }

    @Override
    public void checkTalents() {
        resetDefault();

        if(getOwner().getTalentTree().getTalent(TalentTree.BARRIER_MASTER).isSelected())    {
            setCooldown(1f);
            setCost(25);
        }
        if(getOwner().getTalentTree().getTalent(TalentTree.DISCIPLINE).isSelected())    {
            setOutput(80);
        }
    }

    @Override
    public void applySpell(RaidMember target) {
        target.applyShield(getOutput());
        target.addStatusEffect(new BarrierEffect(getOwner()));

        if(getOwner().getTalentTree().getTalent(TalentTree.CRITICAL_HEALER_II).isSelected()) {
            applyAtonement(target);
        }
    }
}
