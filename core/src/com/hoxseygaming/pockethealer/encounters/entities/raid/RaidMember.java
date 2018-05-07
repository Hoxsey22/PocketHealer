package com.hoxseygaming.pockethealer.encounters.entities.raid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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
    public StatusEffectList statusEffects;


    public RaidMember(int id, String role, Assets assets)  {
        super(id,role,assets);
        healthBar = new HealthBar(this);
        frame = assets.getTexture(assets.raidFrameIdle);
        setRoleImage();
        floatingTextManager = new FloatingTextManager(this, assets);
        statusEffects = new StatusEffectList(this);

    }

    @Override
    public void takeDamage(int output) {
        int newOutput = statusEffects.getStatusEffectModification(output,false);
        super.takeDamage(newOutput);
        floatingTextManager.add(newOutput, FloatingText.DAMAGE);
        if(statusEffects.contains("Prayer of Mending"))    {
            statusEffects.getStatusEffect("Prayer of Mending").applyEffect();
        }
        if(isDead)    {
            statusEffects.clear();
        }

    }

    @Override
    public void receiveHealing(int output) {
        super.receiveHealing(output);
    }

    @Override
    public int receiveHealing(int output, boolean isCritical) {
        int newOutput = statusEffects.getStatusEffectModification(output,true);
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
        frame = assets.getTexture(assets.raidFrameSelected);
    }

    @Override
    public int compareTo(RaidMember rm) {
        return (int)(getFullHealthPercent()*100) - (int)(rm.getFullHealthPercent()*100);
    }

    @Override
    public int compare(RaidMember rm1, RaidMember rm2) {
        return (int)(rm1.getFullHealthPercent()*100) - (int)(rm2.getFullHealthPercent()*100);
    }

    public float getHealthPercent() {
        return (float)hp/(float)maxHp;
    }

    public float getFullHealthPercent() {
        return (float)(hp-healingAbsorb)/(float)maxHp;
    }

    @Override
    public String toString() {
        return id+":"+role+" hp:"+getHp();
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
    public void reset() {
        super.reset();
        switch (role)   {
            case "Tank":
                damage = 5;
                break;
            case "Healer":
                damage = 2;
                break;
            case "Dps":
                damage = 10;
                break;
        }
    }

    public void stop()  {
        if(statusEffects != null) {
            for (int i = 0; i < statusEffects.size(); i++) {
                statusEffects.clear();
            }
        }
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
