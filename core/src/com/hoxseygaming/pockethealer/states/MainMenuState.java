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
 */
public class MainMenuState extends State{

    public Stage stage;
    public Image title;
    public AnimatedBackground animatedBackground;
    public Text text;
    //public Button newGameButton;
    TextButton newGameButton;
    //public Button continueButton;
    TextButton continueButton;
    TextButton settingsButton;
    // window and window components
    WindowFrame window;
    Label windowTitleText;
    Label musicText;
    Label sfxText;
    Slider musicSlider;
    Label musicSliderValueText;
    Slider sfxSlider;
    Label sfxSliderValueText;
    TextButton okButton;

    boolean settingFrameActive;
    public Table buttonTable;
    public Assets assets;
    public Player player;

    public MainMenuState(StateManager sm, Player player) {
        super(sm);
        this.player = player;
        init();
    }

    private void init()   {
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        assets = player.getAssets();

        AudioManager.playMusic(assets.getMusic(assets.mmMusic), true);



        buttonTable = new Table();
        buttonTable.setName("button table");
        buttonTable.setBounds(0,0,PocketHealer.WIDTH,PocketHealer.HEIGHT);
        buttonTable.center().bottom().padBottom(20);

        newGameButton = new TextButton("New", PocketHealer.ui);
        newGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                animatedBackground.stop();
                player.newGame();
                sm.push(new TutorialState(sm, player));
                System.out.println("A New Game has been started.");
            }
        });
        buttonTable.add(newGameButton);

        /*
        newGameButton = new Button("New", false, assets);
        newGameButton.setParent(buttonTable);
        */

        if(GameData.doesDataExist("save")) {
            continueButton = new TextButton("Continue", PocketHealer.ui);
            continueButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    animatedBackground.stop();
                    GameData.load(player);
                    sm.push(new MapState(sm, player));
                    System.out.println("Continued Game has been started.");
                }
            });
            /* DELETE
            continueButton = new Button("Continue", false, assets);
            continueButton.setParent(buttonTable);
            buttonTable.add(continueButton);
            */
            buttonTable.add(continueButton);
        }

        settingsButton = new TextButton("Setting", PocketHealer.ui);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                window.show(stage);
            }
        });

        buttonTable.add(settingsButton);

        initWindowFrame();

        //buttonTable.row();

        //buttonTable.row();



        animatedBackground = new AnimatedBackground();
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.mmBG),false, new Vector2(0,0),1f,assets));
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.mmBG2),false, new Vector2(0,0),2f,assets));
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.mmBG3),false, new Vector2(0,0),3f,assets));
        animatedBackground.add(new ScrollImage(assets.getTexture(assets.mmBG4),false, new Vector2(0,0),4f,assets));
        animatedBackground.setDebug(true);

        /*
        text = new Text("Press screen to continue...", 32, Color.WHITE, false, assets);
        text.setPosition(PocketHealer.WIDTH/2 - text.getXCenter(), 50);
        text.setAlignment(Align.center);
        */

        stage.addActor(animatedBackground);
        //stage.addActor(text);

        /*music = assets.getMusic(assets.mmMusic);
        music.setLooping(true);
        music.setVolume(0.4f);
        music.play();*/

        title = new Image(assets.getTexture(assets.title));
        title.setPosition(PocketHealer.WIDTH/2-title.getWidth()/2,PocketHealer.HEIGHT - title.getHeight());
        title.setName("title");

        stage.addActor(title);

        stage.addActor(buttonTable);

        animatedBackground.start();
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
    }

    public void initWindowFrame()   {
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
