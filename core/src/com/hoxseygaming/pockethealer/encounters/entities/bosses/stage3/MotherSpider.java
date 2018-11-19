package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Strings;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.FeedingTime;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Leap;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Phase;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.TankSwap;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.VenomEffect;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.WebEffect;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class MotherSpider extends Boss {

    private AutoAttack autoAttack;
    private TankSwap tankSwap;
    private Leap leap;
    private FeedingTime feedingTime;

    public MotherSpider(Assets assets) {
        super("Mother Spider",
                Strings.MOTHER_SPIDER_DESCRIPTION,
                255,
                new Raid(12,assets),
                assets);
        setId(12);
        create();
    }

    @Override
    public void create() {
        super.create();
        setDamage(20);

        autoAttack = new AutoAttack(this, 2f);
        tankSwap = new TankSwap(this, 12f);
        leap = new Leap(this,getDamage()*2,16f,8);
        feedingTime = new FeedingTime(this,6f, 19f);

        getPhaseManager().addPhase(new Phase(this, 55f, autoAttack,tankSwap,leap));
        getPhaseManager().addPhase(new Phase(this, 30f, feedingTime));
        loadDebuff(new VenomEffect(this), new WebEffect(this));
    }

    @Override
    public void reward() {
        if(getPlayer().getLevel() >= getId()) {
            getRewardPackage().addNewLevelText();
        }
    }

    public AutoAttack getAutoAttack() {
        return autoAttack;
    }

    public void setAutoAttack(AutoAttack autoAttack) {
        this.autoAttack = autoAttack;
    }

    public TankSwap getTankSwap() {
        return tankSwap;
    }

    public void setTankSwap(TankSwap tankSwap) {
        this.tankSwap = tankSwap;
    }

    public Leap getLeap() {
        return leap;
    }

    public void setLeap(Leap leap) {
        this.leap = leap;
    }

    public FeedingTime getFeedingTime() {
        return feedingTime;
    }

    public void setFeedingTime(FeedingTime feedingTime) {
        this.feedingTime = feedingTime;
    }
}
