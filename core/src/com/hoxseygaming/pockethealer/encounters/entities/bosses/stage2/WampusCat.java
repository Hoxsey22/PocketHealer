package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Phase;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Pounce;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 7/20/2017.
 */

public class WampusCat extends Boss {

    Pounce pounce;
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


        autoAttack = new AutoAttack(this);
        pounce = new Pounce(this);
        /*mechanics.add(humanForm);
        mechanics.add(catForm);
        mechanics.add(autoAttack);
        */

        phaseManager.addPhase(new Phase(this, name+" is in her Cat Form!",30f, pounce, autoAttack));
        phaseManager.addPhase(new Phase(this,name+"is in her Human Form!", 30f, autoAttack));
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
