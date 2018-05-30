package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.hoxseygaming.pockethealer.PocketHealer;

/**
 * Created by Hoxsey on 5/27/2017.
 */
abstract class State extends ApplicationAdapter {
    private final OrthographicCamera cam;
    private final Vector3 mouse;
    final com.hoxseygaming.pockethealer.states.StateManager sm;
    final StretchViewport viewport;

    State(com.hoxseygaming.pockethealer.states.StateManager sm)   {
        this.sm = sm;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, PocketHealer.WIDTH,PocketHealer.HEIGHT);
        viewport = new StretchViewport(PocketHealer.WIDTH,PocketHealer.HEIGHT,cam);
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width,height);
        cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);
    }
}
