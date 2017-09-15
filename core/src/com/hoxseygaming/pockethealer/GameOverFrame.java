package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
    public ImageButton finishImageButton;
    public ImageButton resetImageButton;
    public ImageButton leaveImageButton;
    public Text text;
    public Table table;
    public Image results;
    public Music endingMusic;   // maybe later
    public Boss boss;
    public Assets assets;
    public boolean won;

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

        if(won) {
            finishImageButton = new ImageButton("finish", new Image(assets.getTexture(assets.finishButton)));
            finishImageButton.setName("finish");
            finishImageButton.setPosition(frame.getX() + frame.getWidth()/2 - finishImageButton.getWidth()/2, frame.getY() - finishImageButton.getHeight()/2);

            addActor(finishImageButton);
            createText(won);
        }
        else {
            resetImageButton = new ImageButton("reset", new Image(assets.getTexture(assets.resetButton)));
            resetImageButton.setName("reset");
            resetImageButton.setPosition(frame.getX() + frame.getWidth()/2 - resetImageButton.getWidth(), frame.getY() - resetImageButton.getHeight()/2);

            leaveImageButton = new ImageButton("leave", new Image(assets.getTexture(assets.leaveButton)));
            leaveImageButton.setName("leave");
            leaveImageButton.setPosition(resetImageButton.getX() + resetImageButton.getWidth(), frame.getY() - leaveImageButton.getHeight()/2);

            addActor(resetImageButton);
            addActor(leaveImageButton);
            createText(won);
        }
        //addActor(results);

    }

    public void createText(boolean won)    {
        table = new Table();
        table.setBounds(frame.getX(),frame.getY(), frame.getWidth(), frame.getHeight());

        text = new Text("",24, Color.WHITE, true, assets);
        text.setWrap(true);
        text.setAlignment(Align.top);

        if(won)    {
            text.setText("Congratulations!" +
                    "\n"+boss.getName()+" is defeated!\n" +boss.rewardDescription);
        }
        else    {
            text.setText("You have wiped!" +
                    "\n"+boss.getName()+" has defeated you!");
        }

        //table.top();
        table.add(text.getLabel());
        addActor(table);
    }

    public String hitButton(float x, float y)   {
        Actor hit = hit(x,y,false);

        if(hit !=  null)    {
            return hit.getName();
        }
        return "";
    }

}
