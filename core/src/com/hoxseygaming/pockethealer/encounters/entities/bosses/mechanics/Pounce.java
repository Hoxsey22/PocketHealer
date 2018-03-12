package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.BleedEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 7/26/2017.
 */

public class Pounce extends Mechanic {

    int numOfTargets;

    public Pounce(Boss owner) {
        super("Pounce",30,4f,owner);
        id = 4;
        numOfTargets = 3;
    }

    public Pounce(Boss owner, float speed) {
        super("Pounce",30,speed,owner);
        id = 4;
    }

    @Override
    public void start() {
        super.start();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ArrayList<RaidMember> temp  = getRaid().getRandomRaidMember(3);

                for (int i = 0; i < temp.size(); i++)   {
                    if(temp.get(i) != null) {
                        temp.get(i).takeDamage(damage);
                        temp.get(i).addStatusEffect(new BleedEffect(owner));
                    }
                }

            }
        },speed, speed);
    }

    @Override
    public void applyMechanic(){
    }


    public void stopBleeds() {
        super.stop();
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
