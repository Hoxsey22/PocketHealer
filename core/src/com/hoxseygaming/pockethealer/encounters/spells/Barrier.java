package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.EncounterData;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Barrier extends Spell {

    public Sound sfx;

    public Barrier(int index, Player player, Assets assets) {
        super("Barrier", "An absorption shield.", EffectType.SHIELD, 60, 35, 4f, index);
        setAssets(assets);
        owner = player;
        image = this.assets.getTexture("barrier_icon.png");
        sfx = this.assets.getSound("sfx/barrier_sfx.mp3");
    }

    @Override
    public void castSpell() {
        if(isCastable())    {
            useMana();
            applySpell();
        }
    }

    public void applySpell()    {
        sfx.play(0.3f);
        startCooldownTimer();
        owner.getTarget().applyShield(output);
    }
}
