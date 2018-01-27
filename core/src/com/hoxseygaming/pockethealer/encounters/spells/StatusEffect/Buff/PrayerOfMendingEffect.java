package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff;

import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.CriticalDice;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 12/1/2017.
 */

public class PrayerOfMendingEffect extends Buff{

    public int jumpCount;
    /**
     * */
    public PrayerOfMendingEffect(Player owner) {
        super(owner, 4, "Prayer of Mending", "When the target takes damage, the target will be healed and Prayer of Mending" +
                "will jump to a new target.", owner.getAssets().getTexture(owner.getAssets().prayerOfMendingIcon), -1, -1, 20, false);
        jumpCount = 5;
    }

    public PrayerOfMendingEffect(Player owner, int modValue) {
        super(owner, 4, "Prayer of Mending", "When the target takes damage, the target will be healed and Prayer of Mending" +
                "will jump to a new target.", owner.getAssets().getTexture(owner.getAssets().prayerOfMendingIcon), 45f, 46f, modValue, false);
        jumpCount = 5;
    }

    @Override
    public void startConditions() {

    }

    @Override
    public void additionalConditions() {

    }

    @Override
    public void applyEffect() {
        getTarget().receiveHealing(getModValue(), CriticalDice.roll(getOwner().criticalChance));

        jumpCount--;
        if(jumpCount > 1) {
            ArrayList<RaidMember> temp = getOwner().getRaid().getRandomRaidMember(1, getOwner().getRaid().getBuffLessRaidMembers(this.getName()));
            if(temp.size() < 1)    {
                return;
            }
            RaidMember newTarget = temp.get(0);
            removeFromParent();

            setTarget(newTarget);
            setParent(getTarget().getStatusEffectList());
            getTarget().addStatusEffect(this);
        }
        else {
            remove();
        }
    }

    @Override
    public int modifyOutput(int output) {
        return output;
    }
}
