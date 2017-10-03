package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Sting;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class GiantHornet extends Boss {

    public AutoAttack autoAttack;
    public Sting sting;

    public GiantHornet(Assets assets) {
        super("Giant Hornet", 2500, new Raid(5, assets), assets);
        setId(4);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 5;

        autoAttack = new AutoAttack(this, 0.5f);
        sting = new Sting(this);
        loadMechanics(autoAttack, sting);
    }

    @Override
    public void reward() {
        rewardPoint();
    }
}
