package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.BlessedGardenEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Castable;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class BlessedGarden extends Castable {

    public BlessedGarden(Player player, Assets assets) {
        super(player,
                "Blessed Garden",
                "Increase all healing output by 40% for 15 seconds.",
                0,
                2f,
                EffectType.HEAL,
                0,
                10f,
                70f,
                assets.getSound(assets.healSFX),
                0,
                assets);
        setImage(assets.getTexture(assets.blessedGardenIcon));
    }

    @Override
    public void applySpell(RaidMember target) {
        for(int i = 0; i < owner.getRaid().raidMembers.size(); i++)   {
            owner.getRaid().raidMembers.get(i).addStatusEffect(new BlessedGardenEffect(owner));
        }
    }


    @Override
    public void checkTalents() {}
}
