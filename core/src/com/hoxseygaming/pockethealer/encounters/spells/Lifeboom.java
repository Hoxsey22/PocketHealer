package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Lifeboom extends Periodical {

    /**
     * @param player
     */
    public Lifeboom(Player player,Assets assets)  {
        super(player, "Lifeboom","A small heal that is healed over time.",0, EffectType.RNHEAL, 1, 3, 15,
                0.5f, 10f, 2f, assets.getSound(assets.hotSFX), 0, assets);
        image = this.assets.getTexture(assets.renewIcon);
        setCriticalChance(owner.getCriticalChance());
    }

    public Lifeboom(Player player, Renew parent, Assets assets)  {
        super(player, "Lifeboom","A small heal that is healed over time.",0, EffectType.LBHEAL, 1, 3, 0,
                0f, parent.duration, parent.speed, assets.getSound(assets.hotSFX), 0, assets);
        image = this.assets.getTexture(assets.renewIcon);
        setCriticalChance(owner.getCriticalChance());
    }

    @Override
    public void checkLifeboom() {
    }

    @Override
    public void checkTalents() {

    }
}
