package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BullCharge;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/17/2017.
 */

public class WildBoar extends Boss {

    public BullCharge bullCharge;
    public AutoAttack autoAttack;

    public WildBoar(Assets assets) {
        super("Wild Boar","A wild boar is rampaging through the town and hurting innocent " +
                "people.\n The wild boar will do moderate damage to the tank and will charge a random " +
                "raid member every once in a while.",
                120,
                new Raid(6,assets),
                assets);
        setId(2);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 10;
        bullCharge = new BullCharge(this, 5f);
        bullCharge.setAnnounce(true);
        autoAttack = new AutoAttack(this, 1f);

        loadMechanics(autoAttack, bullCharge);
    }

    @Override
    public void reward() {
        rewardPackage.addRewardText(1,2,3);
        rewardPackage.setSpellImage(assets.getTexture(assets.flashIcon));
    }
}