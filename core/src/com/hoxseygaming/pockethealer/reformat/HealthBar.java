package com.hoxseygaming.pockethealer.reformat;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class HealthBar {

    public int x;
    public int y;
    public int WIDTH = 121;
    public int HEIGHT = 13;
    public Rectangle backgroundBar;
    public Color barColor;
    public ShapeRenderer renderer;
    public float percent;


    public HealthBar(int x, int y)  {
        this.x = x + 7;
        this.y = y + 7;
        backgroundBar = new Rectangle(x+5,y+5,WIDTH+4,HEIGHT+4);
        barColor = Color.GREEN;
        renderer = new ShapeRenderer();
        percent = 1f;

    }

    public Rectangle getBackgroundBar() {
        return backgroundBar;
    }

    public void setBackgroundBar(Rectangle backgroundBar) {
        this.backgroundBar = backgroundBar;
    }

    public Color getBarColor() {
        return barColor;
    }

    public void setBarColor(Color barColor) {
        this.barColor = barColor;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public void draw(Batch batch, float alpha, float hpPercent, float shieldPercent)  {
        batch.end();
        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.setColor(Color.BLACK);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
            renderer.rect(backgroundBar.x,backgroundBar.y,backgroundBar.width,backgroundBar.getHeight());
            renderer.setColor(barColor);
            renderer.rect(x,y,WIDTH*hpPercent,HEIGHT);

            renderer.setColor(Color.SKY);
            renderer.rect(x,y,WIDTH*shieldPercent,HEIGHT);
        renderer.end();
        batch.begin();
    }
}
