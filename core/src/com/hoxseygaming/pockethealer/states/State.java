package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.hoxseygaming.pockethealer.PocketHealer;

/**
 * Created by Hoxsey on 5/27/2017.
 */
public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected com.hoxseygaming.pockethealer.states.StateManager sm;

    protected State(com.hoxseygaming.pockethealer.states.StateManager sm)   {
        this.sm = sm;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, PocketHealer.WIDTH,PocketHealer.HEIGHT);
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
