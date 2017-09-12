package com.hoxseygaming.pockethealer.encounters.spells.Talents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hoxseygaming.pockethealer.Assets;

/**
 * Created by Hoxsey on 9/2/2017.
 */

public class Talent extends Actor{

    public int id;
    public TalentTree talentTree;
    public String description;
    public boolean isSelected;
    public int totalPointRequirement;
    public Talent preReq;
    public Texture image;
    public Assets assets;

    public Talent(TalentTree talentTree, int id, String name, String description, Talent pair, Texture image, Assets assets) {
        this.talentTree = talentTree;
        this.id = id;
        setName(name);
        this.description = description;
        this.image = image;
        isSelected = false;
        preReq = pair;
        totalPointRequirement = 0;
        this.assets = assets;
    }

    public Talent(TalentTree talentTree, int id, String name, String description, Texture image, Assets assets) {
        this.id = id;
        this.talentTree = talentTree;
        setName(name);
        this.description = description;
        this.image = image;
        isSelected = false;
        totalPointRequirement = 0;
        this.assets = assets;
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

    public boolean hasPreReq()  {
        if(preReq != null)
            return true;
        else
            return false;
    }

    public Talent getPreReq() {
        return preReq;
    }

    public float getCenterX()   {
        return getX() + getWidth()/2;
    }

    public float getTop()   {
        return getY() + getHeight();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image, getX(), getY(), getWidth(), getHeight());
        if(preReq != null) {
            if (isSelected) {
                batch.draw(assets.getTexture(assets.selectedLine), getCenterX(), getTop(), assets.getTexture(assets.selectedLine).getWidth(),
                        preReq.getY() - (getY() + getHeight()));
            } else {
                batch.draw(assets.getTexture(assets.idleLine), getCenterX(), getTop(), assets.getTexture(assets.idleLine).getWidth(),
                        preReq.getY() - (getY() + getHeight()));
            }
        }
        if(isSelected()) {
            batch.draw(assets.getTexture(assets.selectedTalent), getX(), getY(), getWidth(), getHeight());
        }
    }

}
