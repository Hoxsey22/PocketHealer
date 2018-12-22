package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.hoxseygaming.pockethealer.AnimatedBackground;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.AudioManager;
import com.hoxseygaming.pockethealer.GameData;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.PocketHealer;
import com.hoxseygaming.pockethealer.ScrollImage;
import com.hoxseygaming.pockethealer.Text;
import com.hoxseygaming.pockethealer.WindowFrame;

/**
 * Created by Hoxsey on 7/11/2017.
 * Merge
 */
public class MainMenuState extends State{

    private Stage stage;
    private Image titleImage;
    private AnimatedBackground animatedBackground;
    //public Button newGameButton;
    private TextButton newGameButton;
    public TextButton creditsButton;
    private TextButton continueButton;
    private TextButton settingsButton;
    // window and window components
    private Text title;
    private WindowFrame window;
    private Label windowTitleText;
    private Label musicText;
    private Label sfxText;
    private Slider musicSlider;
    private Slider sfxSlider;
    private TextButton okButton;
    // confirmation window for create a new game
    private WindowFrame ngConfirmationWindow;
    private Label ngConfirmationText;
    private TextButton confirmButton;
    private TextButton backButton;


    private Table buttonTable;
    private Assets assets;
    private final Player player;

    public MainMenuState(StateManager sm, Player player) {
        super(sm);
        this.player = player;
        init();
    }

    private void init()   {
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        assets = player.getAssets();

        AudioManager.playMusic(assets.getMusic(assets.mainMusic));

        titleImage = new Image(assets.getTexture(assets.title));
        titleImage.setBounds(PocketHealer.WIDTH/2- 380/2,PocketHealer.HEIGHT - 224,380,214);
        titleImage.setName("titleImage");

        buttonTable = new Table();
        buttonTable.setName("button table");
        //buttonTable.setBounds(0,0,PocketHealer.WIDTH,PocketHealer.HEIGHT);
        buttonTable.setPosition(PocketHealer.WIDTH/2-buttonTable.getWidth()/2,PocketHealer.HEIGHT/5);
        buttonTable.center().bottom().padBottom(20);

        newGameButton = new TextButton("New", PocketHealer.ui);
        newGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ngConfirmationWindow.show(stage);
            }
        });
        buttonTable.add(newGameButton).padBottom(15);
        buttonTable.row();

        if(GameData.doesDataExist("save")) {
            continueButton = new TextButton("Continue", PocketHealer.ui);
            continueButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    animatedBackground.stop();
                    GameData.load(player);
                    sm.set(new MapState(sm, player));
                    System.out.println("Continued Game has been started.");
                }
            });
            buttonTable.add(continueButton).padBottom(15);;
            buttonTable.row();
        }

        settingsButton = new TextButton("Setting", PocketHealer.ui);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                window.show(stage);
            }
        });
        buttonTable.add(settingsButton).padBottom(15);
        buttonTable.row();

        creditsButton = new TextButton("Credit", PocketHealer.ui);
        creditsButton.setPosition(PocketHealer.WIDTH/2-creditsButton.getWidth()/2, PocketHealer.HEIGHT/2-creditsButton.getHeight()/2);
        creditsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sm.set(new CreditsState(sm, player, 0));
            }
        });
        buttonTable.add(creditsButton);


        initWindowFrame();
        initNewGameConfirmationWindow();

        animatedBackground = new AnimatedBackground();
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.mmBG),false, new Vector2(0,0),1f,assets));
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.mmBG2),false, new Vector2(0,0),2f,assets));
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.mmBG3),false, new Vector2(0,0),3f,assets));
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.mmBG4),false, new Vector2(0,0),4f,assets));
        animatedBackground.setDebug(true);

        stage.addActor(animatedBackground);

        stage.addActor(titleImage);

        stage.addActor(buttonTable);

        //stage.addActor(creditsButton);

        animatedBackground.start();
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
    }

    private void initNewGameConfirmationWindow() {
        ngConfirmationWindow = new WindowFrame(PocketHealer.ui);
        //ngConfirmationWindow.setDebug(true);

        ngConfirmationText = new Label("Are you sure you want to start a new game?", PocketHealer.ui);
        ngConfirmationText.setWrap(true);
        ngConfirmationText.setAlignment(Align.center);
        confirmButton = new TextButton("Confirm",PocketHealer.ui, "small_button");

        confirmButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                animatedBackground.stop();
                player.newGame();
                sm.set(new TutorialState(sm, player));
                System.out.println("A New Game has been started.");
            }
        });
        backButton = new TextButton("Back",PocketHealer.ui, "small_button");
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ngConfirmationWindow.hide();
            }
        });
        ngConfirmationWindow.center();
        ngConfirmationWindow.add(ngConfirmationText).width(ngConfirmationWindow.getWidth()).center().colspan(2).pad(10).row();
        ngConfirmationWindow.add(confirmButton).center();
        ngConfirmationWindow.add(backButton).center();
    }

    private void initWindowFrame()   {
        window = new WindowFrame(PocketHealer.ui);
        GameData.loadAudioSettings();

        //window.setDebug(true);
        windowTitleText = new Label("Settings", PocketHealer.ui, "header2");

        musicText = new Label("MUSIC", PocketHealer.ui);
        musicText.getStyle().fontColor = Color.WHITE;

        musicSlider = new Slider(0.0f, 100f,1f, false, PocketHealer.ui);
        musicSlider.setValue(AudioManager.musicVolume*100);
        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AudioManager.updateMusicVolume(musicSlider.getValue()/100f);//  musicVolume = musicSlider.getValue()/100f;
                System.out.println("AM Music: " + AudioManager.musicVolume);
                System.out.println("Music Slider: " + musicSlider.getValue());
            }
        });

        sfxText = new Label("SFX", PocketHealer.ui);
        sfxText.getStyle().fontColor = Color.WHITE;

        sfxSlider = new Slider(0.0f, 100f,1f, false, PocketHealer.ui);
        sfxSlider.setValue(AudioManager.sfxVolume*100);
        sfxSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AudioManager.updateSFXVolume(sfxSlider.getValue()/100f);
                System.out.println("AM SFX: " + AudioManager.sfxVolume);
                System.out.println("SFX Slider: " + sfxSlider.getValue());
            }
        });

        okButton = new TextButton("OK", PocketHealer.ui);
        okButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameData.saveAudioSettings();
                window.hide();
            }
        });

        window.add(windowTitleText).width(window.getWidth()*0.6f).center().fillX().padTop(10);
        window.row();

        window.add(musicText).expandX().left().padLeft(10).padTop(10);
        window.row();

        window.add(musicSlider).fillX().pad(5f,10f,0,10f).left();
        window.row();

        window.add(sfxText).expandX().left().padLeft(10).padTop(5f);
        window.row();

        window.add(sfxSlider).fillX().pad(5f,10f,0,10f).left();
        window.row();

        window.add(okButton).expandX().center().bottom().width(window.getWidth()*0.5f).height(window.getHeight()*0.2f).padBottom(10).padTop(5);

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
