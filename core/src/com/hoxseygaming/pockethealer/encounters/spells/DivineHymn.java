package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class DivineHymn extends ChannelCast {

    public boolean isSelectedHolyFocus;

    public DivineHymn(Player player, int index, Assets assets) {
        super(player, "Divine Hymn", "", 4f, 5, EffectType.HEALALL, 25, 150, 45f, index, assets);
        image = assets.getTexture(assets.divineHymnIcon);
        isSelectedHolyFocus = false;
    }

    @Override
    public void useMana() {
        if (isSelectedHolyFocus)
            owner.mana = owner.getMana() + getCost();
        else {
            super.useMana();
        }
    }

    @Override
    public void checkTalents() {
        if(owner.getTalentTree().getTalent(TalentTree.HOLY_FOCUS).isSelected())    {
            isSelectedHolyFocus = true;
        }
    }
}
