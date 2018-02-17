package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.AoDEffect;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.Buff;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.LifeboomEffect;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.RenewEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Periodical;

/**
 * Created by Hoxsey on 12/2/2017.
 */

public class Renew2 extends Periodical {

    private Buff buff;

    /**
     * @param player
     * @param index
     * @param assets
     */
    public Renew2(Player player, int index, Assets assets) {
        super(player, "Renew", "A renewing amount of holy light is place on an ally unit.",
                0, EffectType.HEALOVERTIME,
                1,
                7,
                1f,
                0.5f,
                10f,
                2f, assets.getSound(assets.hotSFX), index, assets);
        image = this.assets.getTexture(assets.renewIcon);

    }

    @Override
    public void applySpell(RaidMember target) {
        checkLifeboom();
        if (target.getStatusEffectList().contains("Lifeboom Effect")
                && !target.getStatusEffectList().contains("AoD Effect")
                && owner.getTalentTree().getTalent(TalentTree.AOD).isSelected()) {

            target.addStatusEffect(new AoDEffect(owner, duration, speed, output));
        }
        else {
            target.addStatusEffect(buff);
        }
    }

    @Override
    public void checkTalents() {
        resetDefault();

        checkAoD();
        checkCriticalHealer();
        checkHasteBuild();
    }

    @Override
    public void checkLifeboom() {
        if(owner.getTalentTree().getTalent(TalentTree.LIFEBOOM).isSelected())  {
            buff = new LifeboomEffect(owner, duration, speed,output);
        }
        else    {
            buff = new RenewEffect(owner, duration, speed, output);
        }
    }
}
