package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Consume;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.ZombieAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.ZombieBite;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class ZombieHorde extends Boss {

    public ZombieHorde(Assets assets) {
        super("Zombie Horde"," After the cave a horde of zombies are drifting around. The only through is through them. Luckily, " +
                "each zombie defeated is one less to deal with. Be careful for their bites or your team will become apart of their army.",
                10000,new Raid(10,assets), assets);
        setId(13);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 0;

        ZombieAttack zombieAttack = new ZombieAttack(this, 2.5f);

        ZombieBite zombieBite = new ZombieBite(this, 9f);

        zombieBite.setNumOfTargets(4);

        Consume consume = new Consume(this, 17f);

        loadMechanics(zombieAttack, zombieBite, consume);
    }

    @Override
    public void reward() {

    }
}
