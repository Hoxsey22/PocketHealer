package com.hoxseygaming.pockethealer.encounters.entities.bosses.timers;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 6/19/2017.
 */
public class TankSwapTimer{

    public Timer timer;
    public RaidMember mainTank;
    public RaidMember offTank;
    public Boss owner;
    public boolean isActive;
    public AutoAttackTimer autoAttackTimer;

    public TankSwapTimer(Boss boss, RaidMember mainTank, RaidMember offTank, float autoAttackTime)  {
        timer = new Timer();
        owner = boss;

        this.mainTank = mainTank;
        this.offTank = offTank;

        autoAttackTimer = new AutoAttackTimer(owner, autoAttackTime);

        isActive = false;

    }

    public void startTimer() {
        autoAttackTimer.startTimer();

        isActive = true;
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                tankSwap();
            }
        }, 13f, 8f);

    }

    public void tankSwap()  {
        if(offTank.isDead() || mainTank.isDead())   {
            if(mainTank.isDead()) {
                owner.target =  offTank;
                stop();
                return;
            }
            else    {
                owner.target = mainTank;
                stop();
                return;
            }
        }

        if( owner.target == mainTank) {
            owner.target = offTank;
        }
        else {
            owner.target = mainTank;
        }
    }

    public void stop()  {
        timer.stop();
        timer.clear();
        isActive = false;
        System.out.println("Tank Swap Timer has stopped!");
    }
}
