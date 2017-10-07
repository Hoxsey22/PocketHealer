package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Earthquake;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/17/2017.
 */

public class Golem extends Boss {

    public AutoAttack autoAttack;
    public Earthquake earthquake;

    public Golem(Assets assets) {
        super("Golem","The sorcerer is at it again and has summoned a Golem to stop anyone " +
                "from reaching her.\nThe golem does heavy damage to the tank and will throw rocks at " +
                "two raid members dealing heavy damage.", 3000, new Raid(5,assets), assets);
        setId(5);
        create();
    }

    @Override
    public void create() {
        super.create();

        damage = 50;

        autoAttack = new AutoAttack(this, 3f);
        earthquake = new Earthquake(this, 5f);
        earthquake.setAnnounce(true);


        loadMechanics(autoAttack, earthquake);

    }

    @Override
    public void reward() {
    }
}
