package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.GameOverFrame;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.PocketHealer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.player.bars.CastBar;
import com.hoxseygaming.pockethealer.encounters.player.bars.ManaBar;
import com.hoxseygaming.pockethealer.encounters.spells.Spell;

/**
 * Created by Hoxsey on 5/28/2017.
 */
public class EncounterState extends State {
    public Player player;
    public ManaBar manaBar;
    public CastBar castBar;
    public Stage stage;
    public Raid raid;
    public Boss boss;
    public Music bgMusic;
    public Image bgImage;
    public Assets assets;
    public GameOverFrame gameOverFrame;
    public boolean isDone;


    public EncounterState(StateManager sm, Player player, Boss boss) {
        super(sm);
        this.player = player;
        this.player.loadTalents();
        this.player.reset();

        this.boss = boss;
        boss.setPlayer(player);
        this.boss.reset();

        raid = this.boss.getEnemies();

        player.setBoss(this.boss);

        create();
    }

    @Override
    public void create() {
        //super.create();
        isDone = false;
        assets = player.assets;
        //player.createSpellBar();
        //player.addDebuggingSpell();

        manaBar = new ManaBar(player, assets);

        castBar = new CastBar(player, assets);
        castBar.anchor(manaBar);
        //raid = new Raid(10, assets);
        player.setRaid(boss.getEnemies());

        //hogger = new Hogger(assets);

        bgMusic = assets.getMusic("sfx/battle_music.ogg");
        bgMusic.setLooping(true);
        bgMusic.setVolume(0.3f);
        bgMusic.play();

        bgImage = new Image(assets.getTexture("battle_bg1.png"));




        stage = new Stage(viewport);
        bgImage.setBounds(0,0,PocketHealer.WIDTH, PocketHealer.HEIGHT);

        // add all actors to the stage
        stage.addActor(bgImage);
        stage.addActor(boss);
        stage.addActor(raid);
        stage.addActor(player.spellBar);
        stage.addActor(manaBar);
        stage.addActor(castBar);
        //
        boss.start();
        System.out.println("STAGE - > Width:"+stage.getWidth()+" Height:"+stage.getHeight());

    }

    @Override
    protected void handleInput() {

        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode)    {
                    case Input.Keys.NUM_1:
                        boss.takeDamage(1000);
                        break;
                    case Input.Keys.NUM_2:
                        for(int i = 0; i < raid.raidMembers.size(); i++)   {
                            raid.raidMembers.get(i).takeDamage(50);
                        }
                        break;
                    case Input.Keys.NUM_3:
                        raid.raidMembers.get(0).takeDamage(50);
                        break;
                    case Input.Keys.NUM_0:
                        for (int i = 0; i < raid.raidMembers.size(); i++)
                            System.out.println("ID:"+raid.raidMembers.get(i).id+", role:"+raid.raidMembers.get(i).role);
                        break;
                }
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
                if(coord.y > 300) {
                    RaidMember raidTarget = (RaidMember) raid.hit(coord.x, coord.y, false);
                    if (raidTarget != null) {
                        System.out.println("found a target");
                        player.setTarget(raidTarget);
                        player.getTarget().selected();
                    }
                }
                else    {
                    if(!player.isCasting) {
                        Spell spell = (Spell) player.spellBar.hit(coord.x, coord.y, false);
                        if (spell != null) {
                            spell.castSpell();
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

    protected void endGameHandleInput() {

        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode)    {
                    case Input.Keys.NUM_1:
                        break;
                    case Input.Keys.NUM_2:
                        break;
                    case Input.Keys.NUM_3:
                        break;
                    case Input.Keys.NUM_0:
                        break;
                }
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
                String buttonHit = gameOverFrame.hitButton(coord.x, coord.y);
                if(buttonHit != "") {
                    switch (buttonHit) {
                        case "finish":
                            player.newLevel(boss.getLevel());
                            bgMusic.stop();
                            sm.set(new MapState(sm, player));
                            break;
                        case "leave":
                            bgMusic.stop();
                            sm.set(new MapState(sm, player));
                            break;
                        case "reset":
                            System.out.println("reset");
                            bgMusic.stop();
                            sm.set(new EncounterState(sm, player, boss));
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
        if(!isDone) {
            handleInput();
            boss.update();
            if (boss.isDead()) {
                if(!boss.isDefeated())    {
                    boss.reward();
                    boss.setDefeated(true);
                    player.setLevel(boss.getId());
                    player.save();
                }
                gameOverFrame = new GameOverFrame(true, boss, assets);
                stage.addActor(gameOverFrame);
                gameOverFrame.setDebug(true);
                boss.stop();
                raid.stop();
                player.stop();
                isDone = true;
            } else if (raid.isRaidDead()) {
                gameOverFrame = new GameOverFrame(false, boss, assets);
                gameOverFrame.setDebug(true);
                stage.addActor(gameOverFrame);
                boss.stop();
                raid.stop();
                player.stop();
                isDone = true;

            }
        }
        else {
            endGameHandleInput();
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        //System.out.println("render");
        Gdx.gl.glClearColor(Color.TAN.r,Color.TAN.g,Color.TAN.b,Color.TAN.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height,true);
        System.out.println("Width:"+width+" Height:"+height);
    }

    @Override
    public void dispose() {
    }
}

