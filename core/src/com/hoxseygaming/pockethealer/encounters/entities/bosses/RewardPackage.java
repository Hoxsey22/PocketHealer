package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 11/21/2017.
 */

public class RewardPackage {

    public ArrayList<Image> images;
    Boss boss;
    private String reward;
    public boolean spell;
    public boolean talent;
    public boolean level;

    public RewardPackage(Boss boss)  {
        this.reward = "";
        images = new ArrayList<>();
        this.boss = boss;
    }

    public void addNewSpellText()  {
        spell = true;
        reward+="New Spell!\n";
    }

    public void addNewTalentText()  {
        talent = true;
        reward+="New Talent Point!\n";
        boss.rewardPoint();

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
