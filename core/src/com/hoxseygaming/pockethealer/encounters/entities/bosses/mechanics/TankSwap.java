package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 8/4/2017.
 */

public class TankSwap extends Mechanic {

    public TankSwap(Boss owner) {
        super(owner);
        id = 2;
        speed = 8f;
        setMainTank();
        setOffTank();
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                tankSwap();
                System.out.println("TANK SWAP!");
            }
        },speed,speed);
    }

    public void tankSwap()  {
        if(offTank.isDead() || mainTank.isDead())   {
            if(mainTank.isDead()) {
                owner.setTarget(offTank);
                stop();
                return;
            }
            else    {
                owner.setTarget(mainTank);
                stop();
                return;
            }
        }

        if( owner.getTarget() == mainTank) {
            owner.setTarget(offTank);
        }
        else {
            owner.setTarget(mainTank);
        }
    }
}
