package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BullCharge;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.FireBreath;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.TankSwap;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 7/20/2017.
 */

public class Proctor extends Boss {

    public AutoAttack autoAttack;
    public BullCharge bullCharge;
    public FireBreath fireBreath;
    public TankSwap tankSwap;

    public Proctor(Assets assets) {
        super("Proctor", 12000, new Raid(10, assets), assets);
        id = 3;
        level = 3;
        damage = 40;
        create();
    }

    @Override
    public void create() {
        super.create();

        autoAttack = new AutoAttack(this);
        mechanics.add(autoAttack);

        bullCharge = new BullCharge(this);
        mechanics.add(bullCharge);

        fireBreath = new FireBreath(this);
        mechanics.add(fireBreath);

        tankSwap = new TankSwap(this);
        mechanics.add(tankSwap);
    }

    @Override
    public void start() {
        super.start();
        autoAttack.start();
        bullCharge.start();
        fireBreath.start();
        tankSwap.start();
    }
}
