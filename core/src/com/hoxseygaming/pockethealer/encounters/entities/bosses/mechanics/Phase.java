package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

import java.util.ArrayList;

/**
 * Phase is a container for multiple mechanics that also keeps track of how long the mechanics will
 * be applied. The parent, PhaseManager will manage the phase to stop or to start.
 *
 * Created by Hoxsey on 8/20/2017.
 *
 */

public class Phase {

    public PhaseManager parent;
    public Boss owner;
    public float length;
    float delay;
    public boolean isActive;
    public Timer timer;
    public ArrayList<Mechanic> mechanics;

    /**
     * @param owner: The Boss which owns the phase.
     * @param length: The length of which the phase last.
     * @param mechanics: List of all the mechanics that exist in the phase.
     */
    public Phase(Boss owner, float length, Mechanic...mechanics) {
        this.owner = owner;
        this.length = length;
        isActive = false;
        this.mechanics = new ArrayList<>();
        delay = 0f;

        for (Mechanic mech:mechanics) {
            this.mechanics.add(mech);
        }
    }

    /**
     * @param owner: The Boss which owns the phase.
     * @param length: The length of which the phase last.
     * @param mechanics: List of all the mechanics that exist in the phase.
     */
    public Phase(Boss owner, float length, float delay, Mechanic...mechanics) {
        this.owner = owner;
        this.length = length;
        isActive = false;
        this.mechanics = new ArrayList<>();
        this.delay = delay;

        for (Mechanic mech:mechanics) {
            this.mechanics.add(mech);
        }
    }


    /**
     * Starts all mechanics and timer of which the phase will last.
     */
    public void start() {
        timer = new Timer();
        isActive = true;

        startMechanics();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                timer.stop();
                timer.clear();
                stopMechanics();

                parent.nextPhase();
            }
        },length);
    }

    /**
     * Stops the phase.
     */
    public void stop()  {
        timer.stop();
    }

    /**
     * Clears the phase.
     */
    public void clear() {
        timer.clear();
    }

    /**
     * Starts all the mechanics listed in the phase.
     */
    private void startMechanics()   {
        for(int i = 0; i < mechanics.size(); i++)   {
            mechanics.get(i).start();
        }
    }

    /**
     * Stops all the mechanics listed in the phase.
     */
    private void stopMechanics()   {
        for(int i = 0; i < mechanics.size(); i++)   {
            mechanics.get(i).stop();
        }
    }

    /*****************************************************************
     *
     *                      GETTERS AND SETTERS
     *
     ******************************************************************/

    public PhaseManager getParent() {
        return parent;
    }

    public void setParent(PhaseManager parent) {
        this.parent = parent;
    }

    public Boss getOwner() {
        return owner;
    }

    public void setOwner(Boss owner) {
        this.owner = owner;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
