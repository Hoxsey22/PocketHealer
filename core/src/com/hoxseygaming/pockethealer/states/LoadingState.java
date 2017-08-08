package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.PocketHealer;

/**
 * Created by Hoxsey on 6/30/2017.
 */
public class LoadingState extends State {

    private Image logo;
    private float progress;
    private ShapeRenderer shapeRenderer;
    private boolean isReady;
    private Stage stage;
    private Assets assets;
    private Label loadingText;
    private BitmapFont font;
    private Label.LabelStyle textStyle;
    private MainMenuState nextState;
    private Player player;

    public LoadingState(StateManager sm) {
        super(sm);

        logo = new Image(new Texture("logo_screen.png"));
        logo.setSize(PocketHealer.WIDTH,PocketHealer.HEIGHT);
        logo.setPosition(0,0);

        createFont();

        progress = 0f;

        shapeRenderer = new ShapeRenderer();

        isReady = false;

        assets = new Assets();

        stage = new Stage(new FitViewport(PocketHealer.WIDTH,PocketHealer.HEIGHT));

        stage.addActor(logo);
        stage.addActor(loadingText);

        assets.load();

    }

    private void createFont()   {
        textStyle = new Label.LabelStyle();
        font = new BitmapFont(Gdx.files.internal("fonts/loading_font.fnt"));
        textStyle.font = font;

        loadingText = new Label("Loading...",textStyle);
        loadingText.setSize(1,1);
        loadingText.setPosition((PocketHealer.WIDTH/2)-(loadingText.getWidth()/2),50);
        loadingText.setAlignment(Align.center);
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
                if(isReady) {
                    //sm.push(new TalentState(sm,assets, player));
                    //sm.push(new EncounterState(sm,assets));
                    //sm.push(new TestState(sm,assets));
                    player = new Player(assets);
                    sm.push(new MainMenuState(sm, player));
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
        if(!assets.update())    {
            progress = assets.getProgress();
        }
        else {
            isReady = true;
            loadingText.setText("Press the screen!");
        }

    }

    @Override
    public void render(SpriteBatch sb) {

        Gdx.gl.glClearColor(Color.TAN.r,Color.TAN.g,Color.TAN.b,Color.TAN.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        shapeRenderer.setProjectionMatrix(stage.getBatch().getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.rect(65, 125, 380 * progress, 50);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
