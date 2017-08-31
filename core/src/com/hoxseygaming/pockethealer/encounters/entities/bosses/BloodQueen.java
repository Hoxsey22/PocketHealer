package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BloodBite;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BloodBoil;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BloodBolts;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Cleave;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.TankSwap;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class BloodQueen extends Boss {

    public BloodQueen(Assets assets) {
        super("Blood Queen", 20000,new Raid(15,assets), assets);
        setId(14);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 3;

        AutoAttack autoAttack = new AutoAttack(this, 0.5f);

        TankSwap tankSwap = new TankSwap(this, 10f);

        Cleave cleave = new Cleave(this, 3f);
        cleave.setNumOfTargets(4);

        BloodBite bloodBite = new BloodBite(this, 15f);

        BloodBoil bloodBoil = new BloodBoil(this, 12f);
        bloodBoil.setNumOfTargets(4);

        BloodBolts bloodBolts = new BloodBolts(this, 45f);


        loadMechanics(autoAttack, tankSwap, cleave, bloodBite, bloodBoil, bloodBolts);
    }
}
