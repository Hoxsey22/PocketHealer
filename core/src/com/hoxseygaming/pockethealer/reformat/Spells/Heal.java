package com.hoxseygaming.pockethealer.reformat.spells;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.reformat.EncounterData;
import com.hoxseygaming.pockethealer.reformat.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.reformat.player.Player;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Heal extends Spell {


    public float castTime;
    public float castTimeCounter;
    public float castTimePercentage;
    public Timer castTimer;

    public Heal(int position, Player player) {
        super("Heal","An efficient slow powerful single target heal.", EffectType.HEAL, 40, 10, 0.5f, position);
        owner = player;
        image = EncounterData.healIconImage;
        castTime = 1.5f;
        castTimeCounter = 1f;
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
        final RaidMember selectedTarget = owner.getTarget();

        castTimer.schedule(new Timer.Task() {

            @Override
            public void run() {
                castTimeCount();
                owner.setSpellCastPercent(getCastTimePercentage());
                System.out.println(name + " cast timer: " + castTimeCounter);
                if (getCastTimePercentage() >= 1f) {
                    applySpell(selectedTarget);
                    setCastTimeCounter(1f);
                    isCasting = false;
                    owner.isCasting = false;
                    castTimer.clear();
                    owner.setSpellCastPercent(0);
                }
            }
        }, 0.05f, 0.05f, (int)(castTime/0.05));
    }

    public void applySpell(RaidMember target)    {
        target.receiveHealing(output);
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
