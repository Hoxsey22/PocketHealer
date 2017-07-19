package com.hoxseygaming.pockethealer.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 7/11/2017.
 */
public class Level extends Actor {

    public int id;
    public Boss owner;
    public String description;
    public Assets assets;

    public Level(int id, Boss boss, String description, Assets assets)  {
        this.id = id;
        owner = boss;
        this.description = description;
        this.assets = assets;
    }

    public void enterEncounter() {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(owner.isMini)    {
            batch.draw(assets.getTexture(assets.miniBossIcon), getX(), getY(),getWidth(),getHeight());
        }
        else    {
            batch.draw(assets.getTexture(assets.bossIcon), getX(), getY(),getWidth(),getHeight());
        }
    }
}
