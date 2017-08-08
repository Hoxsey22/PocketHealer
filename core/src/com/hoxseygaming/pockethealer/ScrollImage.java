package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Hoxsey on 8/1/2017.
 */

public class ScrollImage {

    public Assets assets;
    public Image image1;
    public Image image2;
    private float velocity;
    private float originX;
    private float originY;
    private float endX;
    private float endY;
    public boolean isVertical;

    public ScrollImage(Texture texture, boolean isVertical, Vector2 frame, float velocity, Assets assets)    {
        this.assets = assets;

        this.isVertical = isVertical;

        image1 = new Image(texture);
        image1.setBounds(frame.x,frame.y,texture.getWidth(),texture.getHeight());
        image2 = new Image(texture);
        image2.setBounds(frame.x,frame.y,texture.getWidth(),texture.getHeight());

        setPosition(frame.x,frame.y);

        this.velocity = velocity;

        originX = frame.x;
        originY = frame.y;

        endX = frame.x;
        endY = frame.y;
    }

    public void setPosition(float x, float y)   {
        image1.setPosition(x,y);
        matchPartner();
    }

    public void move()    {
        if(isVertical)    {
            outOfRange();
            image1.setY(image1.getY() - velocity);
            matchPartner();
        }
        else    {
            outOfRange();
            image1.setX(image1.getX() + velocity);
            matchPartner();

        }
    }

    private void matchPartner() {
        if(isVertical)
            image2.setPosition(image1.getX(),image1.getY()+image1.getHeight());
        else
            image2.setPosition(image1.getX()-image1.getWidth(),image1.getY());
    }

    private void outOfRange()   {
        if(isVertical)    {
            if(image2.getY()-velocity < endY)   {
                reposition();
            }
        }
        else    {
            if(image2.getX()+velocity > endX)    {
                reposition();
            }
        }

    }

    private void reposition()   {
        setPosition(originX,originY);
    }

    public void draw(Batch batch, float parent)  {
        image1.draw(batch, parent);
        image2.draw(batch, parent);
    }

}
