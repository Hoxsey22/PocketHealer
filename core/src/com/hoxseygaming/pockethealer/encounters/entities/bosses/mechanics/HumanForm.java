package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 7/26/2017.
 */

public class HumanForm extends Mechanic{

    public Mechanic linkedMechanic;

    public HumanForm(Boss owner)  {
        super("Human Form",0,30f,owner);
        announcementString = "Wampus Cat is in her car form!";
    }

    @Override
    public void start() {
        super.start();
        startAnnouncementTimer();
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
