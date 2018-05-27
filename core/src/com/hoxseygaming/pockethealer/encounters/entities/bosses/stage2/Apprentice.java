package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Fireball;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Phase;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Pyroblast;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.SeedOfCorruption;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.BurnEffect;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.CorruptionEffect;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.SeedOfCorruptionEffect;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class Apprentice extends Boss {

    public SeedOfCorruption seedOfCorruption;
    public Fireball fireball;
    public Pyroblast pyroblast;

    public Apprentice(Assets assets) {
        super("Apprentice"," The Sorcerer is cornered with no where to go, but her apprentice steps in so his master can escape. He very " +
                        "skilled with fire and corruption spells.",
                240,
                new Raid(12,assets),
                assets);
        setId(10);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 0;


        fireball = new Fireball(this, 2f);
        fireball.setDamage(25);
        pyroblast = new Pyroblast(this, 5f);
        pyroblast.setDamage(60);
        seedOfCorruption = new SeedOfCorruption(this);

        phaseManager.addPhase(new Phase(this,0, fireball, seedOfCorruption));
        //loadMechanics(fireball, seedOfCorruption);
        loadDebuff(new BurnEffect(this), new SeedOfCorruptionEffect(this), new CorruptionEffect(this));
    }

    @Override
    public void reward() {
        if(player.getLevel() >= getId()) {
            rewardPackage.addNewLevelText();
            rewardPackage.addNewTalentText();
        }
    }
}
