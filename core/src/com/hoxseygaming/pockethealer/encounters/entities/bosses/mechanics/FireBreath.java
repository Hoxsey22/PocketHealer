package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.BurnEffect;

import java.util.Random;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class FireBreath extends Mechanic{


    private Timer channel;
    private Random dice;

    public FireBreath(Boss owner) {
        super("Fire Breath", 10, 20f, owner);
        dice = new Random();
        setAnnounce(true);
    }

    public FireBreath(Boss owner, int damage, float speed) {
        super("Fire Breath", damage, speed, owner);
        dice = new Random();
        setAnnounce(true);
    }

    @Override
    public void action() {
        startChannel();
        pausePhase();
    }

    public void startChannel()  {
        channel = new Timer();

        channel.schedule(new Timer.Task() {
            int count =  0;

            @Override
            public void run() {
                if(count != 5) {
                    count++;
                    for(int i = 0; i <  getOwner().getEnemies().getRaidMembers().size(); i++)   {
                        getOwner().getEnemies().getRaidMember(i).takeDamage(getDamage());
                        if(dice.nextInt(100)+0 > 94)    {
                            getOwner().getEnemies().getRaidMember(i).addStatusEffect(new BurnEffect(getOwner()));
                        }
                    }
                }
                else    {
                    channel.stop();
                    channel.clear();
                    resumePhase();
                }
            }
        },0.5f,0.5f,5);
    }

    @Override
    public void stop() {
        super.stop();
        if(channel != null)    {
            channel.stop();
            channel.clear();
        }
    }
}
