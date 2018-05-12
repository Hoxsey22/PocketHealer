package com.hoxseygaming.pockethealer.encounters.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.HealingTracker;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class Entity extends Actor{

    public int id;
    public String name;
    public int maxHp;
    public int hp;
    public float hpPercent;
    public int shield;
    public int healingAbsorb;
    public String role;
    public int damage;
    public boolean isDead;
    public Texture roleImage;
    public boolean selected;
    public Assets assets;
    public int amountAbsorbed;
    public HealingTracker healingTracker;

    /**
     * RaidMember param
     * @param id
     * @param role
     */
    public Entity(int id, String role, Assets assets) {
        this.assets = assets;
        setBounds(assets.raidPositions.get(id).x,
                assets.raidPositions.get(id).y,146,70);

        this.id = id;
        this.role = role;
        setRoleStats(role);
        hp = maxHp;
        hpPercent = getHpPercent();
        shield = 0;
        isDead = false;
        selected = false;
        healingTracker = new HealingTracker();
    }

    /**
     * BOSS param
     * @param name
     * @param maxHp
     */
    public Entity(String name, int maxHp, Assets assets) {
        this.assets = assets;
        this.name = name;
        this.maxHp = maxHp;
        hp = maxHp;
        shield = 0;
        isDead = false;
        healingTracker = new HealingTracker();
    }

    public void setRoleStats(String role)    {
        switch (role)   {
            case "Tank":
                maxHp = 200;
                damage = 5;
                break;
            case "Healer":
                maxHp = 100;
                damage = 2;
                break;
            case "Dps":
                maxHp = 100;
                damage = 10;
                break;
        }
    }

    public void takeDamage(int damage)    {
        if(!isDead) {
            if(shield > 0)
                takeShieldDamage(damage);
            else
                hp = hp - damage;

            if(hp <= 0) {
                isDead = true;
                hp = 0;
                shield = 0;
            }
            getHpPercent();
        }
    }

    public int takeDamage(int output, boolean isCritical)    {
        int newOutput = output;
        if(isCritical)
            newOutput = newOutput + (newOutput/2);
        takeDamage(newOutput);
        return newOutput;
    }

    public void takeShieldDamage(int damage)  {
        int oldShield = shield;
        shield = shield - damage;
        if(shield <= 0) {
            hp = hp - Math.abs(shield);
            shield = 0;
            getHpPercent();
            amountAbsorbed = oldShield;
        }
        else {
            amountAbsorbed = damage;
        }
    }

    public void reduceHealingAbsorb(int output)   {
        int oldHealingAbsorb = healingAbsorb;
        healingAbsorb = healingAbsorb - output;

        if(healingAbsorb <= 0) {
            healingTracker.addHealingDone(oldHealingAbsorb);
            receiveHealing(Math.abs(healingAbsorb));
            healingAbsorb = 0;
            getHpPercent();
            return;
        }
        healingTracker.addHealingDone(output);
    }

    public void dealDamage(Entity target)    {
        target.receiveDamage(damage);
        }

    public void receiveDamage(int output) {
        if(!isDead()) {
            hp = hp - output;
            if(hp <= 0) {
                isDead = true;
                hp = 0;
            }
        }
    }

    public void receiveHealing(int output) {
        if (!isDead)   {
            if (hp < maxHp) {
                hp = hp + output;
                if (hp > maxHp) {
                    healingTracker.addHealingDone(output - (hp - maxHp));
                    hp = maxHp;
                    return;
                }
                healingTracker.addHealingDone(output);
            }

        }

    }

    public int receiveHealing(int output, boolean isCritical)    {
        int newOutput = output;

        healingTracker.addTotalHealingDone(newOutput);

        if(isCritical)
            newOutput = newOutput + (newOutput/2);
        if(healingAbsorb > 0) {
            reduceHealingAbsorb(newOutput);
        }
        else {
            receiveHealing(newOutput);
        }
        return newOutput;
    }

    public void applyShield(int output)   {

        int newOutput = output;

        if(newOutput + shield > maxHp) {
            shield = maxHp;
            return;
        }
        shield = shield + newOutput;


        System.out.println("SHIELD APPLIED!");
    }

    public void applyHealingAbsorb(int output) {
        int newOutput = output;

        if(newOutput + healingAbsorb > maxHp) {
            healingAbsorb = maxHp;
        }
        else {
            healingAbsorb = healingAbsorb + newOutput;
        }
    }
    /*Getters and Setters*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getDamage() {
        if(!isDead())
            return damage;
        else
            return 0;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public float getHpPercent() {
        hpPercent = (float)hp/(float)maxHp;
        return hpPercent;
    }

    public float getShieldPercent() {
        return (float)shield/(float)maxHp;
    }

    public void setHpPercent(float hpPercent) {
        this.hpPercent = hpPercent;
    }

    public float getHealingAbsorbPercent()  {
        return (float)healingAbsorb/(float)maxHp;
    }

    public Texture getRoleImage() {
        return roleImage;
    }

    public void setRoleImage(Texture roleImage) {
        this.roleImage = roleImage;
    }


    public boolean isSelected() {
        return selected;
    }

    public void selected() {
        selected = true;
    }

    public void setAssets(Assets assets)    {
        this.assets = assets;
    }

    public void reset() {
        hp = maxHp;
    }

    public boolean equals(Entity entity) {
        return (this.getId() == entity.getId());
    }

    public HealingTracker getHealingTracker() {
        return healingTracker;
    }
}
