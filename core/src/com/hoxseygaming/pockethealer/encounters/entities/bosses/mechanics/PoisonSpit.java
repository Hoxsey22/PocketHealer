package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.PoisonEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class PoisonSpit extends Mechanic {

    private int numOfTargets;
    private boolean poisoned;

    public PoisonSpit(Boss owner) {
        super("Poison Spit", 20, 5f, owner);
        numOfTargets = 3;
        setAnnounce();
    }

    public PoisonSpit(Boss owner, float speed, int numOfTargets) {
        super("Poison Spit", 20, speed, owner);
        this.poisoned = true;
        this.numOfTargets = numOfTargets;
        setAnnounce();
    }

    @Override
    public void action() {
        ArrayList<RaidMember> notTank = getRaid().getRoleLessRaidMembers("tank");
        ArrayList<RaidMember> raidMembers = getRaid().getRandomRaidMember(numOfTargets,notTank);
        for(int i = 0; i < raidMembers.size(); i++)   {
            raidMembers.get(i).takeDamage(getDamage());
            if(poisoned)
                raidMembers.get(i).addStatusEffect(new PoisonEffect(getOwner(), 5, 0.2f));
        }
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
