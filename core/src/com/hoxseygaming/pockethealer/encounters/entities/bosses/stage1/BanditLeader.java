package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BackStab;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Phase;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.PoisonStab;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.TankSwap;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.BleedEffect;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.PoisonEffect;

/**
 * Created by Hoxsey on 8/20/2017.
 */

public class BanditLeader extends Boss {

    private AutoAttack autoAttack;
    private BackStab backStab;
    private PoisonStab poisonStab;
    private boolean isEnrage;

    public BanditLeader(Assets assets) {
        super("Bandit Leader","The sorcerer is partnering up the bandit Leader and is having" +
                " him steal precious materials for her. If not stopped, the Sorcerer will have all she needs " +
                        "for her plan. Be careful, this bandit is very sneaky and will stab you in the back.",
                180,
                new Raid(9, assets),
                assets);
        System.out.println(getName()+" hp: "+getHp());
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

        getPhaseManager().addPhase(new Phase(this, 0, autoAttack,tankSwap, backStab, poisonStab));

        loadDebuff(new BleedEffect(this), new PoisonEffect(this));
    }

    @Override
    public void update() {
        if(getHpPercent() < 0.25 && !isEnrage)    {
            autoAttack.setDamage((int)((float)getDamage()*2.5f));
            backStab.setDamage((int)((float)getDamage()*3));
            poisonStab.setDamage((int)((float)getDamage()*3));
            isEnrage = true;
            displayAnnouncementTimer(getName()+" is now enraged!");
        }
    }

    @Override
    public void reset() {
        super.reset();
        autoAttack.setDamage(getDamage());
        backStab.setDamage(getDamage()*2);
        isEnrage = false;
    }

    @Override
    public void reward() {
        if(getPlayer().getLevel() >= getId()) {
            getRewardPackage().addNewLevelText();
            getRewardPackage().addNewTalentText();
            getRewardPackage().addNewSpellText();
            getRewardPackage().addImage(new Image(getAssets().getTexture(getAssets().smiteIcon)));
        }
    }

}
