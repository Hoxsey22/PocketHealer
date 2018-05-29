package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.AgonyEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class Agony extends Mechanic {

    private int numOfTargets;

    public Agony(Boss owner) {
        super("Agony", 0, 18f, owner);
        numOfTargets = 1;
        setAnnounce(true);
    }

    public Agony(Boss owner, float speed) {
        super("Agony", 0, speed, owner);
        numOfTargets = 1;
        setAnnounce(true);
    }

    @Override
    public void action() {
        ArrayList<RaidMember> temp  = getOwner().getEnemies().getRandomRaidMember(numOfTargets);

        for (int i = 0; i < temp.size(); i++)   {
            AgonyEffect agonyEffect = new AgonyEffect(getOwner());

            temp.get(i).addStatusEffect(agonyEffect);
        }
    }

    @Override
    public void stop() {
        super.stop();
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
