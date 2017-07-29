package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 7/26/2017.
 */

public class CatForm extends Mechanic{

    public Mechanic linkedMechanic;
    public Pounce pounce;

    public CatForm(Boss owner) {
        super(owner);
        id = 4;
        name = "Cat Form";
        damage = 0;
        speed = 30f;
        duration = 0;
        pounce = new Pounce(owner);
    }

    @Override
    public void start() {
        super.start();

        pounce.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                pounce.stop();
                linkedMechanic.start();
                stop();
            }
        },speed);
    }

    public void setLinkedMechanic(Mechanic mechanic) {
        linkedMechanic = mechanic;
    }

    @Override
    public void stop() {
        super.stop();
        pounce.stop();
    }
}
