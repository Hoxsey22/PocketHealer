package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.CatForm;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.HumanForm;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 7/20/2017.
 */

public class WampusCat extends Boss {

    public HumanForm humanForm;
    public CatForm catForm;
    public AutoAttack autoAttack;

    public WampusCat(Assets assets) {
        super("Wampus Cat", 6000 , new Raid(10, assets), assets);

        id = 2;
        damage = 20;
        level = 4;
        namePlate = assets.getTexture(assets.wampusCatName);

    }

    @Override
    public void create()    {
        humanForm = new HumanForm(this);
        catForm = new CatForm(this);


        //linking the mechs together
        humanForm.setLinkedMechanic(catForm);
        catForm.setLinkedMechanic(humanForm);

        autoAttack = new AutoAttack(this);

        mechanics.add(humanForm);
        mechanics.add(catForm);
        mechanics.add(autoAttack);
    }

    @Override
    public void start() {
        super.start();
        create();
        catForm.start();
        autoAttack.start();
    }

    @Override
    public void stop() {
        super.stop();
    }
}