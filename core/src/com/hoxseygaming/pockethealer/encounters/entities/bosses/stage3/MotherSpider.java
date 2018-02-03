package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.FeedingTime;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Leap;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Phase;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.TankSwap;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class MotherSpider extends Boss {

    private AutoAttack autoAttack;
    private TankSwap tankSwap;
    private Leap leap;
    private FeedingTime feedingTime;

    public MotherSpider(Assets assets) {
        super("Mother Spider","",
                240,
                new Raid(12,assets),
                assets);
        setId(12);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 20;

        autoAttack = new AutoAttack(this, 2f);
        tankSwap = new TankSwap(this, 12f);
        leap = new Leap(this,50,15f,5);
        feedingTime = new FeedingTime(this,5f, 20f);

        phaseManager.addPhase(new Phase(this, 55f, autoAttack,tankSwap,leap));
        phaseManager.addPhase(new Phase(this, 30f, feedingTime));
    }

    @Override
    public void start() {
        enemies.start(this);
        phaseManager.startPhase();
    }

    @Override
    public void reward() {
        rewardPackage.addRewardText(2,3);
    }

    @Override
    public void update() {

    }

    @Override
    public void stop() {
        phaseManager.cleanPhases();
    }
}
