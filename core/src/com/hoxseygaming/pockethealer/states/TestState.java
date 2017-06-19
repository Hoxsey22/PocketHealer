package com.hoxseygaming.pockethealer.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hoxseygaming.pockethealer.GUI;
import com.hoxseygaming.pockethealer.HealingEngine;
import com.hoxseygaming.pockethealer.PocketHealer;
import com.hoxseygaming.pockethealer.bosses.Boss;
import com.hoxseygaming.pockethealer.bosses.Hogger;
import com.hoxseygaming.pockethealer.players.Party;
import com.hoxseygaming.pockethealer.players.Player;
import com.hoxseygaming.pockethealer.spells.Spell;

/**
 * Created by Hoxsey on 5/28/2017.
 */
public class TestState extends State {
    Player player;
    Party party;
    HealingEngine he;
    ShapeRenderer sr;
    Boss boss;
    GUI gui;

    public TestState(StateManager sm, SpriteBatch sb) {
        super(sm);
        player = new Player();
        party = new Party();
        party.addPremadeSizeParty(10);
        boss = new Hogger(party, sb);
        gui = new GUI(player,party,boss);


        he = new HealingEngine(player, party);
        sr = new ShapeRenderer();
    }


    @Override
    protected void handleInput() {
        he.handleInput();
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        Gdx.gl.glClearColor(Color.TAN.r,Color.TAN.g,Color.TAN.b,Color.TAN.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);

        gui.draw(sb);

        he.draw(sb);

        /*
        // spacing
        sr.begin(ShapeRenderer.ShapeType.Line);
            sr.setColor(Color.WHITE);
                sr.line(0,20,PocketHealer.WIDTH,20);
                sr.line(0,120,PocketHealer.WIDTH,120);
                //sr.line(0,170, PocketHealer.WIDTH, 170);
                sr.line(0,PocketHealer.HEIGHT-80, PocketHealer.WIDTH,PocketHealer.HEIGHT-80);
                sr.line(0,PocketHealer.HEIGHT-120, PocketHealer.WIDTH,PocketHealer.HEIGHT-120);
        sr.end();

        // raid location top to buttom
        sr.begin(ShapeRenderer.ShapeType.Line);
            sr.setColor(Color.FIREBRICK);
            sr.rect(20,PocketHealer.HEIGHT-20, PocketHealer.WIDTH - 40, -40);   //boss frame
            for(int i = 660; i >  300; i = i - 15 - 64)   {
                for(int k = 20; k < 480; k = k + 134+20)   {
                    sr.rect(k, i, 134, -64);
                }
            }

            sr.rect(10,170, 460,30);
            // mana bar
            sr.rect(10,130, 460, 30);
            //spell location top to buttom
            for(int i = 110; i > 20; i = i - 90 - 10)   {
                for(int k = 20; k < 480; k = k + 95 + 20 )   {
                    System.out.println("x:"+k+", y:"+i+":95,80");
                    //sr.rect(k, i, 95, -80);
                }
            }*/


    }

    @Override
    public void dispose() {
    }
}

