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
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.AgonyEffect;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.BleedEffect;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.BurnEffect;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.RipEffect;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.UnstableMagicEffect;

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
                "Somehow the Sorcerer has been resurrected! Now stronger than ever, but something is different... She has a " +
                        "dark force around her. She is overflowing with power. She must be stopped!",
                600,
                new Raid(2,3,7, assets),
                assets);
        setId(16);
        create();
    }

    @Override
    public void create() {
        super.create();

        setDamage(20);

        agony = new Agony(this, 7f);
        agony.setDamage(25);
        pyroblast = new Pyroblast(this, 2.5f);
        pyroblast.setDamage(40);
        autoAttackp1 = new AutoAttack(this,2f);

        unstableMagic = new UnstableMagic(this);
        unstablePyroblast = new UnstablePyroblast(this, 1.5f);
        autoAttackp2 = new AutoAttack(this,3f);

        autoAttackp3 = new AutoAttack(this, 2f);
        ripTankSwap = new RipTankSwap(this, 10f);
        flurry = new Flurry(this, 10, 10f);
        tailSwipe = new TailSwipe(this, 20f);
        fireBreath = new FireBreath(this, 8, 30f);

        ignite = new Ignite(this, 8f);

        Phase phase1 = new Phase(this, 75, agony, pyroblast,autoAttackp1);

        Phase phase2 = new Phase(this, 60f, unstableMagic,unstablePyroblast, autoAttackp2);
        phase2.setNameChange(true);
        phase2.setNameChange("Deformed Sorcerer");

        Phase phase3 = new Phase(this, 1, autoAttackp3, ripTankSwap, flurry,tailSwipe,fireBreath, ignite);
        phase3.setNameChange(true);
        phase3.setNameChange("Death Dragon");


        phaseManager.addPhase(phase1);
        phaseManager.addPhase(phase2);
        phaseManager.addPhase(phase3);
        //phaseManager.addPhase(new Phase(this, 600f,fireBreath,flurry,tailSwipe));
        loadDebuff(new AgonyEffect(this), new BurnEffect(this), new UnstableMagicEffect(this),
                new RipEffect(this), new BleedEffect(this));

    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void reward() {
        if(player.getLevel() >= getId())
            rewardPackage.addNewLevelText();
    }
}