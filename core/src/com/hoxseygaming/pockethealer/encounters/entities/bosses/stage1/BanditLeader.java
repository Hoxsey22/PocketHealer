package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BackStab;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Cleave;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.TankSwap;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class BanditLeader extends Boss {

    private AutoAttack autoAttack;
    private BackStab backStab;
    private boolean isEnrage;

    public BanditLeader(Assets assets) {
        super("Bandit Leader","The sorcerer is partnering up the bandit leader and is having" +
                " him steal precious materials for her.\nThe bandit leader does moderate damage to the tank " +
                "and will back stab a random raid member dealing heavy damage and leaving behind a bleed.",
                180,
                new Raid(9, assets),
                assets);
        System.out.println(name+" hp: "+getHp());
        setId(6);
        create();
    }

    @Override
    public void create() {
        super.create();
        setDamage(20);

        autoAttack = new AutoAttack(this, 1f);
        backStab = new BackStab(this);
        TankSwap tankSwap = new TankSwap(this, 8f);
        Cleave cleave = new Cleave(this, 5f);
        cleave.setNumOfTargets(3);
        loadMechanics(autoAttack, backStab,tankSwap, cleave);
    }

    @Override
    public void update() {
        if(getHpPercent() < 0.25 && !isEnrage)    {
            //setDamage(30);
            autoAttack.setDamage((int)((float)damage*1.5f));
            backStab.setDamage((int)((float)damage*3));
            isEnrage = true;
            displayAnnouncementTimer(name+" is now enraged!");
        }
    }

    @Override
    public void reset() {
        super.reset();
        autoAttack.setDamage(damage);
        backStab.setDamage(damage*2);
        isEnrage = false;
    }

    @Override
    public void reward() {
        rewardPackage.addRewardText(1,2,3);
        rewardPackage.setSpellImage(assets.getTexture(assets.smiteIcon));
    }
}
