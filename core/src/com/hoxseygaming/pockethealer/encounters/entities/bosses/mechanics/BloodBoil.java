package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 7/12/2017.
 */

public class BloodBoil extends Mechanic {

    public Timer applyTimer;
    public int numOfTargets;

    public BloodBoil(Boss owner) {
        super("Blood Boil",20, 12f, owner);
        debuff = Debuff.BOIL;
    }

    public BloodBoil(Boss owner, float speed) {
        super("Blood Boil", 20, speed, owner);
        debuff = Debuff.BOIL;
    }

    @Override
    public void start() {
        super.start();
        applyTimer.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                ArrayList<RaidMember> raidMembers = raid.getRandomRaidMember(numOfTargets);
                for (int i = 0; i < raidMembers.size(); i++)  {
                    target = raidMembers.get(i);
                    startDebuff();
                }

            }
        },speed,speed);
    }

    public void startDebuff()  {
        applyTimer = new Timer();
        applyMechanic();

        final RaidMember tar = target;
        applyTimer.scheduleTask(new Timer.Task() {
            int count = 0;

            @Override
            public void run() {
                count++;
                if(tar.getHpPercent() > 0.99)    {
                    raid.takeDamage(damage);
                    tar.removeEffect(debuff);
                    applyTimer.stop();
                    applyTimer.clear();

                }

                if(count >= (1f/0.01f) * 15)    {
                    tar.removeEffect(debuff);
                    applyTimer.stop();
                    applyTimer.clear();
                }
            }
        },0.01f,0.01f);
    }
}
