package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.DivineProtectionEffect;
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
                "A massive shield  that covers the raid, reducing all damage by 40%.",
                0,
                2f,
                EffectType.HEAL,
                0,
                10f,
                70f,
                assets.getSound(assets.hotSFX),
                0,
                assets);
        setImage(assets.getTexture(assets.divineProtectionIcon));
    }

    @Override
    public void applySpell(RaidMember target) {
        for(int i = 0; i < owner.getRaid().raidMembers.size(); i++)   {
            owner.getRaid().raidMembers.get(i).addStatusEffect(new DivineProtectionEffect(owner));
        }
    }


    @Override
    public void checkTalents() {}
}
