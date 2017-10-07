package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Earthquake;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/17/2017.
 */

public class TestBoss extends Boss {

    public AutoAttack autoAttack;
    public Earthquake earthquake;

    public TestBoss(Assets assets) {
        super("TEST BOSS","", 3000000, new Raid(15,assets), assets);
        setId(16);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 0;
    }

    @Override
    public void reward() {

    }
}
