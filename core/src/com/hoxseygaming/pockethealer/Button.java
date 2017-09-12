package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Hoxsey on 7/30/2017.
 */

public class Button extends Actor {

    public Image image;
    public Text text;
    public Assets assets;
    public boolean isHit;

    public Button(String name, Assets assets) {
        setName(name);

        this.assets = assets;

        image = new Image(assets.getTexture(assets.button));

        setBounds(0,0,image.getWidth(), image.getHeight());

        isHit = false;

        setupText();
    }

    public Button(String name, Image image, int x, int y, int width, int height) {
        setName(name);
        setBounds(x,y,width,height);
        this.image = image;
        image.setBounds(x,y,width,height);
        isHit = false;
    }

    private void setupText()   {

        text = new Text(getName(),32, Color.BLACK, false, assets);
        //text.setFontSize(32);
        //text.setColor(Color.BLACK);
        text.setWrap(true);

        text.setPosition(getX()+getWidth()/2-text.getWidth()/2,getY()+ getHeight()/2 - text.getHeight()/2);
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
        //lowerTable.setPosition(x,y);
        image.setPosition(x,y);
        text.setPosition(x+getWidth()/2-text.getXCenter(),y+ getHeight()/2 - text.getYCenter());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        image.draw(batch, parentAlpha);
        text.draw(batch, parentAlpha);
    }

    public void dispose()   {
        text.dispose();
    }
}
