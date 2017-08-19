package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Pounce;

/**
 * Created by Hoxsey on 8/17/2017.
 */

public class Tiger extends Boss {

    public Pounce pounce;
    public AutoAttack autoAttack;

    public Tiger(Assets assets) {
        super("Tiger", 2500, assets);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 30;
        pounce = new Pounce(this, 2);
        mechanics.add(pounce);
    }

    @Override
    public void start() {
        super.start();
        autoAttack.start();
        pounce.start();
    }
}
