package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Agony;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BlanketCorruption;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.FireBreath;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Fireball;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Phase;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class Sorcerer extends Boss {

    public Agony agony;
    public BlanketCorruption blanketCorruption;
    public Fireball fireball;
    public FireBreath fireBreath;

    public Sorcerer(Assets assets) {
        super("Sorcerer","The time has come, the Sorcerer is finally taking a stand. The Sorcerer is very similar to his " +
                "apprentice, but has one more trick up his sleeve.",
                240,
                new Raid(12,assets),
                assets);
        setId(11);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 0;

        agony = new Agony(this);
        blanketCorruption = new BlanketCorruption(this);
        fireball = new Fireball(this, 3f);
        fireBreath = new FireBreath(this);
        fireBreath.setSpeed(35f);

        phaseManager.addPhase(new Phase(this, 0, fireball, agony, blanketCorruption, fireBreath));
        //loadMechanics(agony,blanketCorruption,fireball,fireBreath);
    }

    @Override
    public void reward() {
        if(player.getLevel() >= getId()) {
            rewardPackage.addNewLevelText();
            rewardPackage.addNewTalentText();
        }
    }
}
