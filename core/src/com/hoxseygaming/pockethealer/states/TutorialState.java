package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.AudioManager;
import com.hoxseygaming.pockethealer.BlinkingOutline;
import com.hoxseygaming.pockethealer.GameData;
import com.hoxseygaming.pockethealer.GameOverFrame;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.PocketHealer;
import com.hoxseygaming.pockethealer.TutorialFrame;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1.Monster;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Spell;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.TestEffect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 5/28/2017.
 */
public class TutorialState extends State {
    private final Player player;
    private Stage stage;
    private final Raid raid;
    private final Boss boss;
    //public Music bgMusic; // Delete
    private Image bgImage;
    private Assets assets;
    private GameOverFrame gameOverFrame;
    private final TutorialFrame tutorialFrame;
    private boolean isDone;
    private BlinkingOutline blinkingOutline;
    private ArrayList<Rectangle> outlines;
    private int page;


    public TutorialState(StateManager sm, Player player) {
        super(sm);
        this.player = player;
        this.player.loadTalents();
        this.player.reset();

        boss = new Monster(player.getAssets());
        boss.setPlayer(player);
        this.boss.reset();

        raid = this.boss.getEnemies();

        tutorialFrame = new TutorialFrame(player, boss, player.getAssets());

        player.setBoss(this.boss);
        raid.setPlayer(player);
        raid.getRaidMember(0).addStatusEffect(new TestEffect(boss));
        boss.getAnnouncement().setText("Monster is about to Bite!");
        player.setCasting(true);
        player.setTarget(raid.getRaidMember(0));
        create();
    }

    @Override
    public void create() {
        isDone = false;
        assets = player.getAssets();

        blinkingOutline = new BlinkingOutline();
        blinkingOutline.start();

        outlines = new ArrayList<>();
        // intro
        outlines.add(new Rectangle());
        // boss frame
        outlines.add(new Rectangle(boss.getX(), boss.getY(), boss.getWidth(), boss.getHeight()));
        // announcement
        outlines.add(new Rectangle(
                boss.getX(),
                raid.getRaidMember(0).getY()+raid.getRaidMember(0).getHeight()+2,
                boss.getWidth(),
                15));
        // raid frames
        outlines.add(new Rectangle(raid.getRaidMember(0).getX(), raid.getRaidMember(0).getY(), -raid.getRaidMember(0).getX()+(raid.getRaidMember(2).getX()+raid.getRaidMember(2).getWidth()), raid.getRaidMember(0).getHeight()));
        // Tank frame
        outlines.add(new Rectangle(raid.getRaidMember(0).getX(), raid.getRaidMember(0).getY(), raid.getRaidMember(0).getWidth(), raid.getRaidMember(0).getHeight()));
        // dps frame
        outlines.add(new Rectangle(raid.getRaidMember(1).getX(), raid.getRaidMember(1).getY(), raid.getRaidMember(1).getWidth(), raid.getRaidMember(1).getHeight()));
        // healer frame
        outlines.add(new Rectangle(raid.getRaidMember(2).getX(), raid.getRaidMember(2).getY(), raid.getRaidMember(2).getWidth(), raid.getRaidMember(2).getHeight()));
        // debuff and buff
        outlines.add(new Rectangle(
                raid.getRaidMember(0).getHealthBar().getX() + raid.getRaidMember(0).getHealthBar().getWidth() - 20 * (0) - 20,
                raid.getRaidMember(0).getHealthBar().getY() + raid.getRaidMember(0).getHealthBar().getHeight() + 5,
                20,
                20));
        // targeted unit
        outlines.add(new Rectangle(raid.getRaidMember(0).getX(), raid.getRaidMember(0).getY(), raid.getRaidMember(0).getWidth(), raid.getRaidMember(0).getHeight()));
        // targeting a unit
        outlines.add(new Rectangle(raid.getRaidMember(0).getX(), raid.getRaidMember(0).getY(), raid.getRaidMember(0).getWidth(), raid.getRaidMember(0).getHeight()));
        // spell bar
        outlines.add(new Rectangle(player.getSpellBar().getX(), player.getSpellBar().getY(), player.getSpellBar().getWidth(), player.getSpellBar().getHeight()));
        // mana bar
        outlines.add(new Rectangle(player.getManaBar().getX(), player.getManaBar().getY(), player.getManaBar().getWidth(), player.getManaBar().getHeight()));
        // cast bar
        outlines.add(new Rectangle(player.getCastBar().getX(), player.getCastBar().getY(), player.getCastBar().getWidth(), player.getCastBar().getHeight()));
        // conclusion
        outlines.add(new Rectangle());
        // last line
        outlines.add(new Rectangle());


        blinkingOutline.setOutline(outlines.get(0));

        player.setRaid(boss.getEnemies());

        AudioManager.playMusic(assets.getMusic(assets.battleMusic));

        bgImage = new Image(assets.getTexture("battle_bg1.png"));

        stage = new Stage(viewport);

        bgImage.setBounds(0,0,PocketHealer.WIDTH, PocketHealer.HEIGHT);

        // add all actors to the stageNumber
        stage.addActor(bgImage);
        stage.addActor(boss);
        stage.addActor(raid);
        stage.addActor(player.getSpellBar());
        stage.addActor(player.getManaBar());
        stage.addActor(player.getCastBar());
        stage.addActor(tutorialFrame);
        stage.addActor(blinkingOutline);
        //
        //boss.start();
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
                        for(int i = 0; i < raid.getRaidMembers().size(); i++)   {
                            raid.getRaidMembers().get(i).takeDamage(50);
                        }
                        break;
                    case Input.Keys.NUM_3:
                        raid.getRaidMembers().get(0).takeDamage(50);
                        break;
                    case Input.Keys.NUM_0:
                        for (int i = 0; i < raid.getRaidMembers().size(); i++)
                            System.out.println("ID:"+raid.getRaidMembers().get(i).getId()+", role:"+raid.getRaidMembers().get(i).getRole());
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
                Vector2 coord = stage.screenToStageCoordinates(new Vector2((float) screenX, (float) screenY));
                if (tutorialFrame.isComplete()) {
                    if (coord.y > 300) {
                        RaidMember raidTarget = (RaidMember) raid.hit(coord.x, coord.y, false);
                        if (raidTarget != null) {
                            System.out.println("found a target");
                            player.setTarget(raidTarget);
                            player.getTarget().selected();
                        }
                    } else {
                        if (!player.isCasting()) {
                            Spell spell = (Spell) player.getSpellBar().hit(coord.x, coord.y, false);
                            if (spell != null) {
                                spell.castSpell();
                            }
                        }
                    }
                }
                else{
                    tutorialFrame.nextStage();
                    if(!tutorialFrame.isComplete())
                        blinkingOutline.setOutline(outlines.get(tutorialFrame.stageNumber-1));
                    else {
                        blinkingOutline.stop();
                        blinkingOutline.remove();
                        tutorialFrame.remove();
                        player.setCasting(false);
                        boss.getAnnouncement().setText("");
                        raid.getRaidMember(0).getStatusEffectList().dispel();
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
                    player.setLevel(2);
                    player.save();
                }
                raid.loadHealingStats();
                gameOverFrame = new GameOverFrame(true, boss, assets);
                gameOverFrame.showHealingStats();
                page = 1;
                gameOverFrame.addListener(getEndGameListener());
                stage.addActor(gameOverFrame);
                boss.stop();
                raid.stop();
                player.stop();
                isDone = true;
            } else if (raid.isRaidDead()) {
                gameOverFrame = new GameOverFrame(false, boss, assets);
                gameOverFrame.showLose();
                gameOverFrame.addListener(getEndGameListener());
                stage.addActor(gameOverFrame);
                boss.stop();
                raid.stop();
                player.stop();
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
                            sm.set(new MapState(sm, player,1));
                            break;
                    }
                }
                else    {
                    int buttonHit = gameOverFrame.hitButton(coord.x, coord.y);
                    System.out.println(buttonHit);

                    if(buttonHit != -1) {
                        switch (buttonHit) {
                            case 0:
                                GameData.remove("save");
                                sm.set(new MainMenuState(sm, player));
                                break;

                            case 2:
                                System.out.println("reset");
                                sm.set(new TutorialState(sm, player));
                                break;
                        }
                    }
                }


                return false;
            }
        };
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
        AudioManager.clearAll();
    }
}

