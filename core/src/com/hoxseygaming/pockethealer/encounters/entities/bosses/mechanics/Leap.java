package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.VenomEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class Leap extends Mechanic{


    public Timer leapTimer;
    public int numOfTargets;
    private ArrayList<RaidMember> targets;

    public Leap(Boss owner) {
        super("Leap", 40, 15f, owner);
        numOfTargets = 5;
        announce = true;
    }

    public Leap(Boss owner, int damage, float speed, int numOfTargets) {
        super("Leap", damage, speed, owner);
        this.numOfTargets = numOfTargets;
        announce = true;
    }

    @Override
    public void action() {
        startChannel();
    }

    public void startChannel()  {
        leapTimer = new Timer();

        targets = owner.getEnemies().getRandomRaidMember(numOfTargets);
        leapTimer.schedule(new Timer.Task() {
            int count =  0;

            @Override
            public void run() {
                if(count != targets.size()) {
                    targets.get(count).takeDamage(damage);
                    targets.get(count).addStatusEffect(new VenomEffect(owner));
                }
                else    {
                    leapTimer.stop();
                    leapTimer.clear();
                    timer.start();
                    announcementTimer.start();
                }
                count++;
            }
        },0.5f,0.5f,targets.size());
    }
}
