package com.hoxseygaming.pockethealer.players;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.hoxseygaming.pockethealer.spells.Effect;
import com.hoxseygaming.pockethealer.spells.Spell;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Hoxsey on 5/27/2017.
 */
public class Member implements Comparable<Member>, Comparator<Member> {
    public static final String DPS = "DPS";
    public static final String HEALER = "HEALER";
    public static final String TANK = "TANK";

    private int id;
    private int maxHp;
    private int hp;
    private int shield;
    private String role;
    private int damage;
    private Rectangle bounds;
    private Color healthColor;
    private ArrayList<Effect.Mechanic> effects;
    private boolean isDead;

    private Spell spell;

    /**
     * @param id
     * @param hp
     * @param role
     * @param damage
     */
    public Member(int id, int hp, String role, int damage)  {
        this.id = id;
        maxHp = hp;
        this.hp = hp;
        this.role = role;
        this.damage = damage;
        isDead = false;
        effects = new ArrayList<Effect.Mechanic>();
        healthColor = Color.GREEN;
        shield = 0;
    }

    public int getId() {
        return id;
    }

    public void applyEffect(Effect.Mechanic buff) {
        effects.add(buff);
    }

    public void removeEffect(Effect.Mechanic buff)    {
        effects.remove(effects.indexOf(buff));
        System.out.println(buff.toString()+" REMOVED");
    }

    public boolean containsEffects(Effect.Mechanic buff)   {
        if(effects.indexOf(buff) > -1)
            return true;
        else
            return false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getDPS() {
        return DPS;
    }

    public int getHp() {
        return hp;
    }

    public float getHpPercent()  {
        return ((float)hp/(float)maxHp);
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void takeShieldDamage(int damage)  {
        shield = shield - damage;
        if(shield <= 0) {
            hp = hp - Math.abs(shield);
            removeEffect(Effect.Mechanic.SHIELD);
            shield = 0;
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
            setHealthColor();
        }
    }

    public void receiveHealing(int output) {
        if (!isDead)   {
            if (hp < maxHp) {
                hp += output;
                if (hp > maxHp)
                    hp = maxHp;
            }
            setHealthColor();
        }

    }

    public void applyShield(int output)   {
        shield = output;
        System.out.println("SHIELD APPLIED!");
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public ArrayList<Effect.Mechanic> getEffects() {
        return effects;
    }

    public void setBuffs(ArrayList<Effect.Mechanic> buffs) {
        this.effects = buffs;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public Color getHealthColor()   {
        return healthColor;
    }

    public void setHealthColor()    {
        if(hp > maxHp*(0.69))
            healthColor = Color.GREEN;
        else if(hp > maxHp*(0.19))
            healthColor = Color.YELLOW;
        else
            healthColor = Color.RED;

    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public void printBuffs()    {
        System.out.print("Member["+getId()+"] BUFFS: ");
        for(int i = 0; i < effects.size(); i++)   {
            System.out.print(effects.get(i).toString()+",");
        }
        System.out.println();
    }

    @Override
    public int compare(Member m1, Member m2) {
        return m1.getHp() - m2.getHp() ;
    }

    @Override
    public int compareTo(Member m) {
        return getHp() - m.getHp();
    }
}
