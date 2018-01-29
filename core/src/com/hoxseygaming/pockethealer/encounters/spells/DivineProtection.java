package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.BarrierEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Castable;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class DivineProtection extends Castable {

    public ArrayList<Barrier> barriers;
    public boolean isSelectedCriticalHealerII;
    public boolean isSelectedResurgence;


    public DivineProtection(Player player, Assets assets) {
        super(player,
                "Divine Protection",
                "A massive shield  that covers the raid.",
                0,
                4f,
                EffectType.HEAL,
                60,
                10f,
                90f,
                assets.getSound(assets.healSFX),
                0,
                assets);
        setImage(assets.getTexture(assets.divineProtectionIcon));
    }

    @Override
    public void applySpell(RaidMember target) {
        for(int i = 0; i < owner.getRaid().raidMembers.size(); i++)   {
            owner.getRaid().raidMembers.get(i).applyShield(output);
            owner.getRaid().raidMembers.get(i).addStatusEffect(new BarrierEffect(owner));
        }
    }


    @Override
    public void checkTalents() {}
}
