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

    public FloatingText(int id, RaidMember raidMember, int damage, int type, Assets assets)   {
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

        floatingText = new Text("", assets);
        floatingText.setFont(24, true);
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

    public void draw(Batch batch, float alpha)  {
        if(isAnimating){
            floatingText.draw(batch,alpha);
        }
    }

    public void dispose()   {
        floatingText.dispose();
    }
}
