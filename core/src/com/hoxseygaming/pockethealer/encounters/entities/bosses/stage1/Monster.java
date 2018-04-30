package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Bite;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Phase;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/17/2017.
 */

public class Monster extends Boss {

    public Bite bite;
    public AutoAttack autoAttack;

    public Monster(Assets assets) {
        super("Monster","", 30, new Raid(3,assets), assets);
        setId(1);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 10;
        bite = new Bite(this, 5f);
        bite.setAnnounce(true);
        autoAttack = new AutoAttack(this, 1f);

        //loadMechanics(autoAttack, bite);
        phaseManager.addPhase(new Phase(this, 0, autoAttack, bite));
    }

    @Override
    public void reward() {
        rewardPackage.addNewLevelText();
    }
}
