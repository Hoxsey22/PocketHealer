package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 7/26/2017.
 */

public class HumanForm extends Mechanic{

    public Mechanic linkedMechanic;

    public HumanForm(Boss owner)  {
        super(owner);
        id = 5;
        name = "Human Form";
        damage = 0;
        speed = 30f;
        duration = 0;
    }

    @Override
    public void start() {
        super.start();

        timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                linkedMechanic.start();
                stop();
            }
        },speed);
    }

    public void setLinkedMechanic(Mechanic mechanic) {
        linkedMechanic = mechanic;
    }
}
