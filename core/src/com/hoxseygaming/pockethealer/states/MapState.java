package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.AudioManager;
import com.hoxseygaming.pockethealer.BossIcon;
import com.hoxseygaming.pockethealer.InfoFrame;
import com.hoxseygaming.pockethealer.MapFrame;
import com.hoxseygaming.pockethealer.Player;

/**
 * Created by Hoxsey on 7/11/2017.
 */
public class MapState extends State {

    public Player player;
    public Stage stage;
    public int page;
    public int maxPage;
    public Assets assets;
    public MapFrame mapFrame;
    public BossIcon selectedLevel;
    InfoFrame infoFrame;

    public ImageButton pageLeft;
    public ImageButton pageRight;
    private TextButton infoButton;
    private TextButton talentButton;
    private TextButton startButton;
    private TextButton spellButton;
    private Table buttonTable;


    public MapState(StateManager sm, Player player) {
        super(sm);

        stage = new Stage(viewport);

        Gdx.input.setInputProcessor(stage);

        assets = player.getAssets();

        AudioManager.playMusic(assets.getMusic(assets.mmMusic), true);

        pageLeft = new ImageButton(assets.getSkin(), "page_left");
        pageLeft.setBounds(40, 550, 30,30);

        pageRight = new ImageButton(assets.getSkin(), "page_right");
        pageRight.setBounds(410, 550, 30,30);

        page = 1;
        maxPage = 1;

        this.player = player;

        //turnPage();
        startPage();
        createButtons();
        createButtonListeners();
    }

    public void turnPage()  {

        pageLeft.remove();
        pageRight.remove();

        mapFrame.remove();

        mapFrame.changePage(page);

        switch (page)   {
            case 1:
                stage.addActor(mapFrame);

                if(player.getLevel() > 6) {
                    stage.addActor(pageRight);
                    //stage.addActor(pageRight);
                }
                break;
            case 2:
                stage.addActor(mapFrame);

                if(player.getLevel() > 11) {
                    //pageRight.addToStage(stage);
                    stage.addActor(pageRight);
                }
                //pageLeft.addToStage(stage);
                stage.addActor(pageLeft);
                break;
            case 3:
                stage.addActor(mapFrame);
                //pageLeft.addToStage(stage);
                stage.addActor(pageLeft);
                break;
        }
        loadPage();
        createBossIconListeners();
        createButtons();

    }

    public void startPage()  {

        if(player.getLevel() <= 6)
            mapFrame = new MapFrame(player, 1, assets);
        else if(player.getLevel() <= 11 && player.getLevel() > 6)
            mapFrame = new MapFrame(player, 2, assets);
        else
            mapFrame = new MapFrame(player, 3, assets);


        pageLeft.remove();
        pageRight.remove();

        page = mapFrame.page;
        mapFrame.remove();

        switch (mapFrame.page)   {
            case 1:
                stage.addActor(mapFrame);

                if(player.getLevel() > 6) {
                    stage.addActor(pageRight);
                }
                break;
            case 2:
                stage.addActor(mapFrame);

                if(player.getLevel() > 11) {
                    stage.addActor(pageRight);
                }
                stage.addActor(pageLeft);
                break;
            case 3:
                stage.addActor(mapFrame);
                stage.addActor(pageLeft);
                break;
        }
        loadPage();
        createBossIconListeners();
    }

    public void loadPage()  {
        for(int i = 2 + (page-1)*5; i <= page*5+1; i++)   {
            if(player.getLevel() < i)
                return;
            mapFrame.setBoss(i);
        }
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
                if(Input.Keys.BACK == keycode || Input.Keys.BACKSPACE == keycode)    {
                    sm.set(new MainMenuState(sm, player));
                }
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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

    public void createButtons() {
        if(buttonTable != null) {
            buttonTable.remove();
        }
        else {
            buttonTable = new Table();
            buttonTable.setBounds(mapFrame.innerFrame.getX(), 20, mapFrame.innerFrame.getWidth(), mapFrame.innerFrame.getY()-15);

            talentButton = new TextButton("TALENT", assets.getSkin());

            startButton = new TextButton("START", assets.getSkin());

            spellButton = new TextButton("SPELL", assets.getSkin());

            buttonTable.add(talentButton).padRight(5).padTop(5);
            buttonTable.add(startButton).padRight(5).padLeft(5).padTop(5);
            buttonTable.add(spellButton).padLeft(5).padTop(5);
        }

        if(player.getTalentTree().getUnusedPoints() > 1)    {
            talentButton.setChecked(true);
        } else {
            talentButton.setChecked(false);
        }



        stage.addActor(buttonTable);
    }

    public void createButtonListeners()   {

        talentButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sm.set(new TalentSelectionState(sm, player));
            }
        });

        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(selectedLevel != null)    {
                    sm.set(new EncounterState(sm, player, selectedLevel.boss));
                }
            }
        });

        spellButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sm.set(new SpellSelectionState(sm, player));
            }
        });

        pageLeft.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                page--;
                turnPage();
                System.out.println("Page Left");
            }
        });

        pageRight.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                page++;
                turnPage();
                System.out.println("Page Right");
            }
        });
    }

    public void createBossIconListeners()   {
        for(int i = 0; i < mapFrame.bossIconsList.size(); i++)   {
            mapFrame.bossIconsList.get(i).addListener(new ChangeListener() {

                @Override
                public void changed(ChangeEvent event, Actor actor) {

                    if(selectedLevel != null)    {
                        selectedLevel.setChecked(false);
                    }

                    selectedLevel = (BossIcon) actor;

                    mapFrame.setTitle(selectedLevel.getName());
                    mapFrame.setBody(selectedLevel.getDescription());
                    mapFrame.infoFrame.addInfo(selectedLevel.boss);
                    mapFrame.disableInfoButton();
                    mapFrame.showInfoButton();
                }
            });
        }
    }

    @Override
    public void update(float dt) {
        //handleInput();
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
        System.out.println("Map Disposed!");
        mapFrame.dispose();
    }
}
