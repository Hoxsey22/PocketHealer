package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3.MotherSpider;
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
    Timer feedingTimer;

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
        for (int i = 0; i < owner.getEnemies().raidMembers.size(); i++) {
            owner.getEnemies().raidMembers.get(i).addStatusEffect(new WebEffect(owner,100));
        }
        startFeedingTime();
        //timer.stop();
        pause();
    }

    public void startFeedingTime()  {
        feedingTimer = new Timer();

        feedingTimer.scheduleTask(new Timer.Task() {
            int counter = 0;
            @Override
            public void run() {
                counter++;
                if(counter == ((length-1.5f)*10))  {
                    owner.announcement.setText(getOwner().getName()+" is about to consumer her victims!");
                }
                if(counter == ((length)*10)) {
                    ArrayList<RaidMember> targets = owner.getEnemies().getStatusEffectedRaidMembers("Web Effect");
                    for (int i = 0; i < targets.size(); i++) {
                        targets.get(i).takeDamage(targets.get(i).getHp());
                        owner.receiveHealing(100);
                    }
                    if(targets.size() > 0)  {
                        owner.announcement.setText(getOwner().getName()+" has eaten her victims and has restored health!");
                    }
                    else    {
                        owner.announcement.setText(getOwner().getName()+" is now hangry!");
                        owner.setDamage(owner.getDamage()+5);
                        MotherSpider ms = (MotherSpider) owner;
                        ms.leap.setDamage(owner.getDamage()*2);
                        ms.autoAttack.setDamage(owner.getDamage());
                    }
                    feedingTimer.stop();
                    feedingTimer.clear();
                    //timer.start();
                    resume();
                }
            }
        },0.1f, 0.1f);
    }

    @Override
    public void stop() {
        super.stop();
        if (feedingTimer != null) {
            feedingTimer.stop();
            feedingTimer.clear();
        }
    }
}
