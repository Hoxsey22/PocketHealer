package com.hoxseygaming.pockethealer.encounters.player.bars;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.EncounterData;

/**
 * Created by Hoxsey on 6/20/2017.
 */
public class ManaBar extends Actor {

    private Player owner;
    private Assets assets;

    public ManaBar(Player player, Assets assets)    {
        setBounds(20,190,442,40);
        owner = player;
        this.assets = assets;
    }

    public float getPercentage()    {
        return owner.manaBarPercent();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(assets.getTexture("black_bar.png"), getX(),getY(),getWidth(),getHeight());
        batch.draw(assets.getTexture("white_bar.png"), getX()+4,getY()+4,getWidth()-8,getHeight()-8);
        batch.draw(assets.getTexture("mana_bar.png"), getX()+4,getY()+4,(getWidth()-8)*getPercentage(),getHeight()-8);
    }
}
