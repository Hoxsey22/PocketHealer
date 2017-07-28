package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.timers.CleaveTimer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.timers.TankSwapTimer;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 6/19/2017.
 */
public class Hogger extends Boss {

    public CleaveTimer cleaveTimer;
    public TankSwapTimer tankSwapTimer;
    public float autoAttackTimer;

    public Hogger(Assets assets) {
        super("Hogger", 12000, new Raid(10, assets), assets);

        id = 1;
        damage = 20;
        autoAttackTimer = 2f;
        namePlate = assets.getTexture("hogger_name.png");
        cleaveTimer = new CleaveTimer(this,3.5f);
        tankSwapTimer = new TankSwapTimer(this,getMainTank(),getOffTank(), autoAttackTimer);
    }

    public void start() {
        super.start();
        cleaveTimer.startTimer();
        tankSwapTimer.startTimer();
    }

    public int getId() {
        return id;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
