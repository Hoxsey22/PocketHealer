package com.hoxseygaming.pockethealer.reformat.Spells;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.reformat.RaidData;
import com.hoxseygaming.pockethealer.reformat.RaidMember;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Renew extends Spell {

    public float duration;
    public float currentTime;
    public Timer durationTimer;

    public Renew(int position)  {
        super("Renew","A small heal that is healed over time.", EffectType.HEALOVERTIME, 7, 15, 0.5f, position);
        setImage(RaidData.renewIconImage);

        duration = 10f;
        currentTime = 0f;

    }

    public void castSpell()    {
        if(isCastable())  {
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

        final RaidMember target = owner.getTarget();
        durationTimer.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                target.receiveHealing(output);
                increaseCurrentTimer();
                System.out.println("Renew is ticking! "+currentTime);
                if(currentTime == duration)    {
                    target.removeEffect(EffectType.HEALOVERTIME);
                    System.out.println("Renew expired");
                }
            }
        }, 2f, 2f, (int)(duration/2f));
    }

    public void increaseCurrentTimer()   {
        currentTime = currentTime +0.1f;
    }
}
