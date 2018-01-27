package com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics;

import java.util.ArrayList;

/**
 * PhaseManager navigates through all the phases of which the boss has.
 *
 * Created by Hoxsey on 1/10/2018.
 *
 */

public class PhaseManager {

    public int index;
    public ArrayList<Phase> phases;

    public PhaseManager()   {
        phases = new ArrayList<>();
        index = 0;
    }

    /**
     * Adds a phase to the manager.
     *
     * @param newPhase: The phase that is being added.
     */
    public void addPhase(Phase newPhase)    {
        newPhase.setParent(this);
        phases.add(newPhase);
    }

    /**
     * Navigates to the next phase.
     */
    public void nextPhase() {
        System.out.println("Phase "+index+" stopped!");
        index++;
        if(index == phases.size())  {
            index = 0;
        }
        startPhase();

    }

    /**
     * Starts the phase that is indexed.
     */
    public void startPhase()    {
        if(phases.size() > 0) {
            phases.get(index).start();
            System.out.println("Phase "+index+" started!");
        }
    }

    /**
     * Resets all the phases.
     */
    public void reset() {
        cleanPhases();
        index = 0;
    }

    /**
     * Clears all the Phase's timer.
     */
    public void cleanPhases()  {
        for(int i = 0; i < phases.size(); i++)  {
            phases.clear();
        }
    }




}
