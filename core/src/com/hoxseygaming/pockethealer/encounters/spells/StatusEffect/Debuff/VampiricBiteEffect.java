package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 11/29/2017.
 */

public class VampiricBiteEffect extends Debuff {

    private Timer passTimer;

    /**
     */
    public VampiricBiteEffect(Boss owner) {
        super(owner,
                5,
                "Vampiric Bite Effect",
                "Vampiric Bite will do moderate damage and will increase the target's damage " +
                        " by 1000%.",
                owner.getAssets().getTexture(owner.getAssets().biteIcon),
                600f,
                5f,
                1,
                false);
    }

    private void startPassTimer()   {
        passTimer = new Timer();
        passTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ArrayList<RaidMember> debuffless = getOwner().getEnemies().getDebuffLessRaidMembers(getName());
                if(debuffless.size() > 0) {
                    RaidMember newTarget = getOwner().getEnemies().getRandomRaidMember(1, debuffless).get(0);
                    newTarget.addStatusEffect(new VampiricBiteEffect(getOwner()));
                }
        //30f
            }
        },30f,30f);
    }

    @Override
    public void additionalConditions() {

    }

    @Override
    public void applyEffect() {
        getTarget().takeDamage(getModValue());
        setModValue(getModValue()+1);
    }

    @Override
    public int modifyOutput(int output) {
        return output;
    }

    @Override
    public void startConditions() {
        getTarget().setDamage(getTarget().getDamage() * 10);
        startPassTimer();
    }

    @Override
    public void remove() {
        super.remove();
        passTimer.stop();
        passTimer.clear();
    }
}
