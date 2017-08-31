package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BloodBite;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BloodBoil;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BloodBolts;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Cleave;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Fireball;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Outbreak;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.PoisonPotion;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.TankSwap;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class BloodQueen extends Boss {

    public Outbreak outbreak;
    public PoisonPotion poisonPotion;
    public Fireball fireball;

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
        Cleave cleave = new Cleave(this);
        BloodBite bloodBite = new BloodBite(this);
        BloodBoil bloodBoil = new BloodBoil(this);
        BloodBolts bloodBolts = new BloodBolts(this);


        loadMechanics(autoAttack, tankSwap, cleave, bloodBite, bloodBoil, bloodBolts);
    }
}
