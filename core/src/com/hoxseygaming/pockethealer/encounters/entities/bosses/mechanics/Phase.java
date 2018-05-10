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

    public String name;
    public PhaseManager parent;
    public Boss owner;
    public float length;
    public float percentage;
    float delay;
    public boolean isActive;
    public Timer timer;
    public ArrayList<Mechanic> mechanics;
    public boolean isTimed;

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
        isTimed = true;

        for (Mechanic mech:mechanics) {
            mech.setParentPhase(this);
            this.mechanics.add(mech);
        }
    }

    /**
     * @param owner: The Boss which owns the phase.
     * @param length: The length of which the phase last.
     * @param mechanics: List of all the mechanics that exist in the phase.
     */
    public Phase(Boss owner, String name, float length, Mechanic...mechanics) {
        this.owner = owner;
        this.length = length;
        this.name = name;
        isActive = false;
        this.mechanics = new ArrayList<>();
        delay = 0f;
        isTimed = true;

        for (Mechanic mech:mechanics) {
            mech.setParentPhase(this);
            this.mechanics.add(mech);
        }
    }

    /**
     * @param owner: The Boss which owns the phase.
     * @param mechanics: List of all the mechanics that exist in the phase.
     */
    public Phase(Boss owner, int percentage, Mechanic...mechanics) {
        this.owner = owner;
        this.percentage = (float)percentage/100;
        isActive = false;
        this.mechanics = new ArrayList<>();
        delay = 0f;
        isTimed = false;

        for (Mechanic mech:mechanics) {
            this.mechanics.add(mech);
        }
    }

    /**
     * @param owner: The Boss which owns the phase.
     * @param mechanics: List of all the mechanics that exist in the phase.
     */
    public Phase(Boss owner,String name, int percentage, Mechanic...mechanics) {
        this.owner = owner;
        this.percentage = (float)percentage/100;
        this.name = name;
        isActive = false;
        this.mechanics = new ArrayList<>();
        delay = 0f;
        isTimed = false;

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
        if(name == "") {
            Timer phaseTitleTimer = new Timer();
            getOwner().announcement.setText(name);
            phaseTitleTimer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    getOwner().announcement.setText("");
                }
            }, 1f, 1);
        }
        startMechanics();

        if(isTimed) {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    timer.stop();
                    timer.clear();
                    stopMechanics();

                    parent.nextPhase();
                }
            }, length);
        }
        else    {
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    if(owner.getHpPercent() <= percentage ) {
                        timer.stop();
                        timer.clear();
                        stopMechanics();

                        parent.nextPhase();
                    }
                }
            },0.1f,0.1f);
        }
    }

    /**
     * Stops the phase.
     */
    public void stop()  {
        if(timer != null)   {
            timer.stop();
        }
        if(mechanics != null)    {
            for(int i = 0; i < mechanics.size(); i++)   {
                mechanics.get(i).stop();
            }
        }

    }

    /**
     * Clears the phase.
     */
    public void clear() {
        if(timer != null) {
            stop();
            timer.clear();
        }
        if(mechanics != null)   {
            for(int i = 0; i < mechanics.size(); i++)   {
                mechanics.remove(i);
            }
        }
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
        owner.announcement.setText("");
    }

    public void pauseMechanics(Mechanic currentMechanic)    {
        long millis=System.currentTimeMillis();
        java.util.Date date=new java.util.Date(millis);

        System.out.println(date+" :"+currentMechanic.getName()+" paused the other mechanics.");
        for(int i = 0; i < mechanics.size(); i++)   {
            if(!currentMechanic.getName().equalsIgnoreCase(mechanics.get(i).getName()) && !mechanics.get(i).bgMech) {
                mechanics.get(i).pause();
            }
        }
    }

    public void resumeMechanics()   {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                for(int i = 0; i < mechanics.size(); i++)   {
                    if(!mechanics.get(i).bgMech) {
                        mechanics.get(i).resume();
                    }
                }
            }
        },1f,1f,1);

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
