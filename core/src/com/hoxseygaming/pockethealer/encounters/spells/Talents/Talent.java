package com.hoxseygaming.pockethealer.encounters.spells.Talents;

import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 9/2/2017.
 */

public class Talent  {

    public int id;
    public String name;
    public String description;
    public boolean isSelected;
    public int totalPointRequirement;
    public Talent preReq;

    Talent(int id, String name, String description, Talent pair) {
        this.id = id;
        this.name = name;
        this.description = description;
        isSelected = false;
        preReq = pair;
        totalPointRequirement = 0;
    }

    Talent(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        isSelected = false;
        totalPointRequirement = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalPointRequirement() {
        return totalPointRequirement;
    }

    public void setTotalPointRequirement(int totalPointRequirement) {
        this.totalPointRequirement = totalPointRequirement;
    }

    public Talent getPreReq() {
        return preReq;
    }

    public void setPreReq(Talent preReq) {
        this.preReq = preReq;
    }

    public boolean isSelectedable(Player player) {
        if(totalPointRequirement > player.getLevel()) {
            System.out.println("You do not have enough points.");
            return false;
        }
        if(!getPreReq().isSelected()) {
            System.out.println("You must have points in "+preReq.getName());
            return false;
        }
        return  true;
    }
}
