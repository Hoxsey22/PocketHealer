package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.DoubleAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Massacre;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Phase;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.PoisonSpit;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.StoneSkinEffect;

/**
 * Created by Hoxsey on 10/11/2017.
 */


/*Need to add another mech*/
public class Hydra extends Boss {

    Massacre massacre;
    DoubleAttack doubleAttack;
    PoisonSpit p1Poison;
    PoisonSpit p2Poison;
    AutoAttack autoAttack;


    public Hydra(Assets assets) {
        super("Ion, The Hydra", "A monstrous hydra is blocking the way to the top of castle. " +
                "This hydra has been altered for mass destruction. \nDon't let the raid drop below 10% or they die.",
                240,
                new Raid(12, assets),
                assets);
        setId(15);
        create();
    }

    @Override
    public void create() {
        super.create();

        setDamage(20);

        autoAttack = new AutoAttack(this, 1.5f);
        doubleAttack = new DoubleAttack(this, 13f);
        p1Poison = new PoisonSpit(this, 5f, 4, true);
        p2Poison = new PoisonSpit(this, 5f, 8, false);
        massacre = new Massacre(this, 3f);

        phaseManager.addPhase(new Phase(this, 15f, autoAttack, doubleAttack,p1Poison));
        phaseManager.addPhase(new Phase(this, 5f, massacre));
        phaseManager.addPhase(new Phase(this, 15f, autoAttack, doubleAttack,p1Poison));
        phaseManager.addPhase(new Phase(this, 5f, massacre));
        phaseManager.addPhase(new Phase(this, 15f, autoAttack, doubleAttack,p1Poison));
        phaseManager.addPhase(new Phase(this, 5f, massacre));

        phaseManager.addPhase(new Phase(this, 20f, p2Poison));

    }

    @Override
    public void start() {
        super.start();
        for(int i = 0; i < getEnemies().raidMembers.size(); i++)   {
            getEnemies().getRaidMember(i).addStatusEffect(new StoneSkinEffect(this));
        }
    }

    @Override
    public void reward() {
        if(player.getLevel() >= getId())
        rewardPackage.addNewLevelText();
    }
}