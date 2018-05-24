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
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.ConsumingShadowEffect;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.VampiricBiteEffect;

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
                "The queen of all vampires has come to stop you in your tracks. She bites her victims, granted them " +
                        "immense power at a price. She also links to souls together causing them to share pain. Lastly, in desperate times " +
                        "she will fly in the air and unleash a wrath of shadow damage and cursing people to take more of this shadow damage. ",
                600,
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
        setDamage(15);

        bloodLink = new BloodLink(this, 2f);
        cleave = new Cleave(this, 4f);
        cleave.setNumOfTargets(4);
        cleave.setDamage(35);
        vampiricBite = new VampiricBite(this);
        consumingShadow = new ConsumingShadow(this, 8f);

        swarmingShadow = new SwarmingShadow(this, 10, 8f);

        phaseManager.addPhase(new Phase(this, 70f, bloodLink, cleave, vampiricBite));
        phaseManager.addPhase(new Phase(this, 32f, consumingShadow,swarmingShadow));

        loadDebuff(new VampiricBiteEffect(this), new ConsumingShadowEffect(this));
    }

    @Override
    public void reward() {
        if(player.getLevel() >= getId())
            rewardPackage.addNewLevelText();
    }
}
