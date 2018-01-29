package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.BlessedGardenEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Castable;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class BlessedGarden extends Castable {

    public ArrayList<Barrier> barriers;
    public boolean isSelectedCriticalHealerII;
    public boolean isSelectedResurgence;


    public BlessedGarden(Player player, Assets assets) {
        super(player,
                "Blessed Garden",
                "Blankets the raid in renews.",
                0,
                4f,
                EffectType.HEAL,
                0,
                10f,
                90f,
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
