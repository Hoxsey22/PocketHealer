package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.EncounterData;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Renew extends Spell {

    public float duration;
    public Timer durationTimer;
    public Sound sfx;

    /**
     * @param position
     * @param player
     */
    public Renew(int position, Player player, Assets assets)  {
        super("Renew","A small heal that is healed over time.", EffectType.HEALOVERTIME, 7, 15, 0.5f, position);
        setAssets(assets);
        owner = player;
        image = this.assets.getTexture("renew_icon.png");

        duration = 10f;
        sfx = this.assets.getSound("sfx/hot_sfx.mp3");
        setCriticalChance(10);

    }


    public void castSpell()    {
        if(isCastable())  {
            useMana();
            startCooldownTimer();
            startDurationTimer();
        }
    }

    public void startDurationTimer()    {
        if (!owner.getTarget().containsEffects(EffectType.HEALOVERTIME)) {
            owner.getTarget().applyEffect(EffectType.HEALOVERTIME);
            durationTimer = new Timer();
        }
        else
            durationTimer.clear();
        sfx.play(0.3f);
        final RaidMember target = owner.getTarget();
        durationTimer.scheduleTask(new Timer.Task() {
            float currentTime = 0;
            @Override
            public void run() {
                if(target.isDead())
                    durationTimer.stop();
                target.receiveHealing(output,criticalChance.isCritical());
                currentTime = currentTime +2f;
                System.out.println("Renew is ticking! "+currentTime);
                if(currentTime >= duration)    {
                    target.removeEffect(EffectType.HEALOVERTIME);
                    System.out.println("Renew expired");
                    //durationTimer.clear();
                }
            }
        }, 2f, 2f, (int)(duration/2f)-1);
    }

}
