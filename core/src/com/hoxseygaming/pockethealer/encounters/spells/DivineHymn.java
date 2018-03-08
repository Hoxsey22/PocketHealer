package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.ChannelCast;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class DivineHymn extends ChannelCast {

    public DivineHymn(Player player, Assets assets) {
        super(player, "Divine Hymn",
                "A glorious hymn is rang throughout the raid, healing all ally units several times.",
                7,
                4f,
                4,
                20,
                5f,
                70f,
                assets);
        image = assets.getTexture(assets.divineHymnIcon);
    }

    @Override
    public void useMana() {
        if (owner.getTalentTree().getTalent(TalentTree.HOLY_FOCUS).isSelected())
            owner.receiveMana(getCost());
        else
            super.useMana();
    }

    @Override
    public void checkTalents() {
    }
}
