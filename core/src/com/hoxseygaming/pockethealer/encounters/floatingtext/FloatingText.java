package com.hoxseygaming.pockethealer.encounters.floatingtext;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;


/**
 * Created by Hoxsey on 6/28/2017.
 */
public class FloatingText {

    public final static int DAMAGE = 0;
    public final static int HEAL = 1;
    public final static int SHIELD = 2;

    private int id;
    private RaidMember owner;
    private String damage;
    private int type;


    private BitmapFont font;
    private Label.LabelStyle style;
    private Label floatingText;
    private Timer timer;
    private int duration;
    public boolean isAnimating;
    public Assets assets;

    public FloatingText(Assets assets, int id, RaidMember raidMember, int damage, int type)   {
        this.assets = assets;
        this.id = id;
        owner = raidMember;
        this.damage = ""+damage;
        this.type = type;

        duration = 10;
        isAnimating = false;
        create();
    }

    public void create()    {
        style = new Label.LabelStyle();
        font = assets.getFont(assets.floatingFnt);
        style.font = font;
        style.fontColor = Color.WHITE;

        floatingText = new Label("",style);
        floatingText.setScale(0.5f);
        addText();

        floatingText.setAlignment(Align.center);
    }

    public void startAnimation() {

        timer = new Timer();
        isAnimating = true;

        timer.scheduleTask(new Timer.Task() {
            int currentTimer = 0;
            @Override
            public void run() {
                currentTimer++;
                floatingText.setPosition(floatingText.getX(), floatingText.getY()+(currentTimer/2));
                if(currentTimer == duration)    {
                    isAnimating = false;
                    timer.stop();
                    timer.clear();
                }

            }
        },0.1f, 0.1f, duration);

    }

    public void addText()   {
        floatingText.setText(damage);
        switch (type)   {
            case DAMAGE:
                floatingText.setColor(Color.RED);
                floatingText.setPosition(owner.getX()+ owner.getWidth()/2 - 5, owner.getY()+20);
                break;
            case HEAL:
                floatingText.setColor(Color.GREEN);
                floatingText.setPosition(owner.getX()+ owner.getWidth()/2 + 10, owner.getY()+20);
                break;
            case SHIELD:
                floatingText.setColor(Color.YELLOW);
                floatingText.setPosition(owner.getX()+ owner.getWidth()/2 - 15, owner.getY()+20);
        }

    }

    public int getId() {
        return id;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public Label getFloatingText() {
        return floatingText;
    }

    public void setFloatingText(Label floatingText) {
        this.floatingText = floatingText;
    }

    public void draw(Batch batch, float alpha)  {
        if(isAnimating){
            floatingText.draw(batch,alpha);
        }
    }
}
