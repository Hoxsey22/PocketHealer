package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Cleave;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.TankSwap;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/17/2017.
 */

public class Hogger extends Boss {

    public Hogger(Assets assets) {
        super("Hogger", 3000, new Raid(10,assets), assets);
        setId(6);
        create();
    }

    @Override
    public void create() {
        super.create();

        damage = 20;

        AutoAttack autoAttack = new AutoAttack(this, 2f);
        TankSwap tankSwap = new TankSwap(this, 8f);
        Cleave cleave = new Cleave(this, 3f);

        loadMechanics(autoAttack,cleave);

    }

    @Override
    public void reward() {
    }
}
