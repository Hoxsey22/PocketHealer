package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hoxseygaming.pockethealer.states.EncounterState;

public class PocketHealer extends ApplicationAdapter {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final String TITLE = "POCKET HEALER";

	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	Texture img;
	Assets assets;
	private com.hoxseygaming.pockethealer.states.StateManager sm;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		assets = new Assets();
		assets.load();
		while (!assets.manager.update())	{
			System.out.println("Loading: "+assets.manager.getProgress()+"%");
		}
		System.out.println("Loading: Complete!");
		sm = new com.hoxseygaming.pockethealer.states.StateManager();
		sm.push(new EncounterState(sm, batch, assets));
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
