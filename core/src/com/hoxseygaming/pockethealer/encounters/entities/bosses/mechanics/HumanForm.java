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
        id = 6;
        create();
    }

    @Override
    public void create() {
        super.create();
        name = "Human Form";
        announcementString = "Wampus Cat is in her car form!";
        damage = 0;
        speed = 30f;
        duration = 0;

    }

    @Override
    public void start() {
        super.start();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                linkedMechanic.start();
                owner.announcement.setText(announcementString);
                stop();
            }
        },speed);
    }

    public void setLinkedMechanic(Mechanic mechanic) {
        linkedMechanic = mechanic;
    }
}
