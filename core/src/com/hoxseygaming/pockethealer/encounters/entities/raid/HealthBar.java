package com.hoxseygaming.pockethealer.encounters.entities.raid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.hoxseygaming.pockethealer.Assets;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class HealthBar {

    public float x;
    public float y;
    public float width;
    public float height;
    private Assets assets;
    private Texture healthBar;
    private RaidMember owner;


    public HealthBar(RaidMember owner, int x, int y, int width, int height) {
        this.owner = owner;
        assets = owner.assets;
        this.x = x + 9;
        this.y = y + 7;
        this.width = width - 19;
        this.height = height / 4;

        healthBar = assets.getTexture(assets.hpManaBar);

    }

    public HealthBar(RaidMember owner) {
        this.owner = owner;
        assets = owner.assets;

        x = owner.getX() + 10;
        y = owner.getY() + 5;
        width = owner.getWidth() - 20;
        height = owner.getHeight() / 4f;

        healthBar = assets.getTexture(assets.hpManaBar);

    }

    public void draw(Batch batch, float alpha)  {
        batch.draw(healthBar, x,y,width,height);
        if(owner.getHealthPercent() < 0.3)
            batch.draw(assets.getTexture(assets.redFill), x+3,y+2,(width-6)*owner.getHealthPercent(),height-4);
        else if(owner.getHealthPercent() < 0.7)   {
            batch.draw(assets.getTexture(assets.yellowFill), x+3,y+2,(width-6)*owner.getHealthPercent(),height-4);
        }
        else    {
            batch.draw(assets.getTexture(assets.greenFill), x+3,y+2,(width-6)*owner.getHealthPercent(),height-4);
        }
        batch.draw(assets.getTexture(assets.manaFill), x+3,y+2,(width-6)*owner.getShieldPercent(),height-4);
        batch.draw(assets.getTexture(assets.purpleFill), x+3,y+2,(width-6)*owner.getHealingAbsorbPercent(),height-4);
    }
}
