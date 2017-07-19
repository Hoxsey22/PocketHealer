package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by Hoxsey on 7/18/2017.
 */

public class ChatFrame extends Actor {

    public Image frame;
    public Image portrait;
    public Label name;
    public Label chat;
    public Assets assets;
    public String chatString;
    public Timer animationTimer;

    public ChatFrame(Assets assets)  {
        this.assets = assets;
        //frame = assets.getTexture(assets.mapFrame);
        portrait = new Image(assets.getTexture(assets.dpsIcon));
        createFonts();

        animationTimer = new Timer();
    }

    public void startAnimation()   {



    }

    public void createFonts()   {
        Label.LabelStyle textStyle = new Label.LabelStyle();
        BitmapFont font = assets.getFont(assets.talentTooltipFont);
        textStyle.font = font;

        name = new Label("",textStyle);
        name.scaleBy(1.5f);

        chat = new Label("", textStyle);
        chat.scaleBy(1f);
    }
}
