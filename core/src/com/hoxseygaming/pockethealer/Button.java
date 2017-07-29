package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Hoxsey on 7/20/2017.
 */

public class Button extends Actor {

    public Image image;
    public boolean isHit;

    public Button(String name, Image image) {
        setName(name);
        this.image = image;
        setBounds(0,0,image.getWidth(), image.getHeight());
        isHit = false;
    }

    public Button(String name, Image image, int x, int y, int width, int height) {
        setName(name);
        setBounds(x,y,width,height);
        this.image = image;
        image.setBounds(x,y,width,height);
        isHit = false;
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
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        image.draw(batch, parentAlpha);
    }
}
