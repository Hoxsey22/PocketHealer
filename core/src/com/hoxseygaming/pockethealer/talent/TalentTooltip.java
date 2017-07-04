package com.hoxseygaming.pockethealer.talent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.PocketHealer;

/**
 * Created by Hoxsey on 7/4/2017.
 */
public class TalentTooltip extends Actor {

    public Texture background;
    private Label.LabelStyle textStyle;
    private BitmapFont font;
    private Label text;
    private Assets assets;
    public boolean isActive;


    public TalentTooltip(Assets assets)  {
        this.assets = assets;
        isActive = false;
        setBounds(0,0, 200,100);
        create();
    }

    public void create()    {
        background = assets.getTexture(assets.blackBar);
        textStyle = new Label.LabelStyle();
        font = assets.getFont(assets.talentTooltip);
        textStyle.font = font;

        text = new Label("",textStyle);
        text.setColor(Color.WHITE);
        text.setWrap(true);
        text.setWidth(this.getWidth());
        text.setAlignment(Align.topLeft);
    }

    public void fire(Talent talent, int x, int y)    {
        text.setText(talent.description);
        isActive = true;
        setLocation((int)talent.getX(),(int)talent.getY());
    }

    public void setLocation(int x, int y)   {
        int newX = x;
        int newY = y;
        if( x+getWidth() > PocketHealer.WIDTH )   {
            System.out.println("HIT");
            newX = PocketHealer.WIDTH - (int)getWidth();
            System.out.println("newX:"+newX+" isActive: "+isActive);
        }
        if(y-getHeight() < 0)    {
            newY = (int)getHeight();
        }
        setPosition(newX, newY-getHeight() -10);
        //setPosition(200,400);
        text.setPosition(getX()+5,getY()+getHeight() - (text.getHeight()/2)- 5);

    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(isActive) {
            batch.draw(background, getX(), getY(), getWidth(), getHeight());
            text.draw(batch, parentAlpha);
        }
    }
}
