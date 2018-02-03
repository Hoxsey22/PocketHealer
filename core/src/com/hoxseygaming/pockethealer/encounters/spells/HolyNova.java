package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.audio.Sound;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Castable;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class HolyNova extends Castable {

    public Sound sfx;
    public final int MIN_NUMOFTARGETS = 3;

    public HolyNova(Player player, int index, Assets assets) {
        super(player,
                "Holy Nova",
                "Heals multiple targets with the lowest health. Great for getting the raid healed up.",
                3,
                2f,
                EffectType.HEALMULTIPLE,
                25,
                3.5f,
                1f,assets.getSound(assets.healSFX), index,assets);
        image = assets.getTexture(assets.holyNovaIcon);
        sfx = assets.getSound(assets.hotSFX);
        numOfTargets = MIN_NUMOFTARGETS;
    }

    @Override
    public void checkTalents() {
        resetDefault();

        checkCriticalHealer();
        if(owner.getTalentTree().getTalent(TalentTree.SUPER_NOVA).isSelected())    {
            numOfTargets = 4;
        }
    }

    @Override
    public void applySpell(RaidMember target)    {
        // main tar
        System.out.println("checking renew");
        getRandomTargets();

        if(owner.getTalentTree().getTalent(owner.getTalentTree().CRITICAL_HEALER_II).isSelected())    {
            for(int i = 0; i < numOfTargets; i++) {
                applyCriticalHealerII(targets.get(i),output);
            }
            applyCriticalHealerII(target, output);
            for(int i = 0; i < numOfTargets; i++) {
                applyAtonement(targets.get(i));
            }
            applyAtonement(target);
        }
        if(owner.getTalentTree().getTalent(owner.getTalentTree().RENEWING_NOVA).isSelected())  {
            for(int i = 0; i < numOfTargets; i++) {
                applyRenewingNova(targets.get(i));
                targets.get(i).receiveHealing(output, criticalChance.isCritical());
            }
            applyRenewingNova(target);
            target.receiveHealing(output, criticalChance.isCritical());
        }

    }

    @Override
    public void resetDefault()  {
        super.resetDefault();
        numOfTargets = MIN_NUMOFTARGETS;
    }
}
