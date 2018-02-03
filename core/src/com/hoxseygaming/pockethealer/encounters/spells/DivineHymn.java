package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.ChannelCast;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class DivineHymn extends ChannelCast {

    public DivineHymn(Player player, int index, Assets assets) {
        super(player, "Divine Hymn",
                "A godly heal that heals the entire party. Best time to use this is when the raid is taking heavy damage.",
                7,
                4f,
                4,
                EffectType.HEALALL,
                20,
                5f,
                70f,
                index,
                assets);
        image = assets.getTexture(assets.divineHymnIcon);
    }

    @Override
    public void useMana() {
        if (owner.getTalentTree().getTalent(TalentTree.HOLY_FOCUS).isSelected())
            owner.receiveMana(getCost());
    }

    @Override
    public void checkTalents() {
    }
}
