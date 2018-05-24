package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.DoubleAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Rampage;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.ThunderStorm;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Phase;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.PoisonSpit;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.PoisonEffect;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.StoneSkinEffect;

/**
 * Created by Hoxsey on 10/11/2017.
 */


/*Need to add another mech*/
public class Hydra extends Boss {

    ThunderStorm thunderStorm;
    DoubleAttack doubleAttack;
    PoisonSpit p1Poison;
    Rampage rampage;
    public AutoAttack autoAttack;


    public Hydra(Assets assets) {
        super("Ion, The Hydra", "A monstrous hydra is blocking the way to the top of castle. " +
                "This hydra has been altered for mass destruction. \nDon't let the raid drop below 10% or they die.",
                230,
                new Raid(2,3,7, assets),
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
        p1Poison = new PoisonSpit(this, 5.1f, 4, true);
        rampage = new Rampage(this, 15, 5f);
        thunderStorm = new ThunderStorm(this, 15f);

        phaseManager.addPhase(new Phase(this, 75f, autoAttack, doubleAttack,p1Poison, thunderStorm));
        phaseManager.addPhase(new Phase(this, 25f, rampage));

        loadDebuff(new StoneSkinEffect(this), new PoisonEffect(this));

    }

    @Override
    public void start() {
        super.start();
        for(int i = 0; i < getEnemies().raidMembers.size(); i++)   {
            getEnemies().getRaidMember(i).addStatusEffect(new StoneSkinEffect(this));
        }
        System.out.println("Phase Manager - number of phases: "+phaseManager.phases.size()+".");
    }

    @Override
    public void reward() {
        if(player.getLevel() >= getId())
        rewardPackage.addNewLevelText();
    }
}