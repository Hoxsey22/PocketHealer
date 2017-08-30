package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.FireBreath;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Fireball;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Outbreak;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.PoisonPotion;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class Sorcerer extends Boss {

    public Outbreak outbreak;
    public PoisonPotion poisonPotion;
    public Fireball fireball;
    public FireBreath fireBreath;

    public Sorcerer(Assets assets) {
        super("Sorcerer", 20000,new Raid(15,assets), assets);
        setId(10);
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
        fireBreath = new FireBreath(this);
        loadMechanics(outbreak, poisonPotion,fireball,fireBreath);
    }
}
