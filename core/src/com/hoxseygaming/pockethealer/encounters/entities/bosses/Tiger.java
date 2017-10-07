package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Pounce;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/17/2017.
 */

public class Tiger extends Boss {

    public Pounce pounce;
    public AutoAttack autoAttack;

    public Tiger(Assets assets) {
        super("Tiger","A tiger is eating all the live stock and harming some people that " +
                "try to stop him.\nThe tiger will do moderate damage to the tank and will pounce on raid" +
                " members doing moderate damage and leaving behind a bleed.", 2500, new Raid(5,assets), assets);
        setId(3);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 15;
        pounce = new Pounce(this, 2);
        pounce.setSpeed(10f);
        pounce.setAnnounce(true);
        autoAttack = new AutoAttack(this, 1f);

        loadMechanics(autoAttack, pounce);
    }

    @Override
    public void reward() {

    }
}
