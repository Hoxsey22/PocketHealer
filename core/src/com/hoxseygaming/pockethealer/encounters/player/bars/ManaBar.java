package com.hoxseygaming.pockethealer.encounters.player.bars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.EncounterData;

/**
 * Created by Hoxsey on 6/20/2017.
 */
public class ManaBar extends Actor {

    private Player owner;
    private Assets assets;
    private BitmapFont font;
    private Label label;

    public ManaBar(Player player, Assets assets)    {
        setBounds(20,190,442,40);
        owner = player;
        this.assets = assets;
        setupFont();
    }

    private void setupFont()    {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;

        label = new Label(owner.getMana()+"/"+owner.getMaxMana(),labelStyle);
        label.setSize(getWidth()/4, getHeight()-5);
        label.setPosition(getX()+(getWidth()/2)-(label.getWidth()/2), getY()+ 2);
        label.setAlignment(Align.center);



    }

    public float getPercentage()    {
        return owner.manaBarPercent();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(assets.getTexture("black_bar.png"), getX(),getY(),getWidth(),getHeight());
        batch.draw(assets.getTexture("white_bar.png"), getX()+4,getY()+4,getWidth()-8,getHeight()-8);
        batch.draw(assets.getTexture("mana_bar.png"), getX()+4,getY()+4,(getWidth()-8)*getPercentage(),getHeight()-8);
        label.setText(owner.getMana()+"/"+owner.getMaxMana());
        label.draw(batch,parentAlpha);
    }
}
