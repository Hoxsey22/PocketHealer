package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.RipEffect;

/**
 * Created by Hoxsey on 8/4/2017.
 */

public class RipTankSwap extends Mechanic {

    public RipTankSwap(Boss owner) {
        super("Rip Tank Swap",0,8f,owner);
        setMainTank();
        setOffTank();
        setBgMech(true);
    }

    public RipTankSwap(Boss owner, float speed) {
        super("Rip Tank Swap",0,speed,owner);
        setMainTank();
        setOffTank();
        setBgMech(true);
    }

    @Override
    public void action() {
        getOwner().getTarget().addStatusEffect(new RipEffect(getOwner(),getSpeed()));
        tankSwap();

        System.out.println("TANK SWAP!");
    }

    public void tankSwap()  {
        if(getOffTank().isDead() || getMainTank().isDead())   {
            if(getMainTank().isDead()) {
                getOwner().setTarget(getOwner().getOffTank());
                stop();
                return;
            }
            else    {
                getOwner().setTarget(getOwner().getMainTank());
                stop();
                return;
            }
        }

        if( getOwner().getTarget().equals(getMainTank())) {
            getOwner().setTarget(getOwner().getOffTank());
        }
        else {
            getOwner().setTarget(getOwner().getMainTank());
        }
    }
}
