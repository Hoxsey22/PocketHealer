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
                "Blossoms a beautiful garden around the entire raid, increasing all heals by 40%.",
                7,
                2f,
                0,
                10f,
                100f,
                assets.getSound(assets.blessedGardenSFX),
                assets);
        setDescription("Grants all ally units a buff that will increase all healing received by 40% for 15 seconds.");
        setImage(getAssets().getTexture(getAssets().blessedGardenIcon));
    }

    @Override
    public void applySpell(RaidMember target) {
        for(int i = 0; i < getOwner().getRaid().getRaidMembers().size(); i++)   {
            getOwner().getRaid().getRaidMembers().get(i).addStatusEffect(new BlessedGardenEffect(getOwner()));
        }
    }


    @Override
    public void checkTalents() {}
}
