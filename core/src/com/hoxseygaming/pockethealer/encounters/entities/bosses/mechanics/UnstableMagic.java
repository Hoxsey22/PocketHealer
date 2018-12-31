package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.AudioManager;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.UnstableMagicEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class UnstableMagic extends Mechanic {

    private int numOfTargets;

    public UnstableMagic(Boss owner) {
        super("Unstable Magic", 0, 8f, owner);
        numOfTargets = 1;
        setAnnounce();
    }

    @Override
    public void action() {
        AudioManager.playSFX(getAssets().getSound(getAssets().bigDebuffSFX), false);
        ArrayList<RaidMember> temp  = getOwner().getEnemies().getRandomRaidMember(numOfTargets,getOwner().getEnemies().getDebuffLessRaidMembers("Unstable Magic Effect"));

        for (int i = 0; i < temp.size(); i++)   {
            temp.get(i).addStatusEffect(new UnstableMagicEffect(getOwner()));
        }
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
