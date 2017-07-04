package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hoxseygaming.pockethealer.states.EncounterState;
import com.hoxseygaming.pockethealer.states.LoadingState;
import com.hoxseygaming.pockethealer.states.StateManager;

public class PocketHealer extends ApplicationAdapter {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final String TITLE = "POCKET HEALER";

	SpriteBatch batch;
	private StateManager sm;
	public Player player;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		sm = new StateManager();
		player = new Player();
		sm.push(new LoadingState(sm, player));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sm.update(Gdx.graphics.getDeltaTime());
		sm.render(batch);
	}

	@Override
	public void dispose () {
		super.dispose();
	}
}
