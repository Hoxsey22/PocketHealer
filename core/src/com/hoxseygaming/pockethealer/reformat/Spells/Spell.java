package com.hoxseygaming.pockethealer.reformat.Spells;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.reformat.Player;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/17/2017.
 */
public class Spell extends Actor {


    public enum EffectType  {
        HEAL,HEALALL,HEALMULTIPLE,SHIELD,HEALOVERTIME
    }

    public Player owner;
    public String name;
    public String description;
    public EffectType effectType;
    public int output;
    public int cost;
    public float cooldown;
    public float cdCounter;
    public boolean isReady;
    public boolean isCasting;
    public Image image;
    public float cdPercentage;
    public Timer cdTimer;


    public Spell(String name, String description, EffectType effectType, int output, int cost, float cooldown, int index) {
        setBounds(SpellData.positions[index].x,SpellData.positions[index].y,80,80);
        this.name = name;
        this.description = description;
        this.output = output;
        this.cost = cost;
        this.cooldown = cooldown;
        this.effectType = effectType;
        isReady = true;
        isCasting = false;
        cdCounter = 0f;
        cdPercentage = 1f;
    }

    public void startCooldownTimer()    {
            cdTimer = new Timer();
            isReady = false;

            final Spell s = this;
            s.setCdCounter(0);

            cdTimer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    s.cdCount();
                    System.out.println(name + " " + cdCounter);
                    if(s.cdPercentage == 1f) {
                        cdTimer.clear();
                        System.out.println(s.name+" is off cooldown.");
                        isReady = true;
                    }

                }
            },0.1f,0.1f, (int)(s.cooldown/0.1));
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isCasting() {
        return isCasting;
    }

    public void setCasting(boolean casting) {
        isCasting = casting;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public float getCdCounter() {
        return cdCounter;
    }

    public void setCdCounter(float cdCounter) {
        this.cdCounter = cdCounter;
    }

    public void cdCount()   {
        cdCounter = cdCounter + 0.1f;
        getCdPercentage();
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public void setEffectType(EffectType effectType) {
        this.effectType = effectType;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public float getCdPercentage() {
        cdPercentage = cdCounter/cooldown;
        return cdPercentage;
    }

    public void setCdPercentage(float cdPercentage) {
        this.cdPercentage = cdPercentage;
    }

    public boolean isCastable() {
        if(owner.getMana() < cost) {
            System.out.println("OUT OF MANA!");
            return false;
        }
        if(!isReady) {
            System.out.println(name + " IS NOT READY!");
            return false;
        }
        if(isCasting) {
            System.out.println(name + " IS STILL CASTING!");
            return false;
        }
        if(!owner.getTarget().isDead())    {
            System.out.println("Target is dead!");
            return false;
        }

        return true;
    }
}
