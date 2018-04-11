package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
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
        super("Proctor","A long dirt trail leading to the Sorcerer's castle is being protected by the Proctor." +
                " A giant monster with heavy hand and with running speeds hard to dodge. He also has a fire breath that can be very devastating.",
                210,
                new Raid(12, assets),
                assets);
        setId(9);
        level = 3;
        damage = 20;
        create();
    }

    @Override
    public void create() {
        super.create();

        autoAttack = new AutoAttack(this);
        bullCharge = new BullCharge(this);
        fireBreath = new FireBreath(this,8,20f);
        tankSwap = new TankSwap(this);

        loadMechanics(autoAttack, tankSwap, bullCharge, fireBreath);
    }

    @Override
    public void reward() {
        if(player.getLevel() >= getId()) {
            rewardPackage.addNewLevelText();
        }
    }
}
