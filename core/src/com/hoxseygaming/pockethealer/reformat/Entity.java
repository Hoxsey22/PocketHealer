package com.hoxseygaming.pockethealer.reformat;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hoxseygaming.pockethealer.reformat.Spells.Spell;
import com.hoxseygaming.pockethealer.spells.Effect;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public abstract class Entity extends Actor{

    public int id;
    public String name;
    public int maxHp;
    public int hp;
    public float hpPercent;
    public int shield;
    public String role;
    public int damage;
    public boolean isDead;
    public Image roleImage;
    public ArrayList<Spell.EffectType> effects;
    public boolean selected;

    /**
     * RaidMember param
     * @param id
     * @param role
     */
    public Entity(int id, String role) {
        this.id = id;
        this.role = role;
        setRole(role);
        hp = maxHp;
        hpPercent = getHpPercent();
        shield = 0;
        isDead = false;
        effects = new ArrayList<Spell.EffectType>();
        selected = false;
    }

    /**
     * BOSS param
     * @param id
     * @param name
     * @param maxHp
     */
    public Entity(int id, String name, int maxHp) {
        this.id = id;
        this.name = name;
        this.maxHp = maxHp;
        hp = maxHp;
        shield = 0;
        isDead = false;
    }

    public void setRoleStats(String role)    {
        switch (role)   {
            case "Tank":
                maxHp = 200;
                damage = 5;
                roleImage = RaidData.tankIconImage;
                break;
            case "Healer":
                maxHp = 100;
                damage = 2;
                roleImage = RaidData.healerIconImage;
                break;
            case "Dps":
                maxHp = 100;
                damage = 10;
                roleImage = RaidData.dpsIconImage;
                break;
        }
    }

    public void takeDamage(int damage)    {
        if(!isDead) {
            System.out.println("Shield: "+shield);
            if(shield > 0)
                takeShieldDamage(damage);
            else
                hp = hp - damage;

            if(hp <= 0) {
                isDead = true;
                hp = 0;
            }
            getHpPercent();
        }
    }

    public void takeShieldDamage(int damage)  {
        shield = shield - damage;
        if(shield <= 0) {
            hp = hp - Math.abs(shield);
            removeEffect(Spell.EffectType.SHIELD);
            shield = 0;
            getHpPercent();
        }
    }

    public void receiveHealing(int output) {
        if (!isDead)   {
            if (hp < maxHp) {
                hp += output;
                if (hp > maxHp)
                    hp = maxHp;
            }
        }

    }

    public void applyShield(int output)   {
        shield = output;
        System.out.println("SHIELD APPLIED!");
    }

    public void applyEffect(Spell.EffectType buff) {
        effects.add(buff);
    }

    public void removeEffect(Spell.EffectType buff)    {
        effects.remove(effects.indexOf(buff));
        System.out.println(buff.toString()+" REMOVED");
    }

    public boolean containsEffects(Spell.EffectType buff)   {
        if(effects.indexOf(buff) > -1)
            return true;
        else
            return false;
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
        return damage;
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

    public void setHpPercent(float hpPercent) {
        this.hpPercent = hpPercent;
    }

    public Image getRoleImage() {
        return roleImage;
    }

    public void setRoleImage(Image roleImage) {
        this.roleImage = roleImage;
    }

    public ArrayList<Spell.EffectType> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<Spell.EffectType> effects) {
        this.effects = effects;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
