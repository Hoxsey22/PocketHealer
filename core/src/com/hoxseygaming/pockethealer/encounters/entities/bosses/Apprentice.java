package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Fireball;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Outbreak;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.PoisonPotion;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class Apprentice extends Boss {

    public Outbreak outbreak;
    public PoisonPotion poisonPotion;
    public Fireball fireball;

    public Apprentice(Assets assets) {
        super("Apprentice"," The Sorcerer is cornered with no where to go, but his apprentice steps in so his master can escape. He very " +
                        "skilled with giving his enemies diseases and poisoning, all while casting fireballs.", 15000,new Raid(10,assets), assets);
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
        fireball = new Fireball(this, 1.5f);

        loadMechanics(outbreak, poisonPotion,fireball);
    }

    @Override
    public void reward() {
        rewardPackage.addRewardText(2,3);
    }
}
