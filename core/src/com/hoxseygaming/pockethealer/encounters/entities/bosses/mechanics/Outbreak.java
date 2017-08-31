package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/23/2017.
 */

public class Outbreak extends Mechanic {

    int numOfTargets;

    public Outbreak(Boss owner) {
        super("Outbreak", 0, 2f, owner);
    }

    public Outbreak(Boss owner, float speed) {
        super("Outbreak", 0, speed, owner);
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if(hasDiseases())   {
                    ArrayList<RaidMember> temp = getRaid().getRandomRaidMember(3);
                    for(int i = 0; i < temp.size(); i++)   {
                        Disease disease = new Disease(owner);
                        disease.setTarget(temp.get(i));
                        disease.start();
                    }
                }
                else {
                    ArrayList<RaidMember> temp = getRaid().getRandomRaidMember(1);
                    Disease disease = new Disease(owner);
                    disease.setTarget(temp.get(0));
                    disease.start();
                }
            }
        },speed,speed);
    }

    public boolean hasDiseases()    {

        for(int i = 0;  i < getRaid().raidMembers.size(); i++)   {
            if(getRaid().getRaidMember(i).containsEffects(Debuff.DISEASE))    {
                return true;
            }
        }
        return false;
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTarget(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
