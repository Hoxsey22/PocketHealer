package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

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
    public Image results;
    public Music endingMusic;   // maybe later
    public Assets assets;
    public boolean won;

    public GameOverFrame(boolean won, Assets assets)  {
        this.assets = assets;
        this.won = won;
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
            finishButton = new Button("finish", new Image(assets.getTexture(assets.finishButton)));
            finishButton.setName("finish");
            finishButton.setPosition(frame.getX() + frame.getWidth()/2 - finishButton.getWidth()/2, frame.getY() - finishButton.getHeight()/2);

            addActor(finishButton);
            results = new Image(assets.getTexture(assets.youWin));
            results.setName("results");
            results.setPosition(frame.getX(),frame.getY());
        }
        else {
            resetButton = new Button("reset", new Image(assets.getTexture(assets.resetButton)));
            resetButton.setName("reset");
            resetButton.setPosition(frame.getX() + frame.getWidth()/2 - resetButton.getWidth(), frame.getY() - resetButton.getHeight()/2);

            leaveButton = new Button("leave", new Image(assets.getTexture(assets.leaveButton)));
            leaveButton.setName("leave");
            leaveButton.setPosition(resetButton.getX() + (float)(resetButton.getWidth()), frame.getY() - leaveButton.getHeight()/2);

            addActor(resetButton);
            addActor(leaveButton);
            results = new Image(assets.getTexture(assets.youWiped));
            results.setName("results");
            results.setPosition(frame.getX(),frame.getY());
        }
        addActor(results);

    }

    public void createFont()    {

    }

    public String hitButton(float x, float y)   {
        Actor hit = hit(x,y,false);

        if(hit !=  null)    {
            return hit.getName();
        }
        return "";
    }

}
