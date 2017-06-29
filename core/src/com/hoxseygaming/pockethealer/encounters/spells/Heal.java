package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.EncounterData;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.Random;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Heal extends Spell {


    public float castTime;
    public float castTimeCounter;
    public float castTimePercentage;
    public Timer castTimer;
    public Sound casting_sfx;
    public Sound finish_spell;

    public Heal(int position, Player player, Assets assets) {
        super("Heal","An efficient slow powerful single target heal.", EffectType.HEAL, 40, 10, 0.5f, position);
        this.assets = assets;
        owner = player;
        image = this.assets.getTexture("heal_icon.png");
        castTime = 1.5f;
        castTimeCounter = 1f;
        casting_sfx = this.assets.getSound("sfx/casting_sfx.mp3");
        finish_spell = this.assets.getSound("sfx/heal_sfx.mp3");
        setCriticalChance(10);
    }

    public void castSpell()    {
        if(isCastable())  {
            useMana();
            startCooldownTimer();
            startCastTimer();
        }
    }

    public void startCastTimer()    {


        castTimer = new Timer();
        isCasting = true;
        owner.isCasting = isCasting;
        castTimeCounter = 0;
        System.out.println("Heal ID: "+ owner.target.getId());

        casting_sfx.loop(0.3f);

        final RaidMember selectedTarget = owner.getTarget();

        castTimer.schedule(new Timer.Task() {

            @Override
            public void run() {
                castTimeCount();
                owner.setSpellCastPercent(getCastTimePercentage());
                System.out.println(name + " cast timer: " + castTimeCounter);
                if (getCastTimePercentage() >= 1f) {
                    finish_spell.play(0.3f);
                    applySpell(selectedTarget);
                    setCastTimeCounter(1f);
                    isCasting = false;
                    owner.isCasting = false;
                    castTimer.clear();
                    owner.setSpellCastPercent(0);
                    casting_sfx.stop();

                }
            }
        }, 0.05f, 0.05f, (int)(castTime/0.05));
    }

    public void applySpell(RaidMember target)    {
        target.receiveHealing(output,criticalChance.isCritical());
    }

    public float getCastTime() {
        return castTime;
    }

    public void setCastTime(float castTime) {
        this.castTime = castTime;
    }

    public float getCastTimeCounter() {
        return castTimeCounter;
    }

    public void castTimeCount() {
        castTimeCounter = castTimeCounter + 0.05f;
        getCastTimePercentage();
    }

    public void setCastTimeCounter(float castTimeCounter) {
        this.castTimeCounter = castTimeCounter;
        getCastTimePercentage();
    }

    public float getCastTimePercentage() {
        return castTimeCounter/castTime;
    }

    public void setCastTimePercentage(float castTimePercentage) {
        this.castTimePercentage = castTimePercentage;
    }

    public Timer getCastTimer() {
        return castTimer;
    }

    public void setCastTimer(Timer castTimer) {
        this.castTimer = castTimer;
    }
}
