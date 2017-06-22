package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.EncounterData;

/**
 * Created by Hoxsey on 6/17/2017.
 */
public class Spell extends Actor {


    public enum EffectType  {
        HEAL,HEALALL,HEALMULTIPLE,SHIELD,HEALOVERTIME
    }

    public int index;
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
    public Texture image;
    public float cdPercentage;
    public Timer cdTimer;


    public Spell(String name, String description, EffectType effectType, int output, int cost, float cooldown, int index) {
        setBounds(SpellData.positions[index].x,SpellData.positions[index].y,80,80);
        this.index = index;
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

    public void castSpell() {
        System.out.println("Casted a spell!");
    }

    public void startCooldownTimer()    {
            cdTimer = new Timer();
            isReady = false;
            setCdCounter(cooldown);

            cdTimer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    cdCount();
                    System.out.println(name + " " + cdCounter);
                    if(getCdPercentage() <= 0f) {
                        cdTimer.clear();
                        System.out.println(name+" is off cooldown.");
                        isReady = true;
                        setCdCounter(0);
                    }

                }
            },0.1f,0.1f, (int)(cooldown/0.1));
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
        cdCounter = cdCounter - 0.1f;
        getCdPercentage();
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
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
        return cdPercentage = cdCounter/cooldown;
    }

    public void setCdPercentage(float cdPercentage) {
        this.cdPercentage = cdPercentage;
    }

    public void useMana()   {
        owner.mana = owner.mana - cost;
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
        if(owner.getTarget().isDead())    {
            System.out.println("ID:"+owner.getTarget().id+" Target is dead!");
            return false;
        }

        return true;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image, getX(),getY(),getWidth(), getHeight());
        batch.draw(EncounterData.cooldownBar, getX(),getY(),getWidth(), getHeight()*getCdPercentage());
    }
}
