package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Phase;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Pounce;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.BleedEffect;

/**
 * Created by Hoxsey on 8/17/2017.
 */

public class Tiger extends Boss {

    Pounce pounce;
    AutoAttack autoAttack;

    public Tiger(Assets assets) {
        super("Tiger","A tiger is eating all the live stock and harming some people that " +
                "try to stop him.\nThe tiger will do moderate damage to the tank and will pounce on raid" +
                " members doing moderate damage and leaving behind a bleed.",
                150,
                new Raid(6,assets),
                assets);
        setId(3);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 15;
        pounce = new Pounce(this, 2);
        pounce.setNumOfTargets(2);
        pounce.setSpeed(15f);
        pounce.setAnnounce(true);
        autoAttack = new AutoAttack(this, 1f);

        //loadMechanics(autoAttack, pounce);
        phaseManager.addPhase(new Phase(this, 0,autoAttack, pounce));

        loadDebuff(new BleedEffect(this));
    }

    @Override
    public void reward() {
        if(player.getLevel() >= getId()) {
            rewardPackage.addNewLevelText();
            rewardPackage.addNewSpellText();
            rewardPackage.addImage(new Image(assets.getTexture(assets.dispelIcon)));
        }
    }
}
