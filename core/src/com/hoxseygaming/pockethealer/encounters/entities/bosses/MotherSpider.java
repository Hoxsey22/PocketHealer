package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Consume;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.PoisonBite;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.TankSwap;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.WebTrap;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class MotherSpider extends Boss {

    private AutoAttack autoAttack;
    private PoisonBite poisonBite;
    private WebTrap webTrap;
    private Consume consume;
    private TankSwap tankSwap;

    public MotherSpider(Assets assets) {
        super("Mother Spider"," The Sorcerer is now defeated, but his powers were coming from another source and it" +
                " has to be stopped and through the cave is getting one step closer. In the middle of the cave is " +
                "The Mother Spider and she is upset about her children being stepped on. She has a poisonous bite and " +
                "will web her victims before she eats them.", 20000,new Raid(10,assets), assets);
        setId(12);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 20;

        autoAttack= new AutoAttack(this, 2f);

        tankSwap = new TankSwap(this, 8f);

        poisonBite = new PoisonBite(this, 8f);
        poisonBite.setNumOfTargets(4);

        webTrap = new WebTrap(this, 16f);
        webTrap.setNumOfTarget(4);

        consume = new Consume(this, 15f);

        loadMechanics(autoAttack, tankSwap, poisonBite, webTrap, consume);
    }

    @Override
    public void reward() {
        rewardPackage = new RewardPackage("New Talent Point\nLevel up!");
    }

    @Override
    public void update() {

    }
}
