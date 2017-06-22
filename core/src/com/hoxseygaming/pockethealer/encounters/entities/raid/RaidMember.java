package com.hoxseygaming.pockethealer.encounters.entities.raid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.hoxseygaming.pockethealer.encounters.EncounterData;
import com.hoxseygaming.pockethealer.encounters.entities.Entity;

import java.util.Comparator;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class RaidMember extends Entity implements Comparable<RaidMember>, Comparator<RaidMember> {

    public Texture frame;
    public HealthBar healthBar;


    public RaidMember(int id, String role)  {
        super(id,role);
        healthBar = new HealthBar((int)getX(),(int)getY());
        frame = EncounterData.raidFrameIdle;
        setRoleImage();
        //frame = EncounterData.raidFrameIdle;
       // frame.setBounds(getX(),getY(),getWidth(),getHeight());
        //frame.setFillParent(true);

    }

    public void setRoleImage()  {
        switch (role)   {
            case "Tank":
                roleImage = new Texture("tank_role_icon.png");
                break;
            case "Healer":
                roleImage = new Texture("healer_role_icon.png");
                break;
            case "Dps":
                roleImage = new Texture("dps_role_icon.png");
                break;
        }
    }

    public void unselected()  {
        selected = false;
        frame = EncounterData.raidFrameIdle;
    }

    public void selected()  {
        selected = true;
        frame = EncounterData.raidFrameSelected;
    }
    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(frame, getX(),getY(),getWidth(),getHeight());
        batch.draw(roleImage, getX()+5, getY() + getHeight()- 39, 34,34);
        for (int i = 0; i < effects.size(); i++) {
            batch.draw(effects.get(i), healthBar.x + healthBar.WIDTH - 20 * (i) - 20, healthBar.y + healthBar.HEIGHT + 5, 20, 20);
        }
        healthBar.draw(batch,alpha,getHpPercent(),getShieldPercent());
    }


    @Override
    public int compareTo(RaidMember rm) {
        return hp - rm.getHp();
    }

    @Override
    public int compare(RaidMember rm1, RaidMember rm2) {
        return rm1.getHp() - rm2.getHp();
    }
}
