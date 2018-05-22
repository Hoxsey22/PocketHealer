package com.hoxseygaming.pockethealer.encounters.spells.Types;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.AudioManager;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Spell;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;

/**
 * Created by Hoxsey on 8/31/2017.
 */

public abstract class Castable extends Spell {

    public float castTime;
    public float MIN_CAST_TIME;
    public Timer castTimer;
    public Sound castingSFX;
    public Sound spellSFX;

    /**
     *
     * @param player
     * @param name
     * @param description
     * @param castTime
     * @param output
     * @param costPercentage
     * @param cooldown
     * @param spellSFX
     * @param assets
     */
    public Castable(Player player, String name, String description, int levelRequirement, float castTime,
                    int output, float costPercentage, float cooldown, Sound spellSFX, Assets assets) {
        super(player, name, description, levelRequirement, output, costPercentage, cooldown, assets);
        spellType = "Castable";
        this.spellSFX = spellSFX;
        this.castTime = castTime;
        MIN_CAST_TIME = castTime;
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
        target.receiveHealing(output, criticalChance.isCritical());

    }

    public void startCastTimer()    {
        castTimer = new Timer();
        isCasting = true;
        owner.isCasting = isCasting;
        AudioManager.playSFX(castingSFX,true);
        //castingSFX.loop(0.3f);

        final RaidMember sTarget = getOwnerTarget();

        castTimer.scheduleTask(new Timer.Task() {
            int counter = 0;
            @Override
            public void run() {
                counter++;
                owner.setSpellCastPercent((counter*0.01f)/castTime);

                if(counter * 0.01f >= castTime)    {
                    AudioManager.stopSFX(castingSFX);
                    //castingSFX.stop();
                    AudioManager.playSFX(spellSFX, false);
                    //spellSFX.play(0.3f);
                    System.out.println("applying spell");
                    applySpell(sTarget);
                    isCasting = false;
                    owner.isCasting = isCasting;
                    useMana();
                    startCooldownTimer();
                }
            }
        },0.01f, 0.01f,(int)(castTime/0.01f)-1);
    }

    @Override
    public void resetDefault() {
        super.resetDefault();
        castTime = MIN_CAST_TIME;
    }

    public void stop()  {
        if(castTimer != null) {
            castTimer.stop();
            castTimer.clear();
            owner.setSpellCastPercent(0);
            isCasting = false;
            owner.isCasting = isCasting;
            castingSFX.stop();
        }
    }

    public void checkHasteBuild()   {
        if(owner.getTalentTree().getTalent(TalentTree.HASTE_BUILD).isSelected())    {
            castTime = MIN_CAST_TIME - 0.5f;
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
