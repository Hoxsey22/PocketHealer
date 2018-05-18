package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3.Hydra;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.SunderEffect;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class DoubleAttack extends Mechanic {

    Timer secondTimer;
    Hydra hydra;

    public DoubleAttack(Boss owner) {
        super("Double Attack", 20, 2f, owner);
        announce = true;
        hydra = (Hydra) owner;
    }

    public DoubleAttack(Boss owner, float speed)   {
        super("Double Attack", 20, speed, owner);
        announce = true;
        hydra = (Hydra) owner;
    }

    @Override
    public void action() {
        owner.getTarget().takeDamage(200);
        //hydra.autoAttack.stop();
        secondAttack();
        pausePhase();
    }

    public void secondAttack()  {
        secondTimer = new Timer();

        secondTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                System.out.println("Second Attack");

                owner.getTarget().takeDamage(200);
                owner.getTarget().addStatusEffect(new SunderEffect(owner));
                owner.setTarget(owner.getNextThreat());

                secondTimer.stop();
                secondTimer.clear();
                //hydra.autoAttack.resume();
                resumePhase();
            }
        },1.5f,1.5f,1);
    }

    @Override
    public void stop() {
        super.stop();
        if(secondTimer != null) {
            secondTimer.stop();
            secondTimer.clear();
        }
    }
}
