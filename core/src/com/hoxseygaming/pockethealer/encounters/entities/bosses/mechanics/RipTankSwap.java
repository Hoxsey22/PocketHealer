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
        bgMech = true;
    }

    public RipTankSwap(Boss owner, float speed) {
        super("Rip Tank Swap",0,speed,owner);
        setMainTank();
        setOffTank();
        bgMech = true;
    }

    @Override
    public void action() {
        owner.getTarget().addStatusEffect(new RipEffect(owner,speed));
        tankSwap();

        System.out.println("TANK SWAP!");
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
