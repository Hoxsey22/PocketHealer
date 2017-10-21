package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Massacre;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.PoisonSpit;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Swipe;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 10/11/2017.
 */

public class Hydra extends Boss {

    private Massacre massacre;
    private PoisonSpit poisonSpit;
    private Swipe swipe;


    public Hydra(Assets assets) {
        super("Ion, The Hydra", "A monstrous hydra is blocking the way to the top of castle. " +
                "This hydra has been altered for mass destruction. \nDon't let the raid drop below 10% or they die.", 30000,new Raid(15, assets), assets);
        setId(15);
        create();
    }

    @Override
    public void create() {
        super.create();

        setDamage(20);

        massacre = new Massacre(this, 32f);

        poisonSpit = new PoisonSpit(this, 11f);
        poisonSpit.setNumOfTargets(5);

        swipe = new Swipe(this, 2f);

        loadMechanics(massacre, poisonSpit, swipe);



    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void reward() {

    }
}