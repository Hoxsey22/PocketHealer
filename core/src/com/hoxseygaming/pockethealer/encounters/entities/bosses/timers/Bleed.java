package com.hoxseygaming.pockethealer.encounters.entities.bosses.timers;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 7/12/2017.
 */

public class Bleed extends Mechanic {

    public int damage;

    public Bleed( Boss owner) {
        super(1, owner);
    }

    @Override
    public void startTimer() {
        super.startTimer();
    }

    @Override
    public void applyMechanic() {
        super.applyMechanic();
    }
}
