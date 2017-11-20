package com.hoxseygaming.pockethealer;

/**
 * Created by Hoxsey on 11/20/2017.
 */

public class HealingTracker {

    public int healingDone;
    public int totalHealingDone;

    public HealingTracker() {
        healingDone = 0;
        totalHealingDone = 0;
    }

    public void addHealingDone(int hd)    {
        healingDone += hd;
    }

    public void addTotalHealingDone(int thd)    {
        totalHealingDone += thd;
    }

    public int getOverHealing() {
        return totalHealingDone - healingDone;
    }

    public int getOverHealingPercent()  {
        return (int)(100*(float)(getOverHealing()/totalHealingDone));
    }

    public void addHealingTracker(HealingTracker healingTracker)    {
        addHealingDone(healingTracker.healingDone);
        addTotalHealingDone(healingTracker.totalHealingDone);
    }

    public void clear() {
        healingDone = 0;
        totalHealingDone = 0;
    }



}
