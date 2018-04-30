package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Agony;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.FireBreath;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Flurry;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Ignite;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Phase;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Pyroblast;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.RipTankSwap;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.TailSwipe;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.UnstableMagic;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.UnstablePyroblast;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 10/11/2017.
 */


/*Need to add another mech*/
public class DeathDragon extends Boss {

    Agony agony;
    Pyroblast pyroblast;
    AutoAttack autoAttackp1;

    UnstableMagic unstableMagic;
    UnstablePyroblast unstablePyroblast;
    AutoAttack autoAttackp2;

    AutoAttack autoAttackp3;
    RipTankSwap ripTankSwap;
    TailSwipe tailSwipe;
    Flurry flurry;
    FireBreath fireBreath;
    Ignite ignite;


    public DeathDragon(Assets assets) {
        super("Resurrected Sorcerer",
                "The Sorcerer has been resurrected and now stronger than ever. He's come to finish what " +
                        "he has started. ",
                600,
                new Raid(12, assets),
                assets);
        setId(16);
        create();
    }

    @Override
    public void create() {
        super.create();

        setDamage(20);

        agony = new Agony(this);
        agony.setDamage(25);
        pyroblast = new Pyroblast(this, 3f);
        pyroblast.setDamage(40);
        autoAttackp1 = new AutoAttack(this,2f);

        unstableMagic = new UnstableMagic(this);
        unstablePyroblast = new UnstablePyroblast(this, 3f);
        autoAttackp2 = new AutoAttack(this,3f);

        autoAttackp3 = new AutoAttack(this, 2f);
        ripTankSwap = new RipTankSwap(this, 10f);
        flurry = new Flurry(this, 10, 8f);
        tailSwipe = new TailSwipe(this, 12f);
        fireBreath = new FireBreath(this, 8, 20f);

        ignite = new Ignite(this, 8f);


        phaseManager.addPhase(new Phase(this, 75, agony, pyroblast,autoAttackp1));
        phaseManager.addPhase(new Phase(this, 80f, unstableMagic,unstablePyroblast, autoAttackp2));
        phaseManager.addPhase(new Phase(this, 1, autoAttackp3, ripTankSwap, flurry,tailSwipe,fireBreath, ignite));
        //phaseManager.addPhase(new Phase(this, 600f,fireBreath,flurry,tailSwipe));

    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void start() {
        enemies.start(this);
        phaseManager.startPhase();
    }

    @Override
    public void reward() {
        if(player.getLevel() >= getId())
            rewardPackage.addNewLevelText();
    }

    @Override
    public void stop() {
        phaseManager.cleanPhases();
    }
}