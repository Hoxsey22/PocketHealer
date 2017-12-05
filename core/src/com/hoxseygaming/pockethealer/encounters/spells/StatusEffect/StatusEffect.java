package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 11/28/2017.
 */

public abstract class StatusEffect {

    private String name;
    private int id;
    private String description;
    private boolean isActive;
    private RaidMember target;
    private Texture icon;
    private float duration;
    private float speed;
    private float startTime;
    private int modValue;
    private int numOfTicks;
    private boolean dispellable;
    private Timer timer;
    private Assets assets;
    private StatusEffectList parent;

    /**
     * @param id: ID of the status effect and should be unique.
     * @param name: Name of the status effect.
     * @param description: A brief description of the status effect.
     * @param texture: The texture that will create the icon.
     * @param duration: The time of which the status effect will last.
     * @param speed: The time of which the status effect will apply effect.
     * @param modValue: The mod value that will change a specific stat.
     * @param dispellable : Check if this status effect can be dispel.
     */
    public StatusEffect(int id, String name, String description, Texture texture, float duration, float speed, int modValue, boolean dispellable, Assets assets)   {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = texture;
        this.duration = duration;
        this.speed = speed;
        this.modValue = modValue;
        isActive = true;
        numOfTicks = (int)(duration/speed);
        this.dispellable = dispellable;
        timer = new Timer();
        this.assets = assets;
        //this.parent = parent;
    }

    public void start() {
        timer.scheduleTask(new Timer.Task() {
            int currentCount;
            int tickCount = (int)(speed/0.1f);
            int durationCount = (int)(duration/0.1f);
            @Override
            public void run() {
                additionalConditions();
                if(isActive) {
                    currentCount++;
                    if (currentCount % tickCount == 0) {
                        applyEffect();
                    }
                    if (currentCount == durationCount) {
                        remove();
                    }
                }
            }
        },0.1f, 0.1f);
    }

    public void remove()    {
        if(timer != null) {
            timer.stop();
            timer.clear();
        }

        isActive = false;

        removeFromParent();
    }

    public void removeFromParent()  {
        if(parent != null)  {
            parent.getStatusEffects().remove(this);
        }
    }

    public abstract void additionalConditions();

    public abstract void applyEffect();

    /*****************************************************************
     *
     *                      GETTERS AND SETTERS
     *
     ******************************************************************/
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public RaidMember getTarget() {
        return target;
    }

    public void setTarget(RaidMember target) {
        this.target = target;
    }

    public Texture getIcon() {
        return icon;
    }

    public void setIcon(Texture icon) {
        this.icon = icon;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getStartTime() {
        return startTime;
    }

    public void setStartTime(float startTime) {
        this.startTime = startTime;
    }

    public int getModValue() {
        return modValue;
    }

    public void setModValue(int modValue) {
        this.modValue = modValue;
    }

    public int getNumOfTicks() {
        return numOfTicks;
    }

    public void setNumOfTicks(int numOfTicks) {
        this.numOfTicks = numOfTicks;
    }

    public boolean isDispellable() {
        return dispellable;
    }

    public void setDispellable(boolean dispellable) {
        this.dispellable = dispellable;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusEffectList getParent() {
        return parent;
    }

    public void setParent(StatusEffectList parent) {
        this.parent = parent;
    }

    public String toString()    {
        String s = getName()+"\n" +
                "ID: "+getId()+"\n" +
                "Description: "+getDescription()+"\n" +
                "Target :";
        return s;
    }
}
