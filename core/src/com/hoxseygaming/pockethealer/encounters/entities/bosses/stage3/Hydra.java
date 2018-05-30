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

public class Hydra extends Boss {

    private ThunderStorm thunderStorm;
    private DoubleAttack doubleAttack;
    private PoisonSpit p1Poison;
    private Rampage rampage;
    private AutoAttack autoAttack;


    public Hydra(Assets assets) {
        super("Ion, The Hydra", "A monstrous hydra is blocking the way to the top of castle. " +
                "This hydra has been altered for mass destruction with electricity. This beast can also go on a blind rage. Luckily," +
                        " the injured shaman in the monster's room is going to try to help with a strong spell, Stone Skin.",
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
        p1Poison = new PoisonSpit(this, 5.1f, 4);
        rampage = new Rampage(this, 15, 5f);
        thunderStorm = new ThunderStorm(this, 15f);

        getPhaseManager().addPhase(new Phase(this, 75f, autoAttack, doubleAttack,p1Poison, thunderStorm));
        getPhaseManager().addPhase(new Phase(this, 25f, rampage));

        loadDebuff(new StoneSkinEffect(this), new PoisonEffect(this));

    }

    @Override
    public void start() {
        super.start();
        for(int i = 0; i < getEnemies().getRaidMembers().size(); i++)   {
            getEnemies().getRaidMember(i).addStatusEffect(new StoneSkinEffect(this));
        }
        System.out.println("Phase Manager - number of phases: "+getPhaseManager().getPhases().size()+".");
    }

    @Override
    public void reward() {
        if(getPlayer().getLevel() >= getId())
        getRewardPackage().addNewLevelText();
    }

}