package com.hoxseygaming.pockethealer.bosses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.bosses.Boss;
import com.hoxseygaming.pockethealer.players.Member;
import com.hoxseygaming.pockethealer.players.Party;
import com.hoxseygaming.pockethealer.spells.Spell;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/3/2017.
 */
public class Hogger extends Boss {

    private Timer tankDamageTimer;
    private Timer stompTimer;
    private Timer announcementTimer;
    private SpriteBatch sb;
    BitmapFont font;
    public Texture name;
    public String announcement = "Hogger is getting mad and is about to stomp in a fit of rage!";

    public Hogger(Party party, SpriteBatch sb) {
        super(party, 12000);
        this.sb = sb;
        getTank();
        startTimers();
        name = new Texture("hogger_name.png");
    }

    public Texture getName() {
        return name;
    }

}
