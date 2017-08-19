package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BullCharge;

/**
 * Created by Hoxsey on 8/17/2017.
 */

public class WildBoar extends Boss {

    public BullCharge bullCharge;
    public AutoAttack autoAttack;

    public WildBoar(Assets assets) {
        super("Wild Boar", 2200, assets);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 10;
        bullCharge = new BullCharge(this, 5f);
        autoAttack = new AutoAttack(this, 1f);

        mechanics.add(bullCharge);
        mechanics.add(autoAttack);
    }

    @Override
    public void start() {
        super.start();
        bullCharge.start();
        autoAttack.start();
    }
}
