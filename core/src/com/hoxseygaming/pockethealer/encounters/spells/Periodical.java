package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 9/7/2017.
 */

public abstract class Periodical extends InstantCast {

    public float duration;
    public float MIN_DURATION;
    public float speed;
    public float MIN_SPEED;
    public Timer durationTimer;

    /**
     * @param player
     * @param name
     * @param description
     * @param effectType
     * @param numOfTargets
     * @param output
     * @param cost
     * @param cooldown
     * @param spellSFX
     * @param index
     * @param assets
     */
    public Periodical(Player player, String name, String description, EffectType effectType, int numOfTargets,
                      int output, int cost, float cooldown, float duration, float speed, Sound spellSFX, int index, Assets assets) {
        super(player, name, description, effectType, numOfTargets, output, cost, cooldown, spellSFX, index, assets);
        this.duration = duration;
        MIN_DURATION = duration;
        this.speed = speed;
        MIN_SPEED = speed;
    }

    @Override
    public void applySpell(RaidMember target) {
        startDurationTimer();
    }

    public void startDurationTimer()  {
        if (!getTarget().containsEffects(effectType)) {
            getTarget().applyEffect(effectType);
            durationTimer = new Timer();
        }
        else {
                durationTimer.clear();
        }

        final RaidMember target = owner.getTarget();

        durationTimer.scheduleTask(new Timer.Task() {
            float currentTime = 0;

            @Override
            public void run() {

                currentTime = currentTime + speed;

                if(target.isDead())
                    durationTimer.stop();
                if(currentTime >= duration )    {
                    target.removeEffect(EffectType.HEALOVERTIME);
                    System.out.println(name+" expired");
                    checkLifeboom();
                    durationTimer.stop();
                }
                target.receiveHealing(output,criticalChance.isCritical());
            }
        }, speed, speed);

    }

    public void startDurationTimer(RaidMember tar) {
        if (!tar.containsEffects(effectType)) {
            tar.applyEffect(effectType);
            durationTimer = new Timer();
        }
        else
            durationTimer.clear();

        final RaidMember target = tar;

        durationTimer.scheduleTask(new Timer.Task() {
            float currentTime = 0;

            @Override
            public void run() {

                currentTime = currentTime + speed;

                if (target.isDead())
                    durationTimer.stop();
                if (currentTime >= duration) {
                    target.removeEffect(effectType);
                    System.out.println(name + " expired");
                    durationTimer.stop();
                }
                target.receiveHealing(output);
            }
        }, speed, speed);
    }

    public abstract void checkLifeboom();

    public void stop() {
        if(durationTimer != null)    {
            durationTimer.stop();
            durationTimer.clear();
        }
    }

}
