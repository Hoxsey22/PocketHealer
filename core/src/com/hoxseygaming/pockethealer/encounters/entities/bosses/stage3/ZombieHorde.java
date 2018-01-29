package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.ZombieAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.ZombieBite;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class ZombieHorde extends Boss {

    private ZombieAttack zombieAttack;
    private ZombieBite zombieBite;

    public ZombieHorde(Assets assets) {
        super("Zombie Horde"," After the cave a horde of zombies are drifting around. The only through is through them. Luckily, " +
                "each zombie defeated is one less to deal with. Be careful for their bites or your team will become apart of their army.",
                240,
                new Raid(12,assets),
                assets);
        setId(13);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 0;

        zombieAttack = new ZombieAttack(this, 2.5f);
        zombieBite = new ZombieBite(this, 9f);
        zombieBite.setNumOfTargets(2);

        loadMechanics(zombieAttack, zombieBite);
    }

    @Override
    public void reward() {
        rewardPackage.addRewardText(1);
    }
}
