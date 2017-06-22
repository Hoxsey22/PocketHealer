package com.hoxseygaming.pockethealer.reformat.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hoxseygaming.pockethealer.reformat.EncounterData;

/**
 * Created by Hoxsey on 6/20/2017.
 */
public class CastBar extends Actor {

    private Player owner;

    public CastBar(Player player)    {
        setBounds(20,235,442,40);
        owner = player;
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
            batch.draw(EncounterData.blackBar, getX(), getY(), getWidth(), getHeight());
            batch.draw(EncounterData.whiteBar, getX() + 4, getY() + 4, getWidth() - 8, getHeight() - 8);
            batch.draw(EncounterData.castBar, getX() + 4, getY() + 4, (getWidth() - 8) * getPercentage(), getHeight() - 8);
        }
    }
}
