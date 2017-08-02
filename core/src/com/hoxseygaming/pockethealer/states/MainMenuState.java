package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hoxseygaming.pockethealer.AnimatedBackground;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.PocketHealer;

/**
 * Created by Hoxsey on 7/11/2017.
 */
public class MainMenuState extends State{

    public Stage stage;
    public Image title;
    public AnimatedBackground animatedBackground;
    public Image playButton;
    public Assets assets;
    private Player player;

    public MainMenuState(StateManager sm, Player player) {
        super(sm);

        this.player = player;
        stage = new Stage(new FitViewport(PocketHealer.WIDTH, PocketHealer.HEIGHT));

        assets = player.getAssets();

        animatedBackground = new AnimatedBackground(assets.getTexture(assets.mmBG),assets.getTexture(assets.mmMain),
                assets.getTexture(assets.mmFG),false,assets);
        stage.addActor(animatedBackground);

        player.createSpellBar();
        player.addDebuggingSpell();

        /*music = assets.getMusic(assets.mmMusic);
        music.setLooping(true);
        music.setVolume(0.4f);
        music.play();*/

        title = new Image(assets.getTexture(assets.title));
        title.setPosition(PocketHealer.WIDTH/2-title.getWidth()/2,PocketHealer.HEIGHT - title.getHeight());
       /* title.setBounds(PocketHealer.WIDTH/2 - title.getImageWidth()/2, PocketHealer.HEIGHT/2 - title.getImageHeight()/2 - 100,
                title.getImageWidth(), title.getImageHeight());*/
        title.setName("title");

        playButton = new Image(assets.getTexture(assets.mmPlayButtonIdle));
        playButton.setPosition(PocketHealer.WIDTH/2 - playButton.getWidth()/2, 100);
        playButton.setName("play");

        stage.addActor(title);
        stage.addActor(playButton);
        animatedBackground.start();
    }

    @Override
    protected void handleInput() {
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector2 coord = stage.screenToStageCoordinates(new Vector2((float)screenX,(float)screenY));
                Actor hitActor = stage.hit(coord.x, coord.y, false);
                System.out.println("Hit");
                if(hitActor != null  && hitActor.getName().equalsIgnoreCase("play"))    {
                    System.out.println("Hit inside");
                    animatedBackground.stop();
                    sm.push(new MapState(sm, player));
                }
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(Color.BLACK.r,Color.BLACK.g,Color.BLACK.b,Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        update(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose() {

    }
}
