package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Cleave;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.TankSwap;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/17/2017.
 */

public class Hogger extends Boss {
    private AutoAttack autoAttack;
    private TankSwap tankSwap;
    private Cleave cleave;
    private int phase;

    public Hogger(Assets assets) {
        super("Hogger","Hogger, the predecessor of the bandit leader, finds out that his replacement has been defeated. So Hogger has stepped back in his role" +
                " and is ready to extract his revenge. He carries a mighty axe that allows him to cleave so be careful.\n ", 3000, new Raid(10,assets), assets);
        setId(7);
        create();
    }

    @Override
    public void create() {
        super.create();

        damage = 20;

        autoAttack = new AutoAttack(this, 2f);
        tankSwap = new TankSwap(this, 8f);
        cleave = new Cleave(this, 2f);
        cleave.setDamage(damage);

        loadMechanics(autoAttack,tankSwap,cleave);
        phase = 0;

    }

    @Override
    public void update() {
        if(getHpPercent() < 0.75 && getHpPercent() > 0.51 && phase == 0)    {
            cleave.setNumOfTargets(3);
            cleave.setSpeed(cleave.getSpeed()-0.2f);
            phase = 1;
        }
        else if(getHpPercent() < 0.50 && getHpPercent() > 0.26 && phase == 1)    {
            cleave.setNumOfTargets(4);
            cleave.setSpeed(cleave.getSpeed()-0.2f);
            phase = 2;
        }
        else if(getHpPercent() < 0.25 && phase == 2)    {
            cleave.setNumOfTargets(5);
            cleave.setSpeed(cleave.getSpeed()-0.2f);
            phase = 3;
        }
    }

    @Override
    public void reward() {
        rewardPackage.addRewardText(2,3);
        rewardPackage.setSpellImage(assets.getTexture(assets.lifeboomIcon));
    }
}
