package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 7/12/2017.
 */

public class Poison extends Mechanic {


    public Poison(Boss owner) {
        super(owner);
        create();
    }

    public Poison(Boss owner, RaidMember target) {
        super(owner);
        create();
        this.target = target;
    }

    @Override
    public void create() {
        name = "Poison";
        debuff = Debuff.POISON;
        damage = 10;
        speed = 2f;
        super.create();
    }

    @Override
    public void start() {
        super.start();
        applyMechanic();

        final RaidMember tar = target;
        timer.scheduleTask(new Timer.Task() {
            int count = 0;

            @Override
            public void run() {
                count++;
                if(tar.getHpPercent() > 0.9)    {
                    stop();
                    tar.removeEffect(Debuff.BLEED);
                }

                if(count % (speed/0.01f)   == 0)    {
                    tar.takeDamage(damage);
                }
            }
        },0.01f,0.01f);
    }

    @Override
    public void applyMechanic() {
        super.applyMechanic();
        target.applyEffect(Debuff.BLEED);
    }

    public void stop()  {
        timer.stop();
        timer.clear();
    }
}
