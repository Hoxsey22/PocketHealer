package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.RipEffect;

/**
 * Created by Hoxsey on 8/4/2017.
 */

public class RipTankSwap extends Mechanic {

    public RipTankSwap(Boss owner) {
        super("Tank Swap",0,8f,owner);
        setMainTank();
        setOffTank();
    }

    public RipTankSwap(Boss owner, float speed) {
        super("Tank Swap",0,speed,owner);
        setMainTank();
        setOffTank();
    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                owner.getTarget().addStatusEffect(new RipEffect(owner,speed));
                tankSwap();

                System.out.println("TANK SWAP!");
            }
        },speed,speed);
    }

    public void tankSwap()  {
        if(offTank.isDead() || mainTank.isDead())   {
            if(mainTank.isDead()) {
                owner.setTarget(owner.getOffTank());
                stop();
                return;
            }
            else    {
                owner.setTarget(owner.getMainTank());
                stop();
                return;
            }
        }

        if( owner.getTarget().equals(mainTank)) {
            owner.setTarget(owner.getOffTank());
        }
        else {
            owner.setTarget(owner.getMainTank());
        }
    }
}
