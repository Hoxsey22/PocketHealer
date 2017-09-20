package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.hoxseygaming.pockethealer.AnimatedBackground;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.PocketHealer;
import com.hoxseygaming.pockethealer.ScrollImage;
import com.hoxseygaming.pockethealer.Text;

/**
 * Created by Hoxsey on 7/11/2017.
 */
public class MainMenuState extends State{

    public Stage stage;
    public Image title;
    public AnimatedBackground animatedBackground;
    public Text text;
    public Assets assets;
    private Player player;

    public MainMenuState(StateManager sm, Player player) {
        super(sm);

        this.player = player;
        stage = new Stage(viewport);

        assets = player.getAssets();

        animatedBackground = new AnimatedBackground();
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.mmBG),false, new Vector2(0,0),1f,assets));
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.mmBG2),false, new Vector2(0,0),2f,assets));
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.mmBG3),false, new Vector2(0,0),3f,assets));
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.mmBG4),false, new Vector2(0,0),4f,assets));
        animatedBackground.setDebug(true);

        text = new Text("Press screen to continue...", 32, Color.WHITE, false, assets);
        //text.setFontColor(Color.WHITE);
        text.setPosition(PocketHealer.WIDTH/2 - text.getXCenter(), 50);
        text.setAlignment(Align.center);
        //text.setFontSize(32);

        stage.addActor(animatedBackground);
        stage.addActor(text);

        //player.createSpellBar();
        //player.addDebuggingSpell();

        /*music = assets.getMusic(assets.mmMusic);
        music.setLooping(true);
        music.setVolume(0.4f);
        music.play();*/

        title = new Image(assets.getTexture(assets.title));
        title.setPosition(PocketHealer.WIDTH/2-title.getWidth()/2,PocketHealer.HEIGHT - title.getHeight());
        title.setName("title");

        stage.addActor(title);
        //stage.addActor(playButton);
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
                animatedBackground.stop();
                sm.push(new MapState(sm, player));
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
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose() {
        System.out.println("DISPOSE MM!");
        text.dispose();
    }
}
