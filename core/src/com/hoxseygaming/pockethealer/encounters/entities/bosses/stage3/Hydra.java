package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Massacre;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Swipe;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 10/11/2017.
 */


/*Need to add another mech*/
public class Hydra extends Boss {

    private Massacre massacre;
    private Swipe swipe;


    public Hydra(Assets assets) {
        super("Ion, The Hydra", "A monstrous hydra is blocking the way to the top of castle. " +
                "This hydra has been altered for mass destruction. \nDon't let the raid drop below 10% or they die.",
                240,
                new Raid(15, assets),
                assets);
        setId(15);
        create();
    }

    @Override
    public void create() {
        super.create();

        setDamage(20);

        massacre = new Massacre(this, 32f);

        swipe = new Swipe(this, 2f);

        loadMechanics(massacre, swipe);



    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void reward() {
        rewardPackage.addNewLevelText();
    }
}