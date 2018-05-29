package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Hoxsey on 8/1/2017.
 */

public class ScrollImage {

    private Assets assets;
    private Image image1;
    private Image image2;
    private float velocity;
    private float originX;
    private float originY;
    private float endX;
    private float endY;
    private boolean isVertical;

    public ScrollImage(Texture texture, boolean isVertical, Vector2 frame, float velocity, Assets assets)    {
        this.setAssets(assets);

        this.setVertical(isVertical);

        setImage1(new Image(texture));
        getImage1().setBounds(frame.x,frame.y,texture.getWidth(),texture.getHeight());
        setImage2(new Image(texture));
        getImage2().setBounds(frame.x,frame.y,texture.getWidth(),texture.getHeight());

        setPosition(frame.x,frame.y);

        this.setVelocity(velocity);

        setOriginX(frame.x);
        setOriginY(frame.y);

        setEndX(frame.x);
        setEndY(frame.y);
    }

    public void setPosition(float x, float y)   {
        getImage1().setPosition(x,y);
        matchPartner();
    }

    public void move()    {
        if(isVertical())    {
            outOfRange();
            getImage1().setY(getImage1().getY() - getVelocity());
            matchPartner();
        }
        else    {
            outOfRange();
            getImage1().setX(getImage1().getX() + getVelocity());
            matchPartner();

        }
    }

    private void matchPartner() {
        if(isVertical())
            getImage2().setPosition(getImage1().getX(), getImage1().getY()+ getImage1().getHeight());
        else
            getImage2().setPosition(getImage1().getX()- getImage1().getWidth(), getImage1().getY());
    }

    private void outOfRange()   {
        if(isVertical())    {
            if(getImage2().getY()- getVelocity() < getEndY())   {
                reposition();
            }
        }
        else    {
            if(getImage2().getX()+ getVelocity() > getEndX())    {
                reposition();
            }
        }

    }

    private void reposition()   {
        setPosition(getOriginX(), getOriginY());
    }

    public void draw(Batch batch, float parent)  {
        getImage1().draw(batch, parent);
        getImage2().draw(batch, parent);
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public Image getImage1() {
        return image1;
    }

    public void setImage1(Image image1) {
        this.image1 = image1;
    }

    public Image getImage2() {
        return image2;
    }

    public void setImage2(Image image2) {
        this.image2 = image2;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public float getOriginX() {
        return originX;
    }

    public void setOriginX(float originX) {
        this.originX = originX;
    }

    public float getOriginY() {
        return originY;
    }

    public void setOriginY(float originY) {
        this.originY = originY;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public void setVertical(boolean vertical) {
        isVertical = vertical;
    }
}
