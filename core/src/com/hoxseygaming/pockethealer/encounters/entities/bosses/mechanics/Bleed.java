package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 7/12/2017.
 */

public class Bleed extends Mechanic {


    public Bleed( Boss owner) {
        super(owner);
        create();
    }

    public Bleed(Boss owner, RaidMember target) {
        super(owner);
        create();
        this.target = target;
    }

    @Override
    public void create() {
        id = 2;
        name = "Bleed";
        debuff = Debuff.BLEED;
        damage = 5;
        speed = 3f;
        super.create();
    }

    public void amplify()   {
        damage = damage + 5;
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
                    amplify();
                    System.out.println(name+" tick, "+count);
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
