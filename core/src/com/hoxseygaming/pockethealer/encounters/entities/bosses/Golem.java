package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.RockThrow;

/**
 * Created by Hoxsey on 8/17/2017.
 */

public class Golem extends Boss {

    public AutoAttack autoAttack;
    public RockThrow rockThrow;

    public Golem(Assets assets) {
        super("Golem", 3000, assets);
        create();
    }

    @Override
    public void create() {
        super.create();

        damage = 50;

        autoAttack = new AutoAttack(this, 3f);
        rockThrow = new RockThrow(this, 5f);

        mechanics.add(autoAttack);
        mechanics.add(rockThrow);

    }

    @Override
    public void start() {
        super.start();
        autoAttack.start();
        rockThrow.start();
    }
}
