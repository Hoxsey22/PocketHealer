package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by Hoxsey on 8/1/2017.
 */

public class AnimatedBackground extends Actor {

    public ScrollImage bg;
    public ScrollImage main;
    public ScrollImage fg;
    public Timer timer;

    public Assets assets;

    public AnimatedBackground(Texture bg, Texture main, Texture fg, boolean isVertical, Assets assets) {
        this.assets = assets;
        this.bg = new ScrollImage(bg, isVertical, new Vector2(0, PocketHealer.HEIGHT/2-200),2f, assets);
        this.main = new ScrollImage(main, isVertical, new Vector2(0, PocketHealer.HEIGHT/2-200),0f, assets);
        this.fg = new ScrollImage(fg, isVertical, new Vector2(0, PocketHealer.HEIGHT/2-200), 10f, assets);
    }

    public void start() {
        timer = new Timer();
        timer.schedule(new Timer.Task() {

            @Override
            public void run() {
                bg.move();
                main.move();
                fg.move();
            }
        },0.2f,0.2f);

    }

    public void stop()  {
        timer.stop();
        timer.clear();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        bg.draw(batch, parentAlpha);
        main.draw(batch, parentAlpha);
        fg.draw(batch,parentAlpha);
    }
}
