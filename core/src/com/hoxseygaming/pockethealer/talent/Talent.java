package com.hoxseygaming.pockethealer.talent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Hoxsey on 7/1/2017.
 */
public class Talent extends Actor{

    public String name;
    public int levelRequirement;
    public String description;
    public Texture image;
    public boolean isSelected;
    public Talent partner;
    public Texture selectedImage;

    @Override
    public boolean hasParent() {
        return super.hasParent();
    }

    /**
     * @param name
     * @param levelReq
     * @param desc
     */
    public Talent(final String name, int levelReq, String desc) {
        this.name = name;
        levelRequirement = levelReq;
        description = desc;
        isSelected = false;
        setSize(215, 80);
        /*
        bounds = new Rectangle();
        bounds.setSize(215,80);
        */
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

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
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
        batch.draw(image,getX(),getY(), getWidth(),getHeight());
        if(isSelected()) {
            batch.draw(selectedImage, getX(), getY(), getWidth(), getHeight());
        }

    }
}
