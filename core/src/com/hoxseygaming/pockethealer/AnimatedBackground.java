package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/1/2017.
 */

public class AnimatedBackground extends Actor {

    public ArrayList<ScrollImage> scrollImages;
    public Timer timer;

    //public Assets assets;

    public AnimatedBackground() {
        scrollImages = new ArrayList<>();
    }

    public void add(ScrollImage scrollImage)   {
        scrollImages.add(scrollImage);
    }

    public void start() {
        timer = new Timer();
        timer.schedule(new Timer.Task() {

            @Override
            public void run() {
                for(int i = 0; i < scrollImages.size(); i++)   {
                    scrollImages.get(i).move();
                }
            }
        },0.05f,0.05f);

    }

    public void stop()  {
        timer.stop();
        timer.clear();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for(int i = 0; i < scrollImages.size(); i++)   {
            scrollImages.get(i).draw(batch, parentAlpha);
        }
    }
}
