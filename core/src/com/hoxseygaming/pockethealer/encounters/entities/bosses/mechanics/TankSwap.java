package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.Debuff;

/**
 * Created by Hoxsey on 8/4/2017.
 */

public class TankSwap extends Mechanic {

    public Debuff debuff;

    public TankSwap(Boss owner) {
        super("Tank Swap",0,8f,owner);
        setMainTank();
        setOffTank();
        bgMech = true;
    }

    public TankSwap(Boss owner, float speed) {
        super("Tank Swap",0,speed,owner);
        setMainTank();
        setOffTank();
        bgMech = true;
    }

    public TankSwap(Boss owner, float speed, Debuff debuff) {
        super("Tank Swap",0,speed,owner);
        this.debuff = debuff;
        setMainTank();
        setOffTank();
        bgMech = true;
    }

    @Override
    public void action() {
        if(debuff != null)    {
            owner.getTarget().addStatusEffect(debuff);
        }
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
