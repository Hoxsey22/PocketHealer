package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class ThunderStorm extends Mechanic{

    Timer powerlessTimer;

    public ThunderStorm(Boss owner) {
        super("Thunder Storm", 0, 35f, owner);
        announce = true;
    }

    public ThunderStorm(Boss owner, float speed) {
        super("Thunder Storm", 0, speed, owner);
        announce = true;
    }

    @Override
    public void action() {
        for(int i = 0; i < getRaid().raidMembers.size(); i++)   {
            getRaid().getRaidMember(i).takeDamage(100);
        }
        startPowerlessTimer();
        parentPhase.pauseMechanics();

    }

    public void startPowerlessTimer()    {

        owner.announcement.setText(owner.getName()+" is powerless for just 7 seconds!");
        powerlessTimer = new Timer();

        powerlessTimer.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                powerlessTimer.stop();
                powerlessTimer.clear();
                owner.announcement.setText("");
                //timer.start();
                resume();
            }
        },7f,7f,1);
    }

    @Override
    public void stop() {
        super.stop();
        if(powerlessTimer != null){
            powerlessTimer.stop();
            powerlessTimer.clear();
        }
    }
}
