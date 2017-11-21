package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Hoxsey on 11/21/2017.
 */

public class RewardPackage {

    private Image spellImage;
    private String reward;

    public RewardPackage(String reward)  {
        this.reward = reward;
    }

    public RewardPackage(Texture texture, String reward)  {
        spellImage = new Image(texture);
        this.reward = reward;
    }

    public Image getSpellImage() {
        return spellImage;
    }

    public void setSpellImage(Image spellImage) {
        this.spellImage = spellImage;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }
}
