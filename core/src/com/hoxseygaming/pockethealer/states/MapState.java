package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.BossIcon;
import com.hoxseygaming.pockethealer.Button;
import com.hoxseygaming.pockethealer.LevelInfo;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.PocketHealer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Hogger;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.WampusCat;

/**
 * Created by Hoxsey on 7/11/2017.
 */
public class MapState extends State {

    public Player player;
    public Stage stage;
    public Image bgMap;
    public int page;
    public int maxPage;
    public Group bossList;
    public Button talentButton;
    public Button spellButton;
    public Button infoButton;
    public LevelInfo levelInfo;
    public Assets assets;
    public BossIcon selectedLevel;


    public MapState(StateManager sm, Player player) {
        super(sm);

        stage = new Stage(new FitViewport(480, 800));

        page = 0;
        maxPage = 1;

        this.player = player;
        assets = player.getAssets();

        bgMap = new Image(assets.getTexture(assets.mapBg));
        bgMap.setName("Map");
        bgMap.setBounds(0,0, PocketHealer.WIDTH, PocketHealer.HEIGHT);

        levelInfo = new LevelInfo(assets);


        talentButton = new Button("Talent", new Image(assets.getTexture(assets.talentButton)),0,0,160,100);
        spellButton = new Button("Spell", new Image(assets.getTexture(assets.spellButton)), (int)(talentButton.getX()+talentButton.getWidth()), 0,160, 100);
        infoButton = new Button("Info", new Image(assets.getTexture(assets.infoButton)),(int)(spellButton.getX()+spellButton.getWidth()), 0,160, 100);





        bossList = new Group();
        nextPage();

        stage.addActor(bgMap);
        stage.addActor(talentButton);
        stage.addActor(spellButton);
        stage.addActor(infoButton);


        stage.addActor(bossList);
        stage.addActor(levelInfo);


    }

    public void nextPage()  {
        if(player.getLevel() >= page + 1 && page + 1 <= maxPage) {
            page++;
            loadPage();
        }
    }

    public void loadPage()  {
        bossList.clear();
        switch (page)   {
            case 1:
                bossList.addActor(new BossIcon(assets, new Hogger(assets)));
                bossList.addActor(new BossIcon(assets, new WampusCat(assets)));
                break;
            case 2:
                break;
            case 3:
                break;
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
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector2 coord = stage.screenToStageCoordinates(new Vector2((float) screenX, (float) screenY));
                System.out.println("x:" + coord.x + " y:" + coord.y);
                if (coord.y < 150) {
                    Actor hitActor = stage.hit(coord.x, coord.y, false);
                    if (hitActor != null) {

                        switch (hitActor.getName()) {
                            case "Talent":
                                sm.push(new TalentState(sm, player));
                                break;
                            case "Spell":
                                //sm.push(new SpellBookState(sm, player));
                                break;
                            case "Info":
                                levelInfo.displayInfo();
                                break;
                        }
                    }
                } else {
                    if(!levelInfo.isActive) {
                        BossIcon bi = (BossIcon) bossList.hit(coord.x, coord.y, false);
                        if (bi != null) {
                            if(selectedLevel != null)   {
                                selectedLevel.unselect();
                            }
                            selectedLevel = bi;
                            selectedLevel.select();

                            levelInfo.setInfo(bi);
                        }
                    }
                    else {
                        if(levelInfo.hit(coord.x, coord.y))    {
                            sm.push(new EncounterState(sm, player, levelInfo.getBoss()));
                        }
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
        update(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}
