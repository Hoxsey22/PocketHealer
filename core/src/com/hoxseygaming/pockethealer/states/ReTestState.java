package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hoxseygaming.pockethealer.PocketHealer;
import com.hoxseygaming.pockethealer.reformat.player.CastBar;
import com.hoxseygaming.pockethealer.reformat.player.ManaBar;
import com.hoxseygaming.pockethealer.reformat.player.Player;
import com.hoxseygaming.pockethealer.reformat.entities.raid.Raid;
import com.hoxseygaming.pockethealer.reformat.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.reformat.entities.bosses.Hogger;
import com.hoxseygaming.pockethealer.reformat.spells.Heal;
import com.hoxseygaming.pockethealer.reformat.spells.Spell;

/**
 * Created by Hoxsey on 5/28/2017.
 */
public class ReTestState extends State {
    Player player;
    ManaBar manaBar;
    CastBar castBar;
    Stage stage;
    Raid raid;
    Hogger hogger;
    Image img;

    public ReTestState(StateManager sm, SpriteBatch sb) {
        super(sm);
        create();
    }

    @Override
    public void create() {
        //super.create();
        player = new Player();
        manaBar = new ManaBar(player);
        castBar = new CastBar(player);
        raid = new Raid(10);
        hogger = new Hogger(raid);

        stage = new Stage(new FitViewport(PocketHealer.WIDTH,PocketHealer.HEIGHT));

        // add all actors to the stage
        stage.addActor(hogger);
        stage.addActor(raid);
        stage.addActor(player.spellBar);
        stage.addActor(manaBar);
        stage.addActor(castBar);
        //
        hogger.start();
        raid.startRaidDamageTimer(hogger);
        System.out.println("STAGE - > Width:"+stage.getWidth()+" Height:"+stage.getHeight());

    }

    @Override
    protected void handleInput() {

        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode)    {
                    case Input.Keys.NUM_1:
                        player.mana = player.mana - 50;
                        break;
                    case Input.Keys.NUM_2:
                        hogger.hp = hogger.hp - 50;
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

    @Override
    public void update(float dt) {
        handleInput();
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

