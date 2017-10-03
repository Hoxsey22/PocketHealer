package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.BossIcon;
import com.hoxseygaming.pockethealer.Button;
import com.hoxseygaming.pockethealer.MapFrame;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.PocketHealer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Apprentice;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.BanditLeader;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.BloodQueen;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.GiantHornet;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Golem;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Hogger;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.MotherSpider;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Proctor;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Sorcerer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.TestBoss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Tiger;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.WampusCat;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.WildBoar;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.ZombieHorde;

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


    public MapState(StateManager sm, Player player) {
        super(sm);

        stage = new Stage(viewport);

        page = 0;
        maxPage = 1;

        this.player = player;
        assets = player.getAssets();

        nextPage();



    }

    public void nextPage()  {
        //needs to change
        if(player.getLevel() >= page) {
            page++;
            mapFrame = new MapFrame(player,page, assets);
            stage.addActor(mapFrame);
            loadPage();
        }
    }

    public void loadPage()  {

        switch (page)   {
            case 1:
                mapFrame.add(new BossIcon(assets, new WildBoar(assets)));
                mapFrame.add(new BossIcon(assets, new Tiger(assets)));
                mapFrame.add(new BossIcon(assets, new GiantHornet(assets)));
                mapFrame.add(new BossIcon(assets, new Golem(assets)));
                mapFrame.add(new BossIcon(assets, new BanditLeader(assets)));
                mapFrame.add(new BossIcon(assets, new Hogger(assets)));
                mapFrame.add(new BossIcon(assets, new Proctor(assets)));
                mapFrame.add(new BossIcon(assets, new WampusCat(assets)));
                mapFrame.add(new BossIcon(assets, new Apprentice(assets)));
                mapFrame.add(new BossIcon(assets, new Sorcerer(assets)));
                mapFrame.add(new BossIcon(assets, new MotherSpider(assets)));
                mapFrame.add(new BossIcon(assets, new ZombieHorde(assets)));
                mapFrame.add(new BossIcon(assets, new BloodQueen(assets)));
                mapFrame.add(new BossIcon(assets, new TestBoss(assets)));
                /*
                mapFrame.getMap().add(new BossIcon(assets, new CrazyProfessor(assets)));
                mapFrame.getMap().add(new BossIcon(assets, new BloodQueen(assets)));
                mapFrame.getMap().add(new BossIcon(assets, new DeathDragon(assets)));
                */

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

                if(coord.y > mapFrame.innerFrame.getY())    {
                    BossIcon bi = mapFrame.getMap().hit(coord.x, coord.y);
                    if(bi != null)    {
                        if(selectedLevel != null)    {
                            selectedLevel.unselect();
                        }
                        selectedLevel = bi;
                        mapFrame.setTitle(bi.getName());
                        mapFrame.setBody(bi.getDescription());
                        bi.select();
                        mapFrame.startButton.setHighlight(true);
                    }
                }
                if(coord.y < mapFrame.talentButton.getY() + mapFrame.talentButton.getHeight())    {
                    Button hit = mapFrame.hitButton(coord.x, coord.y);
                    if(hit != null)    {
                        switch (hit.getName())   {
                            case "TALENTS":
                                sm.set(new TalentSelectionState(sm, player));
                                break;
                            case "START":
                                if(selectedLevel != null)    {
                                    PocketHealer.music.dispose();
                                    sm.set(new EncounterState(sm, player, selectedLevel.boss));
                                }
                                break;
                            case "SPELLS":
                                sm.set(new SpellSelectionState(sm, player));
                                break;
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
