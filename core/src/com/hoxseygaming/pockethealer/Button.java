package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


/**
 * Created by Hoxsey on 7/30/2017.
 */

public class Button extends Actor {

    public Image image;
    public Image highlightImage;
    public Text text;
    public Assets assets;
    public boolean isHighlight;
    public boolean isHit;

    public Button(String name, boolean isTrue, Assets assets) {
        setName(name);

        this.assets = assets;

        if(isTrue)
            image = new Image(assets.getTexture(assets.button));
        else
            image = new Image(assets.getTexture(assets.smallButton));

        highlightImage = new Image(assets.getTexture(assets.buttonHighlight));

        setBounds(0,0,image.getWidth(), image.getHeight());

        isHit = false;
        isHighlight = false;

        setupText(isTrue);
    }

    public Button(String name, Image image, int x, int y, int width, int height, Assets assets) {
        setName(name);

        this.assets = assets;
        setBounds(x,y,width,height);
        this.image = image;
        image.setBounds(x,y,width,height);
        isHighlight = false;
        isHit = false;
    }

    private void setupText(boolean isTrue)   {
        if(isTrue)
            text = new Text(getName(),32, Color.BLACK, false, assets);
        else
            text = new Text(getName(),24, Color.BLACK, false, assets);
        //title.setFontSize(32);
        //title.setColor(Color.BLACK);
        text.setWrap(true);

        text.setPosition(getX()+getWidth()/2-text.getWidth()/2,getY()+ getHeight()/2 - text.getHeight()/2);
    }

    public boolean pressed(float x, float y) {

        Rectangle bounds = new Rectangle((int)getX(), (int)getY(),(int)getWidth(), (int)getHeight());

        if(bounds.contains((int)x, (int)y)) {
            return true;
        }
        return false;
    }

    public void hit()   {
        isHit = true;
    }

    public boolean isHit()  {
        return isHit;
    }

    public boolean isHighlight() {
        return isHighlight;
    }

    public void setHighlight(boolean highlight) {
        isHighlight = highlight;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        //lowerTable.setPosition(x,y);
        image.setPosition(x,y);
        highlightImage.setPosition(x,y);
        text.setPosition(x+getWidth()/2-text.getXCenter(),y+ getHeight()/2 - text.getYCenter());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!isHighlight)
            image.draw(batch, parentAlpha);
        else
            highlightImage.draw(batch, parentAlpha);
        text.draw(batch, parentAlpha);
    }

    public void dispose()   {
        text.dispose();
    }
}
