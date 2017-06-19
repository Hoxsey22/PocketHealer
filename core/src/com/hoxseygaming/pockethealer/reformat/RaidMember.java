package com.hoxseygaming.pockethealer.reformat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hoxseygaming.pockethealer.players.RaidFrameData;
import com.hoxseygaming.pockethealer.spells.Effect;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class RaidMember extends Entity implements Comparable<RaidMember>, Comparator<RaidMember> {

    public int id;
    public ArrayList<Effect.Mechanic> effects;

    public Image frame = RaidData.raidFrameIdle;
    public HealthBar healthBar;


    public RaidMember(int id, String role)  {
        super(id,role);

        setBounds((float) com.hoxseygaming.pockethealer.players.RaidFrameData.postion[(id)*2+1],
                (float) com.hoxseygaming.pockethealer.players.RaidFrameData.postion[id*2],134,64);

        healthBar = new HealthBar((int)getX(),(int)getY());

        frame.setBounds(getX(),getY(),getWidth(),getHeight());

        roleImage.setWidth(34);
        roleImage.setHeight(34);
        roleImage.setPosition(healthBar.x, healthBar.y + healthBar.HEIGHT);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        frame.draw(batch, alpha);
        roleImage.draw(batch, alpha);
        for (int i = 0; i < effects.size(); i++) {
            switch (effects.get(i)) {
                case HEALOVERTIME:
                    batch.draw(RaidFrameData.renewIconImage, healthBar.x + healthBar.WIDTH - 20 * (i) - 20, healthBar.y + healthBar.HEIGHT + 5, 20, 20);
                    break;
                case SHIELD:
                    batch.draw(RaidFrameData.barrierIconImage, healthBar.x + healthBar.WIDTH - 20 * (i) - 20, healthBar.y + healthBar.HEIGHT + 5, 20, 20);
                    break;
                case BLEED:
                    batch.draw(RaidFrameData.renewIconImage, healthBar.x + healthBar.WIDTH - 20 * (i) - 20, healthBar.y, 10, 10);
                    break;
            }
        }
        healthBar.draw(hpPercent);
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
