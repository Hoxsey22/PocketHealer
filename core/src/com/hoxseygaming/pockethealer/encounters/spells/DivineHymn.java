package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class DivineHymn extends Spell {

    public Sound sfx;
    public Timer channel;

    public DivineHymn(int index, Player player, Assets assets) {
        super(player, "Divine Hymn", "", EffectType.HEALALL, 15, 150, 45f, index, assets);
        image = assets.getTexture("barrier_icon.png");
        sfx = assets.getSound("sfx/barrier_sfx.mp3");
        setCriticalChance(0);
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
        startChannel();
    }

    public void startChannel()  {
        channel = new Timer();

        channel.schedule(new Timer.Task() {
            int count =  0;
            @Override
            public void run() {
                if(count != 5) {
                    count++;
                    owner.raid.receiveHealing(output);
                }
                else    {
                    channel.stop();
                    channel.clear();
                }
            }
        },0.5f,0.5f,5);
    }

}
