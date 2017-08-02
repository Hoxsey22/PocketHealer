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
    public ImageButton finishImageButton;
    public ImageButton resetImageButton;
    public ImageButton leaveImageButton;
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
            finishImageButton = new ImageButton("finish", new Image(assets.getTexture(assets.finishButton)));
            finishImageButton.setName("finish");
            finishImageButton.setPosition(frame.getX() + frame.getWidth()/2 - finishImageButton.getWidth()/2, frame.getY() - finishImageButton.getHeight()/2);

            addActor(finishImageButton);
            results = new Image(assets.getTexture(assets.youWin));
            results.setName("results");
            results.setPosition(frame.getX(),frame.getY());
        }
        else {
            resetImageButton = new ImageButton("reset", new Image(assets.getTexture(assets.resetButton)));
            resetImageButton.setName("reset");
            resetImageButton.setPosition(frame.getX() + frame.getWidth()/2 - resetImageButton.getWidth(), frame.getY() - resetImageButton.getHeight()/2);

            leaveImageButton = new ImageButton("leave", new Image(assets.getTexture(assets.leaveButton)));
            leaveImageButton.setName("leave");
            leaveImageButton.setPosition(resetImageButton.getX() + (float)(resetImageButton.getWidth()), frame.getY() - leaveImageButton.getHeight()/2);

            addActor(resetImageButton);
            addActor(leaveImageButton);
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
