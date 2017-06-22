package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.hoxseygaming.pockethealer.encounters.EncounterData;
import com.hoxseygaming.pockethealer.encounters.entities.Entity;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class Boss extends Entity {

    public Raid enemies;
    public RaidMember target;


    public Boss(String name, int maxHp, Raid enemies) {
        super(name, maxHp);
        setBounds(20, 740, 445, 40);
        this.enemies = enemies;
        target = getMainTank();
    }

    public RaidMember getMainTank() {
       return enemies.getRaidMember(0);
    }

    public RaidMember getOffTank() {
        return enemies.getRaidMember(1);
    }

    public void start() {
        System.out.println("BOSS IS NOW ACTIVE!");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(EncounterData.blackBar, getX(), getY(), getWidth(), getHeight());
        batch.draw(EncounterData.raidFrameHbLow, getX()+2, getY()+2, (getWidth()-4), getHeight()-4);
        batch.draw(EncounterData.raidFrameBoss, getX()+2, getY()+2, (getWidth()-4)*getHpPercent(), getHeight()-4);
    }
}
