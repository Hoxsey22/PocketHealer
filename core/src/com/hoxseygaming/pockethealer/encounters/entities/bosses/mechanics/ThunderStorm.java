package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 8/2/2017.
 */

public class ThunderStorm extends Mechanic{

    private Timer powerlessTimer;

    public ThunderStorm(Boss owner) {
        super("Thunder Storm", 0, 35f, owner);
        setAnnounce(true);
    }

    public ThunderStorm(Boss owner, float speed) {
        super("Thunder Storm", 0, speed, owner);
        setAnnounce(true);
    }

    @Override
    public void action() {
        for(int i = 0; i < getRaid().getRaidMembers().size(); i++)   {
            getRaid().getRaidMember(i).takeDamage(100);
        }
        startPowerlessTimer();
        getParentPhase().pauseMechanics();

    }

    public void startPowerlessTimer()    {

        getOwner().getAnnouncement().setText(getOwner().getName()+" is powerless for just 7 seconds!");
        powerlessTimer = new Timer();

        powerlessTimer.scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                powerlessTimer.stop();
                powerlessTimer.clear();
                getOwner().getAnnouncement().setText("");
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
