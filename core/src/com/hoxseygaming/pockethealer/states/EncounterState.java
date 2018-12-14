package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.AudioManager;
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
    private final Player player;
    private ManaBar manaBar;
    private CastBar castBar;
    private Stage stage;
    private final Raid raid;
    private final Boss boss;
    //public Music bgMusic;
    private Image bgImage;
    private Assets assets;
    private GameOverFrame gameOverFrame;
    private boolean isDone;
    private int page;
    // window frame to confirm quit
    //WindowFrame quitWindow;
    // --Commented out by Inspection (5/29/2018 8:27 PM):Label quitWindowText;
    // --Commented out by Inspection (5/29/2018 8:27 PM):TextButton yesButton;
    // --Commented out by Inspection (5/29/2018 8:27 PM):TextButton noButton;

    //commit
    public EncounterState(StateManager sm, Player player, Boss boss) {
        super(sm);
        this.player = player;
        this.player.loadTalents();
        this.player.reset();

        this.boss = boss;
        this.boss.reset();
        boss.setPlayer(player);


        //Gdx.input.setCursorCatched(true);

        raid = this.boss.getEnemies();
        raid.setPlayer(player);

        player.setBoss(this.boss);

        create();
    }

    @Override
    public void create() {
        //super.create();
        isDone = false;
        assets = player.getAssets();
        //player.createSpellBar();
        //player.addDebuggingSpell();

        manaBar = new ManaBar(player, assets);

        castBar = new CastBar(player, assets);
        castBar.anchor(manaBar);
        //raid = new Raid(10, assets);
        player.setRaid(boss.getEnemies());

        //hogger = new Hogger(assets);

        AudioManager.playMusic(assets.getMusic(assets.battleMusic));

        /* DELETE
        bgMusic = assets.getMusic("sfx/battle_music.ogg");
        bgMusic.setLooping(true);
        bgMusic.setVolume(0.3f);
        bgMusic.play();
        */
        if(boss.getId() <= 6)    {
            bgImage = new Image(assets.getTexture(assets.battleBg1));
        }
        else if(boss.getId() >= 7 && boss.getId() <= 11)    {
            bgImage = new Image(assets.getTexture(assets.battleBg2));
        }
        else    {
            bgImage = new Image(assets.getTexture(assets.battleBg3));
        }
        //bgImage = new Image(assets.getTexture("battle_bg1.png"));
        bgImage.setX(bgImage.getWidth()/2-PocketHealer.WIDTH/2);




        stage = new Stage(viewport);
        bgImage.setBounds(0,0,PocketHealer.WIDTH, PocketHealer.HEIGHT);

        // add all actors to the stageNumber
        stage.addActor(bgImage);
        stage.addActor(boss);
        stage.addActor(raid);
        stage.addActor(player.getSpellBar());
        stage.addActor(manaBar);
        stage.addActor(castBar);
        //
        boss.start();

        // remove after picture
        //player.setMana(400);
        System.out.println("STAGE - > Width:"+stage.getWidth()+" Height:"+stage.getHeight());

    }

    @Override
    protected void handleInput() {

        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode)    {
                    case Input.Keys.NUM_0:
                        boss.takeDamage(1000);
                        break;
                    case Input.Keys.NUM_9:
                        for(int i = 0; i < raid.getRaidMembers().size(); i++)   {
                            raid.getRaidMembers().get(i).takeDamage(50);
                        }
                        break;
                    case Input.Keys.NUM_1:
                        if(player.getSpellBar().getSpells().size() > 0)    {
                            if(!player.isCasting())
                                player.getSpellBar().getSpells().get(0).castSpell();
                        }
                        break;
                    case Input.Keys.NUM_2:
                        if(player.getSpellBar().getSpells().size() > 1)    {
                            if(!player.isCasting())
                                player.getSpellBar().getSpells().get(1).castSpell();
                        }
                        break;
                    case Input.Keys.NUM_3:
                        if(player.getSpellBar().getSpells().size() > 2)    {
                            if(!player.isCasting())
                                player.getSpellBar().getSpells().get(2).castSpell();
                        }
                        break;
                    case Input.Keys.NUM_4:
                        if(player.getSpellBar().getSpells().size() > 3)    {
                            if(!player.isCasting())
                                player.getSpellBar().getSpells().get(3).castSpell();
                        }
                        break;
                    case Input.Keys.L:
                        System.out.println("********** Raid Stats **********");
                        System.out.println("id|role|maxhp|hp|damage");
                        for(int i = 0; i < boss.getEnemies().getRaidMembers().size(); i++)   {
                            System.out.println(boss.getEnemies().getRaidMember(i).getId() + "|"+
                                    boss.getEnemies().getRaidMember(i).getRole() + "|"+
                                    boss.getEnemies().getRaidMember(i).getMaxHp() + "|"+
                                    boss.getEnemies().getRaidMember(i).getHp() + "|"+
                                    boss.getEnemies().getRaidMember(i).getDamage());
                        }
                        break;
                    case Input.Keys.BACK:

                        break;
                    case Input.Keys.BACKSPACE:
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
                    if(!player.isCasting()) {
                        Spell spell = (Spell) player.getSpellBar().hit(coord.x, coord.y, false);
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

// --Commented out by Inspection START (5/29/2018 8:27 PM):
//    protected void endGameHandleInput() {
//
//        Gdx.input.setInputProcessor(new InputProcessor() {
//            @Override
//            public boolean keyDown(int keycode) {
//                switch (keycode)    {
//                    case Input.Keys.NUM_1:
//                        break;
//                    case Input.Keys.NUM_2:
//                        break;
//                    case Input.Keys.NUM_3:
//                        break;
//                    case Input.Keys.NUM_0:
//                        break;
//                }
//                return false;
//            }
//
//            @Override
//            public boolean keyUp(int keycode) {
//                return false;
//            }
//
//            @Override
//            public boolean keyTyped(char character) {
//                return false;
//            }
//
//            @Override
//            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//                Vector2 coord = stage.screenToStageCoordinates(new Vector2((float)screenX,(float)screenY));
//
//                if(gameOverFrame.isWon())   {
//                    int buttonHit = gameOverFrame.hitButton(coord.x, coord.y);
//
//                    if(buttonHit != -1)    {
//                        switch (buttonHit)  {
//                            case 1:
//                                switch (page) {
//                                    case 1:
//                                        gameOverFrame.showReward();
//                                        page = 2;
//                                        break;
//
//                                    case 2:
//                                        player.newLevel(boss.getLevel());
//                                        sm.set(new MapState(sm, player));
//                                        break;
//                                }
//                                break;
//                        }
//                    }
//                }
//                else    {
//                    int buttonHit = gameOverFrame.hitButton(coord.x, coord.y);
//                    System.out.println(buttonHit);
//
//                    if(buttonHit != -1) {
//                        switch (buttonHit) {
//                            case 0:
//                                sm.set(new MapState(sm, player));
//                                break;
//
//                            case 2:
//                                System.out.println("reset");
//                                boss.reset();
//                                sm.set(new EncounterState(sm, player, boss));
//                                break;
//                        }
//                    }
//                }
//
//
//                return false;
//            }
//
//            @Override
//            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//                return false;
//            }
//
//            @Override
//            public boolean touchDragged(int screenX, int screenY, int pointer) {
//                return false;
//            }
//
//            @Override
//            public boolean mouseMoved(int screenX, int screenY) {
//                return false;
//            }
//
//            @Override
//            public boolean scrolled(int amount) {
//                return false;
//            }
//        });
//
//    }
// --Commented out by Inspection STOP (5/29/2018 8:27 PM)

    @Override
    public void update(float dt) {
        if(!isDone) {
            handleInput();
            boss.update();
            if (boss.isDead()) {
                page = 2;
                if(!boss.isDefeated())    {
                    boss.reward();
                    boss.setDefeated(true);
                    player.setLevel(boss.getId()+1);
                    player.save();
                    page = 1;
                }
                raid.loadHealingStats();
                gameOverFrame = new GameOverFrame(true, boss, assets);
                gameOverFrame.showHealingStats();
                gameOverFrame.addListener(getEndGameListener());

                stage.addActor(gameOverFrame);
                stop();
                /*boss.stop();
                raid.stop();
                player.stop();*/
                isDone = true;
            } else if (raid.isRaidDead()) {
                gameOverFrame = new GameOverFrame(false, boss, assets);
                gameOverFrame.showLose();
                gameOverFrame.addListener(getEndGameListener());
                stage.addActor(gameOverFrame);
                stop();
                /*boss.stop();
                raid.stop();
                player.stop();*/
                isDone = true;

            }
        }
        else {
            Gdx.input.setInputProcessor(stage);
            //endGameHandleInput();
        }

    }

    private InputListener getEndGameListener()   {
        return new InputListener()   {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Vector2 coord = stage.screenToStageCoordinates(new Vector2(x, y));

                if(gameOverFrame.isWon())   {
                    switch (page)   {
                        case 1:
                            gameOverFrame.showReward();
                            page = 2;
                            break;
                        case 2:
                            player.newLevel(boss.getLevel());
                            switch (boss.getId()) {
                                case 6:
                                    sm.set(new MapState(sm, player,2));
                                    break;
                                case 11:
                                    sm.set(new MapState(sm, player,3));
                                    break;
                                case 16:
                                    sm.set(new MapState(sm, player,4));
                                    break;
                                default:
                                    sm.set(new MapState(sm, player));
                                    break;
                            }
                    }
                }
                else    {
                    int buttonHit = gameOverFrame.hitButton(coord.x, coord.y);
                    System.out.println(buttonHit);

                    if(buttonHit != -1) {
                        switch (buttonHit) {
                            case 0:
                                sm.set(new MapState(sm, player));
                                break;

                            case 2:
                                System.out.println("reset");
                                sm.set(new EncounterState(sm, player, boss));
                                break;
                        }
                    }
                }


                return false;
            }
        };
    }

    /**
     * Stops all timers in boss raid and player.
     */
    private void stop()  {
        boss.stop();
        raid.stop();
        raid.getHealingTracker().printHealingDone();
        player.stop();

    }

    @Override
    public void render(SpriteBatch sb) {
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

