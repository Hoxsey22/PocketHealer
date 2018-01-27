package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.ConsumingShadowEffect;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class SwarmingShadow extends Mechanic{


    public Timer channel;
    public Timer consumingShadowTimer;
    private Random dice;

    public SwarmingShadow(Boss owner) {
        super("Swarming Shadow", 10, 15f, owner);
    }

    public SwarmingShadow(Boss owner, int damage, float speed) {
        super("Swarming Shadow", damage, speed, owner);
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                startChannel();
                timer.stop();
            }
        },5, speed,3);

    }

    public void startChannel()  {
        channel = new Timer();

        channel.schedule(new Timer.Task() {
            int count =  0;
            @Override
            public void run() {
                if(count != 4) {
                    count++;
                    for(int i = 0; i <  owner.getEnemies().raidMembers.size(); i++)   {
                        owner.getEnemies().getRaidMember(i).takeDamage(damage);
                    }
                    //getRaid().takeDamage(damage);
                }
                else    {
                    channel.stop();
                    channel.clear();
                    timer.start();
                }
            }
        },0.5f,0.5f,4);
    }

    public void startConsumingShadowTimer() {

        consumingShadowTimer = new Timer();

        consumingShadowTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ArrayList<RaidMember> targets = owner.getEnemies().getRandomRaidMember(4, owner.getEnemies().getDebuffLessRaidMembers("Consuming Shadow"));
                for(int i = 0; i < targets.size(); i++){
                    targets.get(i).addStatusEffect(new ConsumingShadowEffect(owner));
                }
            }
        }, 2f,8f);
    }

    @Override
    public void stop() {
        super.stop();
        consumingShadowTimer.stop();
        consumingShadowTimer.clear();
    }
}
