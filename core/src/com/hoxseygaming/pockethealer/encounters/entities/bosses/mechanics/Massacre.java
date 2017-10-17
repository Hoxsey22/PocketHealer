package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class Massacre extends Mechanic{


    public Timer channel;

    public Massacre(Boss owner) {
        super("Massacre", 0, 35f, owner);
    }

    public Massacre(Boss owner, float speed) {
        super("Massacre", 0, speed, owner);
    }

    @Override
    public void start() {
        super.start();
        startAnnouncementTimer();


        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                announcementTimer.stop();
                startChannel();
                stopMechanics();
            }
        },speed, speed);

    }

    public void startChannel()  {
        channel = new Timer();

        channel.schedule(new Timer.Task() {
            int count =  0;
            @Override
            public void run() {
                if(count != 3) {
                    count++;
                    for(int i = 0; i < getRaid().raidMembers.size(); i++)   {
                        if(getRaid().getRaidMember(i).getHpPercent() > 0.1f)
                            getRaid().getRaidMember(i).takeDamage(getRaid().getRaidMember(i).getHp()-1);
                        else    {
                            getRaid().getRaidMember(i).takeDamage(100);
                        }
                    }
                }
                else    {
                    channel.stop();
                    channel.clear();
                    startAnnouncementTimer();
                    startMechanics();
                }
            }
        },6f,6f,3);
    }

    public void stopMechanics() {
        for(int i = 0; i < owner.getMechanics().size(); i++)   {
            owner.getMechanics().get(i).stop();
        }
    }

    public void startMechanics() {
        for(int i = 0; i < owner.getMechanics().size(); i++)   {
            owner.getMechanics().get(i).start();
        }
    }
}
