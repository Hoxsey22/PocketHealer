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
    public Periodical(Player player, String name, String description, int levelRequirement, EffectType effectType, int numOfTargets,
                      int output, int cost, float cooldown, float duration, float speed, Sound spellSFX, int index, Assets assets) {
        super(player, name, description, levelRequirement, effectType, numOfTargets, output, cost, cooldown, spellSFX, index, assets);
        this.duration = duration;
        MIN_DURATION = duration;
        this.speed = speed;
        MIN_SPEED = speed;

    }

    @Override
    public void applySpell(RaidMember target) {
        startDurationTimer();
        System.out.println(name+" applied.");
    }

    public void startDurationTimer()  {
        if (!getOwnerTarget().containsEffects(effectType)) {
            getOwnerTarget().applyEffect(effectType);
        }
        else {
            //if(durationTimer != null)
                durationTimer.clear();
                System.out.println("timer clear");
        }
        durationTimer = new Timer();

        final RaidMember raidMember = getOwnerTarget();

        durationTimer.scheduleTask(new Timer.Task() {
            float currentTime = 0;

            @Override
            public void run() {

                currentTime = currentTime + speed;

                if(raidMember.isDead()) {
                    durationTimer.stop();
                    durationTimer.clear();
                }
                if(currentTime >= duration )    {
                    raidMember.removeEffect(EffectType.HEALOVERTIME);
                    System.out.println(name+" expired");
                    checkLifeboom();
                    //stop();
                }
                raidMember.receiveHealing(output,criticalChance.isCritical());
            }
        }, speed, speed,(int)(duration/speed)-1);

    }

    public void startDurationTimer(RaidMember tar) {
        setTarget(tar);
        if (!getTarget().containsEffects(effectType)) {
            getTarget().applyEffect(effectType);
        }
        else {
            durationTimer.clear();
        }
        durationTimer = new Timer();

        final RaidMember raidMember = getTarget();

        durationTimer.scheduleTask(new Timer.Task() {
            float currentTime = 0;

            @Override
            public void run() {

                currentTime = currentTime + speed;

                if (raidMember.isDead())
                    durationTimer.stop();
                if (currentTime >= duration) {
                    raidMember.removeEffect(effectType);
                    System.out.println(name + " expired");
                    durationTimer.stop();
                    durationTimer.clear();
                }
                raidMember.receiveHealing(output, criticalChance.isCritical());
            }
        }, speed, speed,(int)(duration/speed)-1);
    }

    public abstract void checkLifeboom();

    public void stop() {
        if(durationTimer != null)    {
            durationTimer.stop();
            durationTimer.clear();
        }
    }
}
