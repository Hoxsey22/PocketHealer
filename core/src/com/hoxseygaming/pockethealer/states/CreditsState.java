package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.AnimatedBackground;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.AudioManager;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.PocketHealer;
import com.hoxseygaming.pockethealer.ScrollImage;
import com.hoxseygaming.pockethealer.ShutterAnimation;
import com.hoxseygaming.pockethealer.Text;

/**
 * Created by Hoxsey on 7/11/2017.
 */
public class CreditsState extends State{

    private Stage stage;
    private AnimatedBackground animatedBackground;
    private Assets assets;
    private Table table;
    private Player player;
    private int previousState;
    private boolean isReady;
    private ShutterAnimation shutterAnimation;

    public CreditsState(StateManager sm, Player player, int previousState) {
        super(sm);
            this.player = player;
        this.previousState = previousState;
        isReady = false;
        init();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                isReady = true;
            }
        },5f, 1);

    }

    private void init()   {
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        assets = player.getAssets();

        AudioManager.playMusic(assets.getMusic(assets.creditsMusic));

        table = new Table();

        //title
        table.add(new Text("Credits",32, Color.BLACK, false, assets).getLabel()).left().padBottom(10);
        table.row();
        table.add(new Text("Game Design & Programming",24, Color.BLACK, false, assets).getLabel()).left();
        table.row();
        table.add(new Text("- Joseph Hoxsey",24, Color.BLACK, false, assets).getLabel()).left().padBottom(10);
        table.row();
        table.add(new Text("Art",24, Color.BLACK, false, assets).getLabel()).left();
        table.row();
        table.add(new Text("- ShMELStudio @ Gamedevmarket.com",24, Color.BLACK, false, assets).getLabel()).left();
        table.row();
        table.add(new Text("- Chuchilko @ Gamedevmarket.com",24, Color.BLACK, false, assets).getLabel()).left();
        table.row();
        table.add(new Text("- cruizRF @ Gamedevmarket.com",24, Color.BLACK, false, assets).getLabel()).left();
        table.row();
        table.add(new Text("- Rexard @ Gamedevmarket.com",24, Color.BLACK, false, assets).getLabel()).left().padBottom(10);
        table.row();
        table.add(new Text("Music",24, Color.BLACK, false, assets).getLabel()).left();
        table.row();
        table.add(new Text("- wowsoundsg @ Gamedevmarket.com",24, Color.BLACK, false, assets).getLabel()).left();
        table.row();
        table.add(new Text("- Riku20xx @ Gamedevmarket.com",24, Color.BLACK, false, assets).getLabel()).left();

        table.setPosition(PocketHealer.WIDTH/2 - table.getWidth()/2,PocketHealer.HEIGHT/2 - table.getHeight()/2);



        animatedBackground = new AnimatedBackground();
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.creditsLayer1),false, new Vector2(0,0),0f,assets));
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.creditsLayer2),false, new Vector2(0,0),1f,assets));
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.creditsLayer3),false, new Vector2(0,0),2f,assets));
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.creditsLayer4),false, new Vector2(0,0),2.5f,assets));
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.creditsLayer5),false, new Vector2(0,0),3f,assets));

        stage.addActor(animatedBackground);
        stage.addActor(table);

        animatedBackground.start();
        shutterAnimation = new ShutterAnimation(stage, player.getAssets(), false);
        shutterAnimation.start();
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
                    switch (previousState)   {
                        case 0:
                            sm.push(new MainMenuState(sm, player));
                            break;
                        case 1:
                            shutterAnimation = new ShutterAnimation(stage, assets, true, new Runnable() {
                                @Override
                                public void run() {
                                    sm.push(new MapState(sm, player,4));
                                }
                            });
                            shutterAnimation.start();
                            break;
                    }

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
        //sb.setProjectionMatrix(cam.combined);
        update(Gdx.graphics.getDeltaTime());
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose() {
        System.out.println("DISPOSE MM!");
    }
}
