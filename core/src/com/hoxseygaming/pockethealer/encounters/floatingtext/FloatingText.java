package com.hoxseygaming.pockethealer.encounters.floatingtext;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Text;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;


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
    private Text floatingText;
    private Timer timer;
    private int duration;
    public boolean isAnimating;
    private Assets assets;
    private int fontSize;
    private Color color;
    private float x,y;

    public FloatingText(int id, RaidMember raidMember, int damage, int type, boolean isCritical, Assets assets)   {
        this.assets = assets;
        this.id = id;
        owner = raidMember;
        this.damage = ""+damage;
        this.type = type;

        if(isCritical)    {
            fontSize = 32;
        }
        else    {
            fontSize = 24;
        }

        switch (type)   {
            case DAMAGE:
                color = Color.RED;
                x = owner.getX()+ owner.getWidth()/2 - 5;
                y = owner.getY()+20;
                break;
            case HEAL:
                color = Color.GREEN;
                x = owner.getX()+ owner.getWidth()/2 + 10;
                y = owner.getY()+20;
                break;
            case SHIELD:
                color = Color.YELLOW;
                x = owner.getX()+ owner.getWidth()/2 - 15;
                y = owner.getY()+20;
        }

        duration = 10;
        isAnimating = false;
        create();
    }

    public void create()    {
        floatingText = new Text("" + damage, fontSize, color, true, assets);
        floatingText.setAlignment(Align.center);
        setPosition(x,y);
    }

    public void startAnimation() {

        timer = new Timer();
        isAnimating = true;

        timer.scheduleTask(new Timer.Task() {
            int currentTimer = 0;
            @Override
            public void run() {
                currentTimer++;
                setPosition(floatingText.getX(), floatingText.getY()+(currentTimer/2));
                floatingText.setAlpha(((float)(duration-currentTimer)/(float)duration));
                if(currentTimer == duration)    {
                    isAnimating = false;
                    timer.stop();
                    timer.clear();
                    floatingText.remove();
                }

            }
        },0.1f, 0.1f, duration);

    }

    public void setPosition(float x, float y)   {
        this.x = x;
        this.y = y;
        floatingText.setPosition(this.x, this.y);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
        floatingText.setText(this.damage);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Text getFloatingText() {
        return floatingText;
    }

    public RaidMember getOwner() {
        return owner;
    }

    public void setOwner(RaidMember owner) {
        this.owner = owner;
    }

    public void setFloatingText(Text floatingText) {
        this.floatingText = floatingText;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public void setAnimating(boolean animating) {
        isAnimating = animating;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

    public void draw(Batch batch, float alpha)  {
        if(isAnimating){
            floatingText.draw(batch,alpha);
        }
    }

    public void dispose()   {
    }
}
