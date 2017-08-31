package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class BloodBite extends Mechanic {

    public int numOfTargets;
    public ArrayList<Bleed> bleeds;

    public BloodBite(Boss owner) {
        super("Blood Bite", 20, 2f, owner);
        debuff = Debuff.BITTEN;
        numOfTargets = 1;
    }

    public BloodBite(Boss owner, float speed) {
        super("Blood Bite", 20, speed, owner);
        debuff = Debuff.BITTEN;
        numOfTargets = 1;
    }

    @Override
    public void start() {
        super.start();
        bleeds = new ArrayList<>();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ArrayList<RaidMember> temp;
                if(numOfTargets == 1) {
                    temp = getRaid().getRandomRaidMember(numOfTargets, getRaid().getDebuffLessRaidMembers(Debuff.BITTEN));
                    numOfTargets = numOfTargets * 2;
                }
                else    {
                    temp = getRaid().getRandomRaidMember(numOfTargets,getRaid().getDebuffLessRaidMembers(Debuff.BITTEN));
                    numOfTargets = numOfTargets * 2;
                }
                for(int i = 0; i < temp.size(); i++) {
                    target = temp.get(i);
                    temp.get(i).takeDamage(damage);
                    temp.get(i).setDamage(temp.get(i).getDamage()*3);
                    applyMechanic();
                    Bleed bleed = new Bleed(owner,5f);
                    bleed.setTarget(temp.get(i));
                    bleed.setDamage(1);
                    bleeds.add(bleed);
                    bleed.start();
                }
            }
        },speed,speed);
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
