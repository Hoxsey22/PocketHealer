package com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Strings;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.AutoAttack;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.BullCharge;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Cleave;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.FireBreath;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Phase;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.TankSwap;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.BurnEffect;

/**
 * Created by Hoxsey on 7/20/2017.
 */

public class Proctor extends Boss {

    private AutoAttack autoAttack;
    private BullCharge bullCharge;
    private Cleave cleave;
    private FireBreath fireBreath;
    private TankSwap tankSwap;

    public Proctor(Assets assets) {
        super("Proctor",
                Strings.PROTECTOR_DESCRIPTION,
                210,
                new Raid(12, assets),
                assets);
        setId(9);
        create();
    }

    @Override
    public void create() {
        super.create();
        setDamage(30);

        autoAttack = new AutoAttack(this);
        bullCharge = new BullCharge(this);
        cleave = new Cleave(this, 5f);
        fireBreath = new FireBreath(this,8,18f);
        tankSwap = new TankSwap(this);

        getPhaseManager().addPhase(new Phase(this, 0, autoAttack, tankSwap, cleave, bullCharge, fireBreath));

        loadDebuff(new BurnEffect(this));
    }

    @Override
    public void reward() {
        if(getPlayer().getLevel() >= getId()) {
            getRewardPackage().addNewLevelText();
        }
    }

    public AutoAttack getAutoAttack() {
        return autoAttack;
    }

    public void setAutoAttack(AutoAttack autoAttack) {
        this.autoAttack = autoAttack;
    }

    public BullCharge getBullCharge() {
        return bullCharge;
    }

    public void setBullCharge(BullCharge bullCharge) {
        this.bullCharge = bullCharge;
    }

    public Cleave getCleave() {
        return cleave;
    }

    public void setCleave(Cleave cleave) {
        this.cleave = cleave;
    }

    public FireBreath getFireBreath() {
        return fireBreath;
    }

    public void setFireBreath(FireBreath fireBreath) {
        this.fireBreath = fireBreath;
    }

    public TankSwap getTankSwap() {
        return tankSwap;
    }

    public void setTankSwap(TankSwap tankSwap) {
        this.tankSwap = tankSwap;
    }
}
