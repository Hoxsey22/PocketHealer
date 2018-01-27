package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Fireball;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.SeedOfCorruption;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class Apprentice extends Boss {

    public SeedOfCorruption seedOfCorruption;
    public Fireball fireball;

    public Apprentice(Assets assets) {
        super("Apprentice"," The Sorcerer is cornered with no where to go, but his apprentice steps in so his master can escape. He very " +
                        "skilled with giving his enemies diseases and poisoning, all while casting fireballs.",
                240,
                new Raid(12,assets),
                assets);
        setId(10);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 0;


        fireball = new Fireball(this, 2f);
        fireball.setDamage(10);
        seedOfCorruption = new SeedOfCorruption(this);

        loadMechanics(fireball, seedOfCorruption);
    }

    @Override
    public void reward() {
        rewardPackage.addRewardText(2,3);
    }
}
