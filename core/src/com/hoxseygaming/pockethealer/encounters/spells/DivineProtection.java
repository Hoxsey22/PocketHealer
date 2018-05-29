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

    private ArrayList<Barrier> barriers;
    private boolean isSelectedCriticalHealerII;
    private boolean isSelectedResurgence;


    public DivineProtection(Player player, Assets assets) {
        super(player,
                "Divine Protection",
                "Calls the beyond for protection, reducing all damage by 20%.",
                7,
                2f,
                0,
                10f,
                100f,
                assets.getSound(assets.hotSFX),
                assets);
        setImage(getAssets().getTexture(getAssets().divineProtectionIcon));
    }

    @Override
    public void applySpell(RaidMember target) {
        for(int i = 0; i < getOwner().getRaid().getRaidMembers().size(); i++)   {
            getOwner().getRaid().getRaidMembers().get(i).addStatusEffect(new DivineProtectionEffect(getOwner()));
        }
    }

    @Override
    public void checkTalents() {}

    public ArrayList<Barrier> getBarriers() {
        return barriers;
    }

    public void setBarriers(ArrayList<Barrier> barriers) {
        this.barriers = barriers;
    }

    public boolean isSelectedCriticalHealerII() {
        return isSelectedCriticalHealerII;
    }

    public void setSelectedCriticalHealerII(boolean selectedCriticalHealerII) {
        isSelectedCriticalHealerII = selectedCriticalHealerII;
    }

    public boolean isSelectedResurgence() {
        return isSelectedResurgence;
    }

    public void setSelectedResurgence(boolean selectedResurgence) {
        isSelectedResurgence = selectedResurgence;
    }
}
