package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

/**
 * Created by Hoxsey on 7/30/2017.
 */

public class Button extends Actor {

    public Image image;
    public Label label;
    public Assets assets;
    public boolean isHit;

    public Button(String name, Assets assets) {
        setName(name);
        this.assets = assets;
        image = new Image(assets.getTexture(assets.button));
        setBounds(0,0,image.getWidth(), image.getHeight());
        isHit = false;
        setupLabel();
    }

    public Button(String name, Image image, int x, int y, int width, int height) {
        setName(name);
        setBounds(x,y,width,height);
        this.image = image;
        image.setBounds(x,y,width,height);
        isHit = false;
    }

    private void setupLabel()   {
        Label.LabelStyle textStyle = new Label.LabelStyle();
        BitmapFont font = assets.getFont(assets.mapTitle);
        textStyle.font = font;

        label = new Label(getName(),textStyle);
        label.setColor(Color.BLACK);
        label.setWrap(true);
        label.setWidth(this.getWidth());
        label.setAlignment(Align.center);
        label.setPosition(getX()+getWidth()/2-label.getWidth()/2,getY()+ getHeight()/2 - label.getHeight()/2);
    }

    public void hit()   {
        isHit = true;
    }

    public boolean isHit()  {
        return isHit;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        image.setPosition(x,y);
        label.setPosition(getX()+getWidth()/2-label.getWidth()/2,getY()+ getHeight()/2 - label.getHeight()/2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        image.draw(batch, parentAlpha);
        label.draw(batch, parentAlpha);
    }
}
