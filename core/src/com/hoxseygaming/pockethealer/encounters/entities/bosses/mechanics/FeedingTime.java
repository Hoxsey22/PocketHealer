package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
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

    public FeedingTime(Boss owner) {
        super("Feeding Time", 0, 5f, owner);
    }

    public FeedingTime(Boss owner, float speed, float length) {
        super("Feeding Time", 0, speed, owner);
        this.length = length;
    }

    @Override
    public void start() {
        super.start();
        announcementString = owner.getName()+ " is about to eat her victims!";
        startAnnouncementTimer();

        isStart = false;
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
            if(!isStart) {
                for (int i = 0; i < owner.getEnemies().raidMembers.size(); i++) {
                    owner.getEnemies().raidMembers.get(i).addStatusEffect(new WebEffect(owner,100));
                }
                isStart = true;

            }
            else    {
                ArrayList<RaidMember> targets = owner.getEnemies().getDebuffRaidMembers("Web Effect");
                for(int i = 0; i < targets.size(); i++)   {
                    targets.get(i).takeDamage(targets.get(i).getHp());
                    owner.receiveHealing(100);
                }
                isStart = false;
                announcementString = "";
                announcementTimer.stop();
            }


            }
        },speed,length,2);
    }
}
