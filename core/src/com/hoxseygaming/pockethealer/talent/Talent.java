package com.hoxseygaming.pockethealer.talent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 7/1/2017.
 */
public class Talent extends Actor{

    private Player owner;
    public String name;
    public int levelRequirement;
    public String description;
    public Texture image;
    private Assets assets;
    public boolean isSelected;
    public Talent partner;

    /**
     * @param name
     * @param levelReq
     * @param desc
     */
    public Talent(Player owner, String name, int levelReq, String desc) {
        this.owner = owner;
        this.name = name;
        setName(name);
        assets = owner.getAssets();
        levelRequirement = levelReq;
        description = desc;
        isSelected = false;
        setImage();
        setSize(174, 111);
    }

    public void setImage() {
        switch (name)   {
            case "Continuous Renewal":
                image = assets.getTexture(assets.continuousRenewalIcon);
                break;
            case "Lifeboom":
                image = assets.getTexture(assets.lifeboomIcon);
                break;
            case "Perseverance":
                image = assets.getTexture(assets.perseveranceIcon);
                break;
            case "Burst Healer":
                image = assets.getTexture(assets.burstHealerIcon);
                break;
            case "Inner Focus":
                image = assets.getTexture(assets.innerFocusIcon);
                break;
            case "Work Together":
                image = assets.getTexture(assets.workTogetherIcon);
                break;
        }
    }

    public void setPartner(Talent newPartner)    {
        newPartner.partner = this;
        partner = newPartner;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public void setLevelRequirement(int levelRequirement) {
        this.levelRequirement = levelRequirement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void selected() {
        isSelected = true;
        partner.unselected();
    }

    public void unselected() {
        isSelected = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!isSelected()) {
            batch.draw(assets.getTexture(assets.talentBg), getX(), getY(), getWidth(), getHeight());
        }
        else{
            batch.draw(assets.getTexture(assets.selectedTalent), getX(), getY(), getWidth(), getHeight());
        }
        batch.draw(image, getX(),getY(),getWidth(),getHeight());


    }
}
