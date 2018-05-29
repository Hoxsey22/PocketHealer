package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3;

import com.hoxseygaming.pockethealer.Assets;
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
        super("Mother Spider","Something is still wrong and a huge dark force is coming from the castle. Though the creepy cave, a giant spider, the mother of all spider is blocking the way to the castle. " +
                        "She does a ferocious leap and injects them with venom. She loves to eat her victims, but only if they are in " +
                        "her web. Be careful!",
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
            getRewardPackage().addNewTalentText();
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
