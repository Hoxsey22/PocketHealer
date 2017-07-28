package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

/**
 * Created by Hoxsey on 7/20/2017.
 */

public class LevelInfo extends Group {

    public Image frame;
    public Label bossName;
    public Label description;
    public Image exitButton;
    public boolean isActive;
    public Assets assets;
    public Table table;
    public Image startButton;
    public Boss boss;

    public LevelInfo(Assets assets)  {
        this.assets = assets;

        frame = new Image(assets.getTexture(assets.infoFrame));
        frame.setName("frame");
        frame.setPosition(0, PocketHealer.HEIGHT/2);

        exitButton = new Image(assets.getTexture(assets.exitButton));
        exitButton.setName("exit");
        exitButton.setPosition(frame.getX()+frame.getWidth() - exitButton.getWidth()-5, frame.getY() + frame.getHeight() -45);

        startButton = new Image(assets.getTexture(assets.startButton));
        startButton.setName("start");
        startButton.setPosition(frame.getX()+frame.getWidth()/2 - startButton.getWidth()/2, frame.getY()+10);

        isActive = false;
        table = new Table();
        table.setName("table");
        table.setBounds(frame.getX()+25, frame.getY()+20, frame.getWidth()-50, frame.getHeight()-60);
        table.align(Align.topLeft);
        createFont();

        addActor(frame);
        addActor(exitButton);
        addActor(table);
        addActor(startButton);
    }

    public void setInfo(BossIcon bossIcon)   {
        boss = bossIcon.getBoss();
        bossName.setText(bossIcon.getName());

        description.setText(bossIcon.getDescription());
    }

    public void displayInfo()   {
        isActive = true;
    }

    public void createFont()    {
        Label.LabelStyle textStyle = new Label.LabelStyle();
        BitmapFont font = assets.getFont(assets.talentTooltipFont);
        textStyle.font = font;

        bossName = new Label("test",textStyle);
        bossName.setFontScale(1.5f);
        bossName.setName("boss name");

        description = new Label("test", textStyle);
        description.setName("description");
        description.setFontScale(1f);
        description.setWrap(true);
        description.setWidth(table.getWidth());
        description.setAlignment(Align.topLeft);

        table.row();
        table.add(bossName).left();
        table.row();
        table.add(description).width(table.getWidth());

    }

    public boolean hit(float x, float y)   {
        Actor temp = this.hit(x,y, false);
        if(temp != null) {
            if (temp.getName().equalsIgnoreCase("exit")) {
                isActive = false;
                return false;
            }
            else if(temp.getName().equalsIgnoreCase("start"))   {
                return true;
            }
        }
        return false;
    }

    public Boss getBoss() {
        return boss;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(isActive)    {

            frame.draw(batch, parentAlpha);
            exitButton.draw(batch, parentAlpha);
            startButton.draw(batch, parentAlpha);
            table.draw(batch,parentAlpha);
            /*
            bossName.draw(batch, parentAlpha);
            description.draw(batch, parentAlpha);
            */
        }
    }
}
