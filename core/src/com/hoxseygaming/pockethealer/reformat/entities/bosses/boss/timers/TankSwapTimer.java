package com.hoxseygaming.pockethealer.reformat.entities.bosses.boss.timers;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.reformat.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.reformat.entities.bosses.Boss;

/**
 * Created by Hoxsey on 6/19/2017.
 */
public class TankSwapTimer{

    public Timer timer;
    public RaidMember mainTank;
    public RaidMember offTank;
    public Boss owner;
    public boolean isActive;

    public TankSwapTimer(Boss boss, RaidMember mainTank, RaidMember offTank)  {
        timer = new Timer();
        owner = boss;
        this.mainTank = mainTank;
        this.offTank = offTank;
        isActive = false;
    }

    public void startTimer() {

        isActive = true;
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if( owner.target == mainTank && !offTank.isDead())
                    owner.target = offTank;
                else if (!mainTank.isDead())
                    owner.target = mainTank;
                else if(mainTank.isDead() && offTank.isDead())
                    timer.clear();
            }
        }, 13f, 8f);

    }

    public void stop()  {
        timer.stop();
        timer.clear();
    }
}
