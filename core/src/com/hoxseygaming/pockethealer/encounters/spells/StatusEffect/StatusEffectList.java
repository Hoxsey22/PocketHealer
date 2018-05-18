package com.hoxseygaming.pockethealer.encounters.spells.StatusEffect;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 12/2/2017.
 */

public class StatusEffectList {

    public ArrayList<StatusEffect> statusEffects;
    public RaidMember owner;

    public StatusEffectList(RaidMember owner)   {
        statusEffects = new ArrayList<>();
        this.owner = owner;
    }

    public void add(StatusEffect statusEffect)   {
        if(contains(statusEffect.getName()))    {
            removeStatusEffect(statusEffect.getName());
            statusEffects.add(statusEffect);
            statusEffect.setParent(this);
            statusEffect.start();

        }
        else {
            statusEffects.add(statusEffect);
            statusEffect.setParent(this);
            statusEffect.start();
        }
    }

    /**
     * This method will remove any status effects that can be dispel.
     */
    public void dispel()    {

        for(int i = 0; i < statusEffects.size(); i++)   {
            if(statusEffects.get(i).isDispellable())    {
                statusEffects.get(i).dispel();
            }
        }
    }

    /**
     * This method will remove any status effects that can be dispel.
     */
    public void clear()    {

        for(int i = 0; i < statusEffects.size(); i++)   {
            statusEffects.get(i).remove();
        }
    }

    /**
     * Finds the status effect based on the name.
     * @param name
     * @return
     */
    public StatusEffect getStatusEffect(String name)   {
        for(int i = 0; i <  statusEffects.size(); i++)   {
            if(statusEffects.get(i).getName().equalsIgnoreCase(name))    {
                return statusEffects.get(i);
            }
        }
        return null;
    }

    /**
     * Removes status effect based on name.
     * @param name
     */
    public void removeStatusEffect(String name)    {
       getStatusEffect(name).remove();
    }

    /**
     * Checks if a status effect name is in the list.
     *
     * @param name
     * @return
     */
    public boolean contains(String name)   {
        for(int i = 0; i <  statusEffects.size(); i++)   {
            if(statusEffects.get(i).getName().equalsIgnoreCase(name))    {
                return true;
            }
        }
        return false;
    }

    public int getStatusEffectModification(int output, boolean isHealing) {
        int newOutput = output;
        for (int i = statusEffects.size()-1; i >= 0; i--) {
            switch (statusEffects.get(i).getType()) {
                case StatusEffect.NONE:
                    break;
                case StatusEffect.DAMAGE_AMPLIFIER:
                    if (!isHealing) {
                        newOutput = statusEffects.get(i).modifyOutput(newOutput);
                    }
                    break;
                case StatusEffect.HEALING_REDUCTION:
                    if (isHealing) {
                        newOutput = statusEffects.get(i).modifyOutput(newOutput);
                    }
                    break;
            }
        }
        return newOutput;
    }

    public boolean contains(int id)   {
        for (int i = 0; i < statusEffects.size(); i++){
            if(statusEffects.get(i).getId() == id)  {
                return true;
            }
        }
        return false;
    }


    public void draw(Batch batch, float alpha)  {

        for(int i = 0; i <  statusEffects.size(); i++) {
            batch.draw(statusEffects.get(i).getIcon(),
                    owner.healthBar.x + owner.healthBar.width - 20 * (i) - 20,
                    owner.healthBar.y + owner.healthBar.height + 5,
                    20,
                    20);
        }
    }

    /*****************************************************************
     *
     *                      GETTERS AND SETTERS
     *
     ******************************************************************/

    public ArrayList<StatusEffect> getStatusEffects() {
        return statusEffects;
    }

    public void setStatusEffects(ArrayList<StatusEffect> statusEffects) {
        this.statusEffects = statusEffects;
    }

    public RaidMember getOwner() {
        return owner;
    }

    public void setOwner(RaidMember owner) {
        this.owner = owner;
    }

    public int size()   {
        return statusEffects.size();
    }

    public StatusEffect get(int index)  {
        return statusEffects.get(index);
    }
}
