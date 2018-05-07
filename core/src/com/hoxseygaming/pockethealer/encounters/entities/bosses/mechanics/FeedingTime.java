package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.WebEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/23/2017.
 */

public class FeedingTime extends Mechanic {

    float length;
    private boolean isStart;
    float totalTime;

    public FeedingTime(Boss owner) {
        super("Feeding Time", 0, 5f, owner);
        isStart = false;
        announce = true;
    }

    public FeedingTime(Boss owner, float speed, float length) {
        super("Feeding Time", 0, speed, owner);
        this.length = length;
        isStart = false;
        announce = true;
    }

    @Override
    public void action() {
        totalTime += speed;

        if(!isStart) {
            for (int i = 0; i < owner.getEnemies().raidMembers.size(); i++) {
                owner.getEnemies().raidMembers.get(i).addStatusEffect(new WebEffect(owner,100));
            }
            isStart = true;

        }
        else    {
            ArrayList<RaidMember> targets = owner.getEnemies().getStatusEffectedRaidMembers("Web Effect");
            for(int i = 0; i < targets.size(); i++)   {
                targets.get(i).takeDamage(targets.get(i).getHp());
                owner.receiveHealing(100);
            }
            isStart = false;
        }
    }
}
