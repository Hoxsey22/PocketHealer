package com.hoxseygaming.pockethealer.encounters.spells.Types;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.AudioManager;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Spell;

/**
 * Created by Hoxsey on 8/31/2017.
 */

public abstract class ChannelCast extends Spell {

    private float castTime;
    private float MIN_CAST_TIME;
    private int ticksPerCast;
    private int MIN_TICK_PER_CAST;
    protected Timer castTimer;
    protected Sound castingSFX;
    protected Sound spellSFX;

    /**
     *
     * @param player
     * @param name
     * @param description
     * @param castTime
     * @param output
     * @param costPercentage
     * @param cooldown
     * @param assets
     */
    public ChannelCast(Player player, String name, String description, int levelRequirement, float castTime, int ticksPerCast,
                       int output, float costPercentage, float cooldown, Assets assets) {
        super(player, name, description, levelRequirement, output, costPercentage, cooldown, assets);
        setSpellType("Channeled");
        this.castTime = castTime;
        MIN_CAST_TIME = castTime;
        this.ticksPerCast = ticksPerCast;
        MIN_TICK_PER_CAST = ticksPerCast;
        castingSFX = getAssets().getSound(getAssets().castingSFX);
    }

    @Override
    public void castSpell() {
        if(isCastable())    {
            startCastTimer();
        }

    }

    @Override
    public void applySpell(RaidMember target)    {
        target.receiveHealing(getOutput(), getCriticalChance().isCritical());
    }

    public void startCastTimer()    {
        castTimer = new Timer();
        setCasting(true);
        getOwner().setCasting(isCasting());
        AudioManager.playSFX(castingSFX, true);
        final float tickTime = (castTime/ticksPerCast)-0.01f;

        final RaidMember sTarget = getOwnerTarget();

        castTimer.scheduleTask(new Timer.Task() {
            int counter = 0;
            int tCounter = 0;

            @Override
            public void run() {
                counter++;
                tCounter++;

                getOwner().setSpellCastPercent(1f-(counter*0.01f)/castTime);

                if(tCounter * 0.01f >= tickTime)    {
                    tCounter = 0;
                    applySpell(sTarget);
                }
                if(counter * 0.01f >= castTime)    {
                    AudioManager.stopSFX(castingSFX);
                    setCasting(false);
                    getOwner().setCasting(isCasting());
                    useMana();
                    startCooldownTimer();
                    stop();
                }
            }
        },0.01f, 0.01f,(int)(castTime/0.01f)-1);
    }

    @Override
    public void resetDefault() {
        super.resetDefault();
        castTime = MIN_CAST_TIME;
        ticksPerCast = MIN_TICK_PER_CAST;
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

    public float getMIN_CAST_TIME() {
        return MIN_CAST_TIME;
    }

    public void setMIN_CAST_TIME(float MIN_CAST_TIME) {
        this.MIN_CAST_TIME = MIN_CAST_TIME;
    }

    public int getTicksPerCast() {
        return ticksPerCast;
    }

    public void setTicksPerCast(int ticksPerCast) {
        this.ticksPerCast = ticksPerCast;
    }

    public int getMIN_TICK_PER_CAST() {
        return MIN_TICK_PER_CAST;
    }

    public void setMIN_TICK_PER_CAST(int MIN_TICK_PER_CAST) {
        this.MIN_TICK_PER_CAST = MIN_TICK_PER_CAST;
    }

    public Timer getCastTimer() {
        return castTimer;
    }

    public void setCastTimer(Timer castTimer) {
        this.castTimer = castTimer;
    }
}
