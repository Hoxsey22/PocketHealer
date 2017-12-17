package com.hoxseygaming.pockethealer.encounters.spells.Types;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Spell;

/**
 * Created by Hoxsey on 8/31/2017.
 */

public abstract class ChannelCast extends Spell {

    public float castTime;
    public float MIN_CAST_TIME;
    public int ticksPerCast;
    public int MIN_TICK_PER_CAST;
    public Timer castTimer;
    public Sound castingSFX;
    public Sound spellSFX;

    /**
     *
     * @param player
     * @param name
     * @param description
     * @param castTime
     * @param effectType
     * @param output
     * @param costPercentage
     * @param cooldown
     * @param index
     * @param assets
     */
    public ChannelCast(Player player, String name, String description, int levelRequirement, float castTime, int ticksPerCast, EffectType effectType,
                       int output, float costPercentage, float cooldown, int index, Assets assets) {
        super(player, name, description, levelRequirement,effectType, output, costPercentage, cooldown, index, assets);
        //this.spellSFX = spellSFX;
        this.castTime = castTime;
        MIN_CAST_TIME = castTime;
        this.ticksPerCast = ticksPerCast;
        MIN_TICK_PER_CAST = ticksPerCast;
        castingSFX = assets.getSound(assets.castingSFX);
    }

    @Override
    public void castSpell() {
        if(isCastable())    {
            startCastTimer();
        }

    }

    @Override
    public void applySpell(RaidMember target)    {
        if(effectType == EffectType.HEAL)
            target.receiveHealing(output, criticalChance.isCritical());
        if(effectType == EffectType.HEALALL)
            owner.raid.receiveHealing(output);
    }

    public void startCastTimer()    {
        castTimer = new Timer();
        isCasting = true;
        owner.isCasting = isCasting;
        castingSFX.loop(0.3f);
        final float tickTime = (castTime/ticksPerCast)-0.01f;

        final RaidMember sTarget = getOwnerTarget();

        castTimer.scheduleTask(new Timer.Task() {
            int counter = 0;
            int tCounter = 0;

            @Override
            public void run() {
                counter++;
                tCounter++;

                owner.setSpellCastPercent(1f-(counter*0.01f)/castTime);

                if(tCounter * 0.01f >= tickTime)    {
                    tCounter = 0;
                    applySpell(sTarget);
                }
                if(counter * 0.01f >= castTime)    {
                    castingSFX.stop();
                    isCasting = false;
                    owner.isCasting = isCasting;
                    useMana();
                    startCooldownTimer();
                    stop();
                }
            }
        },0.01f, 0.01f,(int)(castTime/0.01f)-1);
    }

    public void stop()  {
        if(castTimer != null) {
            castTimer.stop();
            castTimer.clear();
        }
    }

    public float getCastTime() {
        return castTime;
    }

    public void setCastTime(float castTime) {
        this.castTime = castTime;
    }

    public Sound getCastingSFX() {
        return castingSFX;
    }

    public void setCastingSFX(Sound castingSFX) {
        this.castingSFX = castingSFX;
    }

    public Sound getSpellSFX() {
        return spellSFX;
    }

    public void setSpellSFX(Sound spellSFX) {
        this.spellSFX = spellSFX;
    }
}
