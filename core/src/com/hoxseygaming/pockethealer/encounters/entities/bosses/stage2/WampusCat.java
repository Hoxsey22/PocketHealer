package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
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
        super("Wampus Cat","The Sorcerer has given an evil woman the power of the Wampus Cat. " +
                "Her Cat form is fierce and not to be taken lightly, but her human form is nothing to worry about.",
                210 ,
                new Raid(9, assets),
                assets);

        setId(8);
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
    public void update() {

    }

    @Override
    public void stop() {
        super.stop();
        catForm.pounce.stopBleeds();
        if(catForm !=null)
            catForm.stop();
        if (humanForm !=null)
            humanForm.stop();
    }

    @Override
    public void reward() {
        if(player.getLevel() >= getId()) {
            rewardPackage.addNewLevelText();
            rewardPackage.addNewTalentText();
            rewardPackage.addNewSpellText();
            rewardPackage.addImage(new Image(assets.getTexture(assets.penanceIcon)));
        }
    }
}
