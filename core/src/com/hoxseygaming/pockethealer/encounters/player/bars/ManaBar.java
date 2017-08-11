package com.hoxseygaming.pockethealer.encounters.player.bars;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.Text;

/**
 * Created by Hoxsey on 6/20/2017.
 */
public class ManaBar extends Actor {

    private Player owner;
    private Assets assets;
    private Texture manaBar;
    private Texture manaFill;
    private Text text;

    public ManaBar(Player player, Assets assets)    {
        setBounds(0,95,480,49);
        owner = player;
        this.assets = assets;
        manaBar = assets.getTexture(assets.hpManaBar);
        manaFill = assets.getTexture(assets.manaFill);
        setupText();
    }

    private void setupText()    {

        text = new Text(owner.getMana()+"/"+owner.getMaxMana(), true, assets);
        text.setFontSize(32);
        text.setColor(Color.WHITE);
        text.setPosition(getX()+(getWidth()/2)-(text.getWidth()/2), getY()+getHeight()/2 -text.getHeight()/2);

    }

    public float getPercentage()    {
        return owner.manaBarPercent();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(manaBar, getX(),getY(),getWidth(),getHeight());
        batch.draw(manaFill, getX()+11,getY()+5,(getWidth()-20)*getPercentage(),37);
        text.setText(owner.getMana()+"/"+owner.getMaxMana());
        text.draw(batch,parentAlpha);
    }
}
