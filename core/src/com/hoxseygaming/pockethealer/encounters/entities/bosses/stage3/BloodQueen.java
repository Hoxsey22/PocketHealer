package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BloodLink;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Cleave;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.ConsumingShadow;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Phase;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.SwarmingShadow;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.VampiricBite;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/26/2017.
 */

public class BloodQueen extends Boss {

    private BloodLink bloodLink;
    private Cleave cleave;
    private SwarmingShadow swarmingShadow;
    private ConsumingShadow consumingShadow;
    private VampiricBite vampiricBite;

    public BloodQueen(Assets assets) {
        super("Blood Queen",
                "",
                480,
                new Raid(12,assets),
                assets);
        setId(14);
        create();
    }

    @Override
    public void update() {

    }

    @Override
    public void create() {
        super.create();
        setDamage(5);

        bloodLink = new BloodLink(this, 2f);
        cleave = new Cleave(this, 4f);
        cleave.setNumOfTargets(4);
        vampiricBite = new VampiricBite(this);
        consumingShadow = new ConsumingShadow(this, 8f);

        swarmingShadow = new SwarmingShadow(this, 6, 8f);

        phaseManager.addPhase(new Phase(this, 70f, bloodLink, cleave, vampiricBite));
        phaseManager.addPhase(new Phase(this, 32f, consumingShadow,swarmingShadow));
    }

    @Override
    public void reward() {
        rewardPackage.addRewardText(3);
    }

    @Override
    public void start() {
        enemies.start(this);
        phaseManager.startPhase();
    }

    @Override
    public void stop() {
        phaseManager.cleanPhases();
    }
}
