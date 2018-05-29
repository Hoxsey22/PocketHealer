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

        text = new Text(owner.getMana()+"/"+owner.getMaxMana(),32, Color.WHITE, true, assets);
        text.setPosition(getX()+(getWidth()/2)-(text.getWidth()/2), getY()+getHeight()/2 -text.getHeight()/2);

    }

    public float getPercentage()    {
        return owner.manaBarPercent();
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public Texture getManaBar() {
        return manaBar;
    }

    public void setManaBar(Texture manaBar) {
        this.manaBar = manaBar;
    }

    public Texture getManaFill() {
        return manaFill;
    }

    public void setManaFill(Texture manaFill) {
        this.manaFill = manaFill;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(manaBar, getX(),getY(),getWidth(),getHeight());
        batch.draw(manaFill, getX()+11,getY()+5,(getWidth()-20)*getPercentage(),37);
        text.setText(owner.getMana()+"/"+owner.getMaxMana());
        text.draw(batch,parentAlpha);
    }
}
