package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 7/20/2017.
 */

public class BossIcon extends Actor {

    public Assets assets;
    public Image image;
    public Image glow;
    public Image defeated;
    public Boss boss;
    public String description;
    public boolean isSelected;

    public BossIcon(Assets assets)   {
        setName("Boss Location");
        this.assets = assets;
        image = new Image(assets.getTexture(assets.bossLocation));
        glow = new Image(assets.getTexture(assets.selectedLevel));
        defeated = new Image(assets.getTexture(assets.defeatedBossIcon));
        isSelected = false;
    }

    public BossIcon(Assets assets, Boss boss)   {
        this.assets = assets;
        this.boss = boss;

        setName(boss.name);
        description = boss.getDescription();

        image = new Image(assets.getTexture(assets.bossLocation));
        image.setBounds(assets.bossIconPosition.get(boss.id-2).x,assets.bossIconPosition.get(boss.id-2).y, 50, 50);

        glow = new Image(assets.getTexture(assets.selectedLevel));
        glow.setBounds(image.getX(),image.getY(),image.getWidth(), image.getHeight());

        defeated = new Image(assets.getTexture(assets.defeatedBossIcon));
        defeated.setBounds(image.getX(),image.getY(),image.getWidth(), image.getHeight());

        setBounds(image.getX(), image.getY(), image.getWidth(), image.getHeight());

        System.out.println(this.getDebug());
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        image.setPosition(x,y);
        glow.setPosition(x,y);
        defeated.setPosition(x,y);
    }

    public void select()    {
        isSelected = true;
    }

    public void unselect() {
        isSelected = false;
    }

    public String getDescription()  {
        return description;
    }

    public Boss getBoss() {
        return boss;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(boss.isDefeated())    {
            defeated.draw(batch, parentAlpha);
            return;
        }

        if(isSelected)
            glow.draw(batch, parentAlpha);
        else
            image.draw(batch, parentAlpha);

    }
}
