package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.hoxseygaming.pockethealer.states.LoadingState;
import com.hoxseygaming.pockethealer.states.StateManager;

public class PocketHealer extends ApplicationAdapter {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final String TITLE = "POCKET HEALER";
	public static Music music;
	public static Skin ui;

	SpriteBatch batch;
	private StateManager sm;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		sm = new StateManager();
		ui = new Skin(Gdx.files.internal("pocket_healer_ui.json"));
		music = Gdx.audio.newMusic(Gdx.files.internal("mm_music.ogg"));
		music.setLooping(true);
		music.setVolume(0.0f);
		music.play();
		sm.push(new LoadingState(sm));
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
