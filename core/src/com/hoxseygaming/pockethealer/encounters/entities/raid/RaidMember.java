package com.hoxseygaming.pockethealer.encounters.entities.raid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.EncounterData;
import com.hoxseygaming.pockethealer.encounters.entities.Entity;

import java.util.Comparator;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class RaidMember extends Entity implements Comparable<RaidMember>, Comparator<RaidMember> {

    public Texture frame;
    public HealthBar healthBar;


    public RaidMember(int id, String role, Assets assets)  {
        super(id,role,assets);
        healthBar = new HealthBar((int)getX(),(int)getY());
        frame = assets.getTexture("raid_frame_idle.png");
        setRoleImage();

    }

    public void setRoleImage()  {
        switch (role)   {
            case "Tank":
                roleImage = assets.getTexture("tank_role_icon.png");
                break;
            case "Healer":
                roleImage = assets.getTexture("healer_role_icon.png");
                break;
            case "Dps":
                roleImage = assets.getTexture("dps_role_icon.png");
                break;
        }
    }

    public void unselected()  {
        selected = false;
        frame = assets.getTexture("raid_frame_idle.png");
    }

    public void selected()  {
        selected = true;
        frame = assets.getTexture("raid_frame_selected.png");
    }
    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(frame, getX(),getY(),getWidth(),getHeight());
        if(!isDead()) {
            batch.draw(roleImage, getX()+5, getY() + getHeight()- 39, 34,34);
            for (int i = 0; i < effects.size(); i++) {
                batch.draw(effects.get(i), healthBar.x + healthBar.WIDTH - 20 * (i) - 20, healthBar.y + healthBar.HEIGHT + 5, 20, 20);
            }
            healthBar.draw(batch, alpha, getHpPercent(), getShieldPercent());
        }
        else {
            batch.draw(assets.getTexture("death_icon.png"), getX()+5, getY() + getHeight()- 39, 34,34);
        }
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
