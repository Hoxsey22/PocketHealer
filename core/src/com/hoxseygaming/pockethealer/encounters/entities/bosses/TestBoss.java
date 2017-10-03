package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.RockThrow;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/17/2017.
 */

public class TestBoss extends Boss {

    public AutoAttack autoAttack;
    public RockThrow rockThrow;

    public TestBoss(Assets assets) {
        super("TEST BOSS","", 3000000, new Raid(5,assets), assets);
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
