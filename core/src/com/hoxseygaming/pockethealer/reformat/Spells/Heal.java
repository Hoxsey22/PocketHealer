package com.hoxseygaming.pockethealer.reformat.Spells;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.players.Player;
import com.hoxseygaming.pockethealer.reformat.RaidData;
import com.hoxseygaming.pockethealer.reformat.RaidMember;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Heal extends Spell {


    public float castTime;
    public float castTimeCounter;
    public float castTimePercentage;
    public Timer castTimer;

    public Heal(int position) {
        super("Heal","An efficient slow powerful single target heal.", EffectType.HEAL, 40, 10, 0.5f, position);
        setImage(RaidData.healIconImage);
        castTimeCounter = 1f;
    }

    public void castSpell()    {
        if(isCastable())  {
            startCooldownTimer();
            startCastTimer();
        }
    }

    public void startCastTimer()    {


        castTimer = new Timer();
        isCasting = true;

        final RaidMember selectedTarget = owner.getTarget();

        castTimer.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                castTimeCount();
                if (castTimePercentage == 1f) {
                    applySpell(selectedTarget);
                    setCastTimeCounter(1f);
                    isCasting = false;
                    castTimer.clear();
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
        castTimePercentage = castTimeCounter/castTime;
        return castTimePercentage;
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
