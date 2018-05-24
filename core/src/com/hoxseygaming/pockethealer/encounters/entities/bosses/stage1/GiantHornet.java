package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Phase;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Sting;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.PoisonEffect;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class GiantHornet extends Boss {

    AutoAttack autoAttack;
    Sting sting;

    public GiantHornet(Assets assets) {
        super("Giant Hornet","A sorcerer has put a spell on a hornet causing it to grow into " +
                "a giant. It needs to be stopped before someone gets hurt.\nThe giant hornet is fast, but does" +
                "small damage to the tanks and will sting a random raid member causing the target to be " +
                "poisoned. ", 125,
                new Raid(6, assets),
                assets);
        setId(4);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 5;

        autoAttack = new AutoAttack(this, 0.5f);
        sting = new Sting(this);
        sting.setAnnounce(true);
        //sting.setSpeed(8f);
        sting.setNumOfTargets(3);

        phaseManager.addPhase(new Phase(this, 0, autoAttack, sting));
        //loadMechanics(autoAttack, sting);
        loadDebuff(new PoisonEffect(this));
    }

    @Override
    public void reward() {
        if(player.getLevel() >= getId()) {
            rewardPackage.addNewLevelText();
            rewardPackage.addNewTalentText();
            rewardPackage.addNewSpellText();
            rewardPackage.addImage(new Image(assets.getTexture(assets.holyNovaIcon)));
            rewardPackage.addImage(new Image(assets.getTexture(assets.prayerOfMendingIcon)));
        }
    }
}
