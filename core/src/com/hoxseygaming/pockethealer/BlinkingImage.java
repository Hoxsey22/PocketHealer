package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by Hoxsey on 9/27/2017.
 */

public class BlinkingImage extends Actor {

    private Image image;
    private boolean isBlink;
    protected Timer timer;
    private float speed;

    public BlinkingImage(Texture texture)   {
        image = new Image(texture);
        speed = 0.5f;
    }

    public BlinkingImage(Texture texture, float speed)   {
        image = new Image(texture);
        this.speed = speed;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        image.setPosition(x,y);
    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);
        image.setBounds(x, y, width, height);
    }

    public void start() {
        timer = new Timer();

        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if(isBlink)
                    isBlink = false;
                else
                    isBlink = true;
            }
        },speed, speed);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isBlink() {
        return isBlink;
    }

    public void setBlink(boolean blink) {
        isBlink = blink;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!isBlink)
            image.draw(batch, parentAlpha);
    }
}
