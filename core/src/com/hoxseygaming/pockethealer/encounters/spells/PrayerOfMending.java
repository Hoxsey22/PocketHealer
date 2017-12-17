package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.PrayerOfMendingEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Castable;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class PrayerOfMending extends Castable {

    public ArrayList<Barrier> barriers;
    public boolean isSelectedCriticalHealerII;
    public boolean isSelectedResurgence;

    public PrayerOfMending(Player player, int index, Assets assets) {
        super(player, "Prayer of Mending","When the target takes damage, the target will be healed and Prayer of Mending", 0,1.5f, EffectType.HEAL,
                35, 2f, 8,assets.getSound(assets.healSFX), index, assets);
        setImage(assets.getTexture(assets.prayerOfMendingIcon));
        isSelectedCriticalHealerII = false;
        isSelectedResurgence = false;
        barriers = new ArrayList<>();
    }

    @Override
    public void applySpell(RaidMember target) {
        target.addStatusEffect(new PrayerOfMendingEffect(owner, 20));
    }

    public void resetDefault()  {
    }


    @Override
    public void checkTalents() {
        if(owner.getTalentTree().getTalent(TalentTree.CRITICAL_HEALER).isSelected())    {
            setCriticalChance(30);
        }
        if(owner.getTalentTree().getTalent(TalentTree.HASTE_BUILD).isSelected())    {
            castTime = castTime - 0.25f;
        }
    }
}
