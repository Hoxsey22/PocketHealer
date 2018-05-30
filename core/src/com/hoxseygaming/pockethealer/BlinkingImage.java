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
    private Timer timer;
    private float speed;

    public BlinkingImage(Texture texture)   {
        image = new Image(texture);
        speed = 0.5f;
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
                isBlink = !isBlink;
            }
        },speed, speed);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!isBlink)
            image.draw(batch, parentAlpha);
    }
}
