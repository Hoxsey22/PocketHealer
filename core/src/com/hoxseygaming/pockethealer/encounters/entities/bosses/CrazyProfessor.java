package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Fireball;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Outbreak;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.PoisonPotion;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class CrazyProfessor extends Boss {

    public Outbreak outbreak;
    public PoisonPotion poisonPotion;
    public Fireball fireball;

    public CrazyProfessor(Assets assets) {
        super("Apprentice","", 15000,new Raid(10,assets), assets);
        setId(14);
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
