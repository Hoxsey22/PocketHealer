package com.hoxseygaming.pockethealer.encounters.entities.raid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.Entity;
import com.hoxseygaming.pockethealer.encounters.floatingtext.FloatingText;
import com.hoxseygaming.pockethealer.encounters.floatingtext.FloatingTextManager;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.StatusEffect;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.StatusEffectList;

import java.util.Comparator;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class RaidMember extends Entity implements Comparable<RaidMember>, Comparator<RaidMember> {

    public Texture frame;
    public HealthBar healthBar;
    public FloatingTextManager floatingTextManager;
    public EventListener eventListener;
    public int totalHealingDone;
    public int overHealingDone;
    public StatusEffectList statusEffects;
    public boolean isDamaged;


    public RaidMember(int id, String role, Assets assets)  {
        super(id,role,assets);
        healthBar = new HealthBar(this,(int)getX(),(int)getY(),(int)getWidth(),(int) getHeight());
        frame = assets.getTexture("raid_frame_idle.png");
        setRoleImage();
        floatingTextManager = new FloatingTextManager(this, assets);
        statusEffects = new StatusEffectList(this);

    }

    @Override
    public void takeDamage(int output) {
        int newOutput = statusEffects.getStatusEffectModifucation(output,false);
        super.takeDamage(newOutput);
        floatingTextManager.add(newOutput, FloatingText.DAMAGE);
        if(statusEffects.contains("Prayer of Mending"))    {
            statusEffects.getStatusEffect("Prayer of Mending").applyEffect();
        }
    }

    @Override
    public void receiveHealing(int output) {
        super.receiveHealing(output);
    }

    @Override
    public int receiveHealing(int output, boolean isCritical) {
        int newOutput = statusEffects.getStatusEffectModifucation(output,true);
        newOutput = super.receiveHealing(newOutput, isCritical);
        floatingTextManager.add(newOutput,FloatingText.HEAL, isCritical);
        return newOutput;
    }

    @Override
    public void applyShield(int output) {
        super.applyShield(output);
        floatingTextManager.add(output,FloatingText.SHIELD);
    }

    public void setRoleImage()  {
        switch (role)   {
            case "Tank":
                roleImage = assets.getTexture(assets.tankIcon);
                break;
            case "Healer":
                roleImage = assets.getTexture(assets.healerIcon);
                break;
            case "Dps":
                roleImage = assets.getTexture(assets.dpsIcon);
                break;
        }
    }

    public void unselected()  {
        selected = false;
        frame = assets.getTexture(assets.raidFrameIdle);
    }

    public void selected()  {
        selected = true;
        frame = assets.getTexture("raid_frame_selected.png");
    }

    @Override
    public int compareTo(RaidMember rm) {
        return (int)(getHealthPercent()*100) - (int)(rm.getHealthPercent()*100);
    }

    @Override
    public int compare(RaidMember rm1, RaidMember rm2) {
        return (int)(rm1.getHealthPercent()*100) - (int)(rm2.getHealthPercent()*100);
    }

    public float getHealthPercent() {
        return (float)hp/(float)maxHp;
    }

    @Override
    public String toString() {
        return id+":"+role+" hp:"+getHealthPercent();
    }

    public void addStatusEffect(StatusEffect newStatusEffect)   {
        newStatusEffect.setTarget(this);
        statusEffects.add(newStatusEffect);
    }

    public StatusEffectList getStatusEffectList() {
        return statusEffects;
    }

    public void setStatusEffects(StatusEffectList statusEffects) {
        this.statusEffects = statusEffects;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(frame, getX(),getY(),getWidth(),getHeight());
        if(!isDead()) {
            batch.draw(roleImage, getX()+8, getY() + getHeight()- 39, 34,34);
            /*
            for (int i = 0; i < effects.size(); i++) {
                batch.draw(effects.get(i), healthBar.x + healthBar.width - 20 * (i) - 20, healthBar.y + healthBar.height + 5, 20, 20);
            }*/
            statusEffects.draw(batch, alpha);
            healthBar.draw(batch, alpha);
            floatingTextManager.draw(batch, alpha);
        }
        else {
            floatingTextManager.clear();
            batch.draw(assets.getTexture(assets.deathIcon), getX()+5, getY() + getHeight()- 39, 34,34);
        }



    }
}
