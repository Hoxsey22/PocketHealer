package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BackStab;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.PoisonStab;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.TankSwap;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class BanditLeader extends Boss {

    private AutoAttack autoAttack;
    private BackStab backStab;
    private PoisonStab poisonStab;
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
        setDamage(15);

        autoAttack = new AutoAttack(this, 1.5f);

        backStab = new BackStab(this);
        backStab.setNumOfTargets(2);

        poisonStab = new PoisonStab(this);
        poisonStab.setNumOfTargets(2);

        TankSwap tankSwap = new TankSwap(this, 12f);

        loadMechanics(autoAttack, backStab, poisonStab,tankSwap);
    }

    @Override
    public void update() {
        if(getHpPercent() < 0.25 && !isEnrage)    {
            //setDamage(30);
            autoAttack.setDamage((int)((float)damage*2f));
            backStab.setDamage((int)((float)damage*3.5));
            poisonStab.setDamage((int)((float)damage*3.5));
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
        if(player.getLevel() >= getId()) {
            rewardPackage.addNewLevelText();
            rewardPackage.addNewTalentText();
            rewardPackage.addNewSpellText();
            rewardPackage.addImage(new Image(assets.getTexture(assets.smiteIcon)));
        }
    }
}
