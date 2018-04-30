package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.BurnEffect;

import java.util.Random;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class FireBreath extends Mechanic{


    public Timer channel;
    private Random dice;

    public FireBreath(Boss owner) {
        super("Fire Breath", 10, 20f, owner);
        dice = new Random();
        announce = true;
    }

    public FireBreath(Boss owner, int damage, float speed) {
        super("Fire Breath", damage, speed, owner);
        dice = new Random();
        announce = true;
    }

    @Override
    public void action() {
        //pausePhase();
        startChannel();
        //timer.stop();
        pause();
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
                        if(dice.nextInt(100)+0 > 94)    {
                            owner.getEnemies().getRaidMember(i).addStatusEffect(new BurnEffect(owner));
                        }
                    }
                    //getRaid().takeDamage(damage);
                }
                else    {
                    channel.stop();
                    channel.clear();
                    //timer.start();
                    resume();
                }
            }
        },0.5f,0.5f,4);
    }
}
