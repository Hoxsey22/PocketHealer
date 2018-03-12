package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 11/21/2017.
 */

public class RewardPackage {

    public ArrayList<Image> images;
    private String reward;
    public boolean spell;
    public boolean talent;
    public boolean level;

    public RewardPackage()  {
        this.reward = "";
        images = new ArrayList<>();
    }

    public void addNewSpellText()  {
        spell = true;
        reward+="New Spell!\n";
    }

    public void addNewTalentText()  {
        talent = true;
        reward+="New Talent Point!\n";
    }
    public void addNewLevelText()  {
        level = true;
        reward+="Level up!\n";
    }

    public void addImage(Image image)   {
        images.add(image);
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }
}
