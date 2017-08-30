package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Consume;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.PoisonBite;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.WebTrap;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class MotherSpider extends Boss {

    public MotherSpider(Assets assets) {
        super("Apprentice", 20000,new Raid(15,assets), assets);
        setId(12);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 20;

        AutoAttack autoAttack= new AutoAttack(this, 2f);

        PoisonBite poisonBite = new PoisonBite(this, 8f);
        poisonBite.setNumOfTargets(4);

        WebTrap webTrap = new WebTrap(this, 16f);
        webTrap.setNumOfTarget(6);

        Consume consume = new Consume(this, 15f);

        loadMechanics(autoAttack, poisonBite, webTrap, consume);
    }
}
