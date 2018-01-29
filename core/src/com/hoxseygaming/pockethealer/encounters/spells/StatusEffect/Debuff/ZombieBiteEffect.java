package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 1/5/2018.
 */

public class ZombieBiteEffect extends HealingAbsorbEffect {
    private Timer damageTimer;
    private int progressiveDamage;

    /**
     * A debuff is a negative status effect that is commonly from a boss and is
     * put on a raid member.
     *
     * @param owner    : The owner of the debuff.
     */
    public ZombieBiteEffect(Boss owner) {
        super(owner, 100);
        setIcon(owner.assets.getTexture(owner.assets.biteIcon));
        setDescription("Zombie bite is a healing absorb and if not healed din time, " +
                "the member will die and become one with the horde of zombies.");
    }


    /**
     * A debuff is a negative status effect that is commonly from a boss and is
     * put on a raid member.
     *
     * @param owner    : The owner of the debuff.
     * @param modValue : The value of the debuff.
     */
    public ZombieBiteEffect(Boss owner, int modValue) {
        super(owner, modValue);
        setIcon(owner.assets.getTexture(owner.assets.biteIcon));
        setDescription("Zombie bite is a healing absorb and if not healed din time, " +
                "the member will die and become one with the horde of zombies.");
    }

    @Override
    public void startConditions() {
        super.startConditions();
        startDamage();
    }

    @Override
    public void additionalConditions() {
        super.additionalConditions();

        if(getTarget().isDead())    {
            getOwner().setDamage(getOwner().getDamage()+5);
            getOwner().setHp(getOwner().getHp()+(int)(getOwner().getMaxHp()*0.1f));
            remove();
        }
    }

    private void startDamage()  {
        damageTimer = new Timer();
        progressiveDamage = 5;
        damageTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                progressiveDamage += 2;
                getTarget().takeDamage(progressiveDamage);

            }
        },2f, 2f);



    }
}
