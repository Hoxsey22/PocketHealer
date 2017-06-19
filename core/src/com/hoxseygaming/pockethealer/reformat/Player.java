package com.hoxseygaming.pockethealer.reformat;

import com.hoxseygaming.pockethealer.reformat.Spells.Barrier;
import com.hoxseygaming.pockethealer.reformat.Spells.FlashHeal;
import com.hoxseygaming.pockethealer.reformat.Spells.Heal;
import com.hoxseygaming.pockethealer.reformat.Spells.Renew;
import com.hoxseygaming.pockethealer.reformat.Spells.Spell;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Player {

    public int maxMana;
    public int mana;
    public RaidMember target;
    public ArrayList<Spell> spells;

    public Player() {
        maxMana = 1000;
        mana = 1000;
        spells = new ArrayList<Spell>();
        addDebuggingSpell();
    }

    public void addDebuggingSpell() {
        spells.add(new Heal(spells.size()));
        spells.add(new FlashHeal(spells.size()));
        spells.add(new Renew(spells.size()));
        spells.add(new Barrier(spells.size()));
    }

    public void addSpell(int index, Spell newSpell)  {
        spells.add(index, newSpell);
    }

    public void addSpell(Spell newSpell)  {
        spells.add(newSpell);
    }

    public Spell getSpell(int pos) {
        return spells.get(pos);
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public RaidMember getTarget() {
        return target;
    }

    public void setTarget(RaidMember target) {
        this.target = target;
    }
}
