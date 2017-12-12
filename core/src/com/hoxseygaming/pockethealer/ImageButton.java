package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;


/**
 * Created by Hoxsey on 7/20/2017.
 */

public class ImageButton extends Actor {

    public Texture texture;
    public boolean isHit;
    private boolean flipX;
    public boolean isHidden;

    public ImageButton(String name, Texture texture) {
        setName(name);
        this.texture = texture;
        setBounds(0,0, texture.getWidth(), texture.getHeight());
        isHit = false;
        isHidden = false;
    }

    public ImageButton(String name, Texture texture, int x, int y, int width, int height) {
        setName(name);
        setBounds(x,y,width,height);
        this.texture = texture;
        isHit = false;
        isHidden = false;
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
    }

    public void enable()    {
        isHidden = false;
    }

    public void disable()   {
        isHidden = true;
    }

    public void flipX() {
        flipX = true;
    }

    public void addToStage(Stage stage)    {
        isHidden = false;
        stage.addActor(this);
    }

    @Override
    public boolean remove() {
        isHidden = true;
        return super.remove();
    }

    public boolean pressed(float x, float y) {

        if(!isHidden) {
            Rectangle bounds = new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());

            if (bounds.contains((int) x, (int) y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight(), 0, 0, texture.getWidth(), texture.getWidth(), flipX, false);
    }
}
