package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 7/27/2017.
 */

public class GameOverFrame extends Group {

    public Image disableBG;
    public Image frame;
    public Label boxLabel;  //maybe for a later time
    public ArrayList<Label> chat;   // maybe for a later time
    public Button finishButton;
    public Button resetButton;
    public Button leaveButton;
    public Text title;
    public Text body;
    public Table table;
    public Image results;
    public Music endingMusic;   // maybe later
    public Boss boss;
    public Assets assets;
    public boolean won;
    public int page;

    public GameOverFrame(boolean won, Boss boss, Assets assets)  {
        this.assets = assets;
        this.won = won;
        this.boss = boss;
        create();
    }

    public void create()    {
        disableBG = new Image(assets.getTexture(assets.disableBG));
        disableBG.setName("disable bg");
        disableBG.setBounds(0,0,PocketHealer.WIDTH, PocketHealer.HEIGHT);

        frame = new Image(assets.getTexture(assets.endGameFrame));
        frame.setName("frame");
        frame.setPosition(PocketHealer.WIDTH/2 - frame.getWidth()/2, PocketHealer.HEIGHT/2 - frame.getWidth()/2);

        addActor(disableBG);
        addActor(frame);
        createText();

    }

    public void createText()    {
        table = new Table();
        table.setName("table");
        table.setBounds(frame.getX(),frame.getY(), frame.getWidth()-5, frame.getHeight()-10);

        title = new Text("",32, Color.WHITE, true, assets);
        title.setName("title");
        title.setWrap(true);
        title.setAlignment(Align.top);

        body = new Text("", 24, Color.WHITE, false, assets);
        body.setName("body");
        body.setWrap(true);
        body.setAlignment(Align.center);

        addActor(table);
    }

    public void showHealingStats()  {
        title.setText("Healing");

        table.add(title.getLabel()).width(table.getWidth());
        table.row();

        body.setText("Effective Healing: "+boss.getEnemies().healingTracker.getEffectiveHealingPercent()+"%\n"+
                "Overhealing: "+boss.getEnemies().healingTracker.getOverHealingPercent()+"%");

        table.add(body.getLabel()).width(table.getWidth());
    }

    public void showReward()    {
        table.clear();

        title.setText("Reward");
        table.add(title.getLabel()).width(table.getWidth());
        table.row();
        if(boss.rewardPackage.getSpellImage() != null) {
            boss.rewardPackage.getSpellImage().setAlign(Align.center);
            table.add(boss.rewardPackage.getSpellImage());
            table.row();
        }
        body.setText(boss.rewardPackage.getReward());
        table.add(body.getLabel()).width(table.getWidth());

        finishButton = new Button("FINISH", false, assets);
        finishButton.setPosition(frame.getX() + frame.getWidth()/2 - finishButton.getWidth()/2, frame.getY() - finishButton.getHeight()/2);
        addActor(finishButton);
    }

    public void showLose()  {
        title.setText("You have wiped!");
        table.add(title.getLabel()).width(table.getWidth());

        resetButton = new Button("RESET", false,assets);
        resetButton.setPosition(frame.getX() + frame.getWidth()/2 - resetButton.getWidth(), frame.getY() - resetButton.getHeight()/2);

        leaveButton = new Button("QUIT", false, assets);
        leaveButton.setPosition(resetButton.getX() + resetButton.getWidth(), frame.getY() - leaveButton.getHeight()/2);
        leaveButton.setDebug(true);

        addActor(resetButton);
        addActor(leaveButton);
    }

    public int hitButton(float x, float y)   {


        if(leaveButton != null && leaveButton.pressed(x,y))    {
            return 0;
        }
        if(finishButton != null && finishButton.pressed(x,y))    {
            return 1;
        }
        if(resetButton != null && resetButton.pressed(x,y))    {
            return 2;
        }
        return -1;
    }
}
