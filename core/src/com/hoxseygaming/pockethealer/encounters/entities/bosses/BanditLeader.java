package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BackStab;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class BanditLeader extends Boss {

    public AutoAttack autoAttack;
    public BackStab backStab;

    public BanditLeader(Assets assets) {
        super("Bandit Leader", 3000,new Raid(5, assets), assets);
        setId(5);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 20;

        autoAttack = new AutoAttack(this, 1f);
        backStab = new BackStab(this);
        loadMechanics(autoAttack, backStab);
    }
}
