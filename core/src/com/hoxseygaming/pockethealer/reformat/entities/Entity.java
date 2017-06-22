package com.hoxseygaming.pockethealer.reformat.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.hoxseygaming.pockethealer.reformat.EncounterData;
import com.hoxseygaming.pockethealer.reformat.spells.Spell;

import java.util.ArrayList;

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
    public String role;
    public int damage;
    public boolean isDead;
    public Texture roleImage;
    public ArrayList<Texture> effects;
    public boolean selected;

    /**
     * RaidMember param
     * @param id
     * @param role
     */
    public Entity(int id, String role) {
        this.setBounds((float) EncounterData.raidPositions[(id)*2+1],
                (float) EncounterData.raidPositions[id*2],134,64);

        System.out.println("ID: "+id+", x:"+getX()+" y:"+getY()+", width:"+getWidth()+", height:"+getHeight());

        this.id = id;
        this.role = role;
        setRoleStats(role);
        hp = maxHp;
        hpPercent = getHpPercent();
        shield = 0;
        isDead = false;
        effects = new ArrayList<Texture>();
        selected = false;
    }

    /**
     * BOSS param
     * @param name
     * @param maxHp
     */
    public Entity(String name, int maxHp) {
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
        System.out.println("\n\n ID:"+id+" Role:"+role+" is receiving heals!");
        if (!isDead)   {
            if (hp < maxHp) {
                hp = hp + output;
                if (hp > maxHp)
                    hp = maxHp;
            }
        }

    }

    public void applyShield(int output)   {
        shield = output;
        if(!containsEffects(Spell.EffectType.SHIELD))
            applyEffect(Spell.EffectType.SHIELD);
        System.out.println("SHIELD APPLIED!");
    }

    public void applyEffect(Spell.EffectType buff) {
        effects.add(EncounterData.getEffectImage(buff));
    }

    public void removeEffect(Spell.EffectType buff)    {
        effects.remove(effects.indexOf(EncounterData.getEffectImage(buff)));
        System.out.println(buff.toString()+" REMOVED");
    }

    public boolean containsEffects(Spell.EffectType buff)   {
        if(effects.indexOf(EncounterData.getEffectImage(buff)) > -1)
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

    public float getShieldPercent() {
        return (float)shield/(float)maxHp;
    }

    public void setHpPercent(float hpPercent) {
        this.hpPercent = hpPercent;
    }

    public Texture getRoleImage() {
        return roleImage;
    }

    public void setRoleImage(Texture roleImage) {
        this.roleImage = roleImage;
    }

    public ArrayList<Texture> getEffects() {
        return effects;
    }

    public boolean isSelected() {
        return selected;
    }

    public void selected() {
        selected = true;
    }
}
