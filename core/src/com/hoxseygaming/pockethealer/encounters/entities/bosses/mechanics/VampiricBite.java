package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.VampiricBiteEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class VampiricBite extends Mechanic {

    public int numOfTargets;
    public ArrayList<Bleed> bleeds;

    public VampiricBite(Boss owner) {
        super("Vampiric Bite", 20, 5f, owner);
        numOfTargets = 1;
    }

    public VampiricBite(Boss owner, float speed) {
        super("Vampiric Bite", 20, speed, owner);
        numOfTargets = 1;
    }

    @Override
    public void start() {
        super.start();

        bleeds = new ArrayList<>();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                target = owner.getEnemies().getRandomRaidMember(1).get(0);
                target.takeDamage(damage);
                target.addStatusEffect(new VampiricBiteEffect(owner));
                timer.stop();
                timer.clear();
            }
        },speed);
    }

    public int getNumOfTargets() {
        return numOfTargets;
    }

    public void setNumOfTargets(int numOfTargets) {
        this.numOfTargets = numOfTargets;
    }
}
