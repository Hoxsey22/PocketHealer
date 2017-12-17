package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Fireball;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Outbreak;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.PoisonPotion;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class DeathDragon extends Boss {

    public Outbreak outbreak;
    public PoisonPotion poisonPotion;
    public Fireball fireball;

    public DeathDragon(Assets assets) {
        super("Death Dragon","", 240,new Raid(12,assets), assets);
        setId(16);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 0;

        outbreak = new Outbreak(this, 10f);
        poisonPotion = new PoisonPotion(this, 9f);
        poisonPotion.setNumOfTargets(3);
        fireball = new Fireball(this, 3f);
        loadMechanics(outbreak, poisonPotion,fireball);
    }

    @Override
    public void reward() {

    }
}
