package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BackStab;
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
                "and will back stab a random raid member dealing heavy damage and leaving behind a bleed.", 3000,new Raid(5, assets), assets);
        setId(6);
        create();
    }

    @Override
    public void create() {
        super.create();
        damage = 20;

        autoAttack = new AutoAttack(this, 1f);
        backStab = new BackStab(this);
        loadMechanics(autoAttack, backStab);
    }

    @Override
    public void update() {
        if(getHpPercent() < 0.25 && !isEnrage)    {
            setDamage(30);
            autoAttack.setDamage(damage);
            backStab.setDamage(damage*3);
            isEnrage = true;
            displayAnnouncementTimer(name+" is now enraged!");
        }
    }

    @Override
    public void reward() {
        rewardPackage = new RewardPackage(assets.getTexture(assets.smiteIcon), "New Spell!\nNew Talent Point\nLevel up!");
    }
}
