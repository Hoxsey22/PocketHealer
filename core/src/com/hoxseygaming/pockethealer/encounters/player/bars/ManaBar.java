package com.hoxseygaming.pockethealer.encounters.player.bars;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.EncounterData;

/**
 * Created by Hoxsey on 6/20/2017.
 */
public class ManaBar extends Actor {

    private Player owner;

    public ManaBar(Player player)    {
        setBounds(20,190,442,40);
        owner = player;
    }

    public float getPercentage()    {
        return owner.manaBarPercent();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(EncounterData.blackBar, getX(),getY(),getWidth(),getHeight());
        batch.draw(EncounterData.whiteBar, getX()+4,getY()+4,getWidth()-8,getHeight()-8);
        batch.draw(EncounterData.manaBar, getX()+4,getY()+4,(getWidth()-8)*getPercentage(),getHeight()-8);
    }
}
