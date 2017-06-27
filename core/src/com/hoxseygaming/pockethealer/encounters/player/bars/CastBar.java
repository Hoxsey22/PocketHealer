package com.hoxseygaming.pockethealer.encounters.player.bars;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.EncounterData;

/**
 * Created by Hoxsey on 6/20/2017.
 */
public class CastBar extends Actor {

    private Player owner;
    private Assets assets;

    public CastBar(Player player, Assets assets)    {
        setBounds(20,235,442,40);
        owner = player;
        this.assets = assets;
    }

    public boolean isActive() {
        return owner.isCasting();
    }

    public float getPercentage()    {
        return owner.getSpellCastPercent();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isActive()) {
            batch.draw(assets.getTexture("black_bar.png"), getX(), getY(), getWidth(), getHeight());
            batch.draw(assets.getTexture("white_bar.png"), getX() + 4, getY() + 4, getWidth() - 8, getHeight() - 8);
            batch.draw(assets.getTexture("casting_bar.png"), getX() + 4, getY() + 4, (getWidth() - 8) * getPercentage(), getHeight() - 8);
        }
    }
}
