package com.hoxseygaming.pockethealer.encounters.entities.raid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.hoxseygaming.pockethealer.Assets;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class HealthBar {

    private float x;
    private float y;
    private float width;
    private float height;
    private Assets assets;
    private Texture healthBar;
    private RaidMember owner;


    public HealthBar(RaidMember owner, int x, int y, int width, int height) {
        this.owner = owner;
        assets = owner.getAssets();
        this.x = x + 9;
        this.y = y + 7;
        this.width = width - 19;
        this.height = height / 4;

        healthBar = getAssets().getTexture(getAssets().hpManaBar);

    }

    public HealthBar(RaidMember owner) {
        this.owner = owner;
        assets = owner.getAssets();

        x = owner.getX() + 10;
        y = owner.getY() + 5;
        width = owner.getWidth() - 20;
        height = owner.getHeight() / 4f;

        healthBar = getAssets().getTexture(getAssets().hpManaBar);

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public Texture getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(Texture healthBar) {
        this.healthBar = healthBar;
    }

    public RaidMember getOwner() {
        return owner;
    }

    public void setOwner(RaidMember owner) {
        this.owner = owner;
    }

    public void draw(Batch batch, float alpha)  {
        batch.draw(healthBar, x,y,width,height);
        if(owner.getHealthPercent() < 0.3)
            batch.draw(getAssets().getTexture(getAssets().redFill), x+3,y+2,(width-6)*owner.getHealthPercent(),height-4);
        else if(owner.getHealthPercent() < 0.7)   {
            batch.draw(getAssets().getTexture(getAssets().yellowFill), x+3,y+2,(width-6)*owner.getHealthPercent(),height-4);
        }
        else    {
            batch.draw(getAssets().getTexture(getAssets().greenFill), x+3,y+2,(width-6)*owner.getHealthPercent(),height-4);
        }
        batch.draw(getAssets().getTexture(getAssets().manaFill), x+3,y+2,(width-6)*owner.getShieldPercent(),height-4);
        batch.draw(getAssets().getTexture(getAssets().purpleFill), x+3,y+2,(width-6)*owner.getHealingAbsorbPercent(),height-4);
    }
}
