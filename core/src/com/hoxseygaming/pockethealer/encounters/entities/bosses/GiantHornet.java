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
        super("Giant Hornet","A sorcerer has put a spell on a hornet causing it to grow into " +
                "a giant. It needs to be stopped before someone gets hurt.\nThe giant hornet is fast, but does" +
                "small damage to the tanks and will sting a random raid member causing the target to be " +
                "poisoned. ", 2500, new Raid(5, assets), assets);
        setId(4);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 5;

        autoAttack = new AutoAttack(this, 0.5f);
        sting = new Sting(this);
        sting.setAnnounce(true);
        loadMechanics(autoAttack, sting);
    }

    @Override
    public void reward() {
        rewardPoint();
    }
}
