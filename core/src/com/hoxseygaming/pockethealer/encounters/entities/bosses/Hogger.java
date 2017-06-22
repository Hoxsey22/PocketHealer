package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.timers.AutoAttackTimer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.timers.CleaveTimer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.timers.TankSwapTimer;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 6/19/2017.
 */
public class Hogger extends Boss {

    public CleaveTimer cleaveTimer;
    public AutoAttackTimer autoAttackTimer;
    public TankSwapTimer tankSwapTimer;
    public Texture namePlate;

    public Hogger(Raid enemies) {
        super("Hogger", 12000, enemies);

        damage = 30;
        namePlate = new Texture("hogger_name.png");
        cleaveTimer = new CleaveTimer(this,3.5f);
        autoAttackTimer = new AutoAttackTimer(this, 2f);
        tankSwapTimer = new TankSwapTimer(this,getMainTank(),getOffTank());
    }

    public void start() {
        cleaveTimer.startTimer();
        autoAttackTimer.startTimer();
        tankSwapTimer.startTimer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(namePlate, getX()+(getWidth()/2)-((getWidth()/3)/2) ,getY()+2,getWidth()/3,getHeight()-5);
    }
}
