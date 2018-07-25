package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Strings;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Phase;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Pounce;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.BleedEffect;

/**
 * Created by Hoxsey on 7/20/2017.
 */

public class WampusCat extends Boss {

    private Pounce pounce;
    private AutoAttack autoAttack;

    public WampusCat(Assets assets) {
        super("Wampus Cat",
                Strings.WAMPUS_CAT_DESCRIPTION,
                210 ,
                new Raid(9, assets),
                assets);

        setId(8);
        create();
    }

    @Override
    public void create()    {
        super.create();
        setDamage(20);
        autoAttack = new AutoAttack(this);
        pounce = new Pounce(this);

        getPhaseManager().addPhase(new Phase(this, getName()+" is in her Cat Form!",30f, pounce, autoAttack));
        getPhaseManager().addPhase(new Phase(this,getName()+"is in her Human Form!", 30f, autoAttack));

        loadDebuff(new BleedEffect(this));
    }

    @Override
    public void reward() {
        if(getPlayer().getLevel() >= getId()) {
            getRewardPackage().addNewLevelText();
            getRewardPackage().addNewTalentText();
            getRewardPackage().addNewSpellText();
            getRewardPackage().addImage(new Image(getAssets().getTexture(getAssets().penanceIcon)));
        }
    }

    public Pounce getPounce() {
        return pounce;
    }

    public void setPounce(Pounce pounce) {
        this.pounce = pounce;
    }

    public AutoAttack getAutoAttack() {
        return autoAttack;
    }

    public void setAutoAttack(AutoAttack autoAttack) {
        this.autoAttack = autoAttack;
    }
}
