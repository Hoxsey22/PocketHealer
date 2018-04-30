package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.PoisonEffect;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class DoubleAttack extends Mechanic {

    Timer secondTimer;

    public DoubleAttack(Boss owner) {
        super("Double Attack", 20, 2f, owner);
        announce = true;
    }

    public DoubleAttack(Boss owner, float speed)   {
        super("Double Attack", 20, speed, owner);
        announce = true;
    }

    @Override
    public void action() {
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                owner.getTarget().takeDamage(100);
                secondAttack();
            }
        },speed,speed);
    }

    public void secondAttack()  {
        secondTimer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                owner.getTarget().takeDamage(100);
                owner.getTarget().addStatusEffect(new PoisonEffect(owner,8f,8f,0, 0.15f));
                owner.setTarget(owner.getNextThreat());
            }
        },1f);
    }
}
