package com.hoxseygaming.pockethealer.talent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.hoxseygaming.pockethealer.Assets;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 7/6/2017.
 */
public class TalentWindow extends Group {

    public Texture image;
    public Actor doneButton;
    public TalentTooltip tooltip;
    public TalentBook talentBook;
    public Assets assets;

    public TalentWindow(TalentBook talentBook, Assets assets)   {

        this.assets = assets;

        image = assets.getTexture(assets.talentWindow);
        setBounds(25,24,434,765);

        doneButton = new Actor();
        doneButton.setName("done");
        doneButton.setSize(151,49);
        doneButton.setPosition(getX() + (getWidth()/2) - (doneButton.getWidth()/2), getY() - 10);
        System.out.println("Done button[ x: "+doneButton.getX()+" y: "+(doneButton.getY() + doneButton.getHeight()) );


        tooltip = new TalentTooltip(this);

        this.talentBook = talentBook;
        setTalentPositions();
        addActor(this.talentBook);
        addActor(tooltip);
        addActor(doneButton);

    }

    public Talent hit(float x, float y) {
        Actor hit= talentBook.hit(x,y,false);
        if(hit != null) {
            switch (hit.getName()) {
                case "Continuous Renewal":
                    return talentBook.getTalent(hit.getName());
                case "Lifeboom":
                    return talentBook.getTalent(hit.getName());
                case "Perseverance":
                    return talentBook.getTalent(hit.getName());
                case "Burst Healer":
                    return talentBook.getTalent(hit.getName());
                case "Inner Focus":
                    return talentBook.getTalent(hit.getName());
                case "Work Together":
                    return talentBook.getTalent(hit.getName());
            }
        }
        return null;
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public Actor getDoneButton() {
        return doneButton;
    }

    public void setDoneButton(Actor done_button) {
        this.doneButton = done_button;
    }

    public TalentTooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(TalentTooltip tooltip) {
        this.tooltip = tooltip;
    }

    public TalentBook getTalentBook() {
        return talentBook;
    }

    public void setTalentBook(TalentBook talentBook) {
        this.talentBook = talentBook;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public void setTalentPositions()    {
        ArrayList<Talent> talents = talentBook.talents;

        talents.get(0).setPosition(getX()+15, getY()+ tooltip.getHeight() + (talents.get(0).getHeight()* 3));
        talents.get(1).setPosition(talents.get(0).getX() +talents.get(0).getWidth()+ 2, talents.get(0).getY());
        for(int i = 2; i < talents.size(); i = i * 2) {
            talents.get(i).setPosition(talents.get(i-2).getX(), talents.get(i-2).getY() - talents.get(i-2).getHeight());
            talents.get(i+1).setPosition(talents.get(i).getX() + talents.get(i).getWidth() + 2, talents.get(i).getY());
        }



    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image,getX(),getY(),getWidth(),getHeight());
        talentBook.draw(batch,parentAlpha);
        tooltip.draw(batch, parentAlpha);
        batch.draw(assets.getTexture(assets.doneButton),doneButton.getX(),doneButton.getY(),doneButton.getWidth(),doneButton.getHeight());
    }
}
