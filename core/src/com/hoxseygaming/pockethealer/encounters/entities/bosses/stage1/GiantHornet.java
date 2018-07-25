package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Strings;
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

    private AutoAttack autoAttack;
    private Sting sting;

    public GiantHornet(Assets assets) {
        super("Giant Hornet",
                Strings.GIANT_HORNET_DESCRIPTION,
                125,
                new Raid(6, assets),
                assets);
        setId(3);
        create();
    }

    @Override
    public void create() {
        super.create();
        setDamage(5);

        autoAttack = new AutoAttack(this, 0.5f);
        sting = new Sting(this);
        sting.setAnnounce();
        sting.setNumOfTargets(3);

        getPhaseManager().addPhase(new Phase(this, 0, autoAttack, sting));
        loadDebuff(new PoisonEffect(this));
    }

    @Override
    public void reward() {
        if(getPlayer().getLevel() >= getId()) {
            getRewardPackage().addNewLevelText();
            getRewardPackage().addNewTalentText();
            getRewardPackage().addNewSpellText();
            getRewardPackage().addImage(new Image(getAssets().getTexture(getAssets().holyNovaIcon)));
            getRewardPackage().addImage(new Image(getAssets().getTexture(getAssets().prayerOfMendingIcon)));
        }
    }

}
