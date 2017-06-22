package com.hoxseygaming.pockethealer.reformat.player;

import com.hoxseygaming.pockethealer.reformat.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.reformat.spells.Barrier;
import com.hoxseygaming.pockethealer.reformat.spells.FlashHeal;
import com.hoxseygaming.pockethealer.reformat.spells.Heal;
import com.hoxseygaming.pockethealer.reformat.spells.Renew;
import com.hoxseygaming.pockethealer.reformat.spells.Spell;
import com.hoxseygaming.pockethealer.reformat.spells.SpellBar;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Player {

    public int maxMana;
    public int mana;
    public RaidMember target;
    public SpellBar spellBar;
    public ArrayList<Spell> spells;
    public float spellCastPercent;
    public boolean isCasting;

    public Player() {
        maxMana = 1000;
        mana = 1000;
        spellCastPercent = 0;
        spells = new ArrayList<Spell>();
        spellBar = new SpellBar();
        addDebuggingSpell();
        isCasting = false;
    }

    public void addDebuggingSpell() {
        spells.add(new Heal(spells.size(),this));
        spellBar.addSpell(spells.get(spells.size()-1));

        spells.add(new FlashHeal(spells.size(),this));
        spellBar.addSpell(spells.get(spells.size()-1));

        spells.add(new Renew(spells.size(),this));
        spellBar.addSpell(spells.get(spells.size()-1));

        spells.add(new Barrier(spells.size(),this));
        spellBar.addSpell(spells.get(spells.size()-1));

        for(int i = 0; i < spells.size(); i++)   {
            System.out.println(spellBar.getSpell(i).name+" added to spell bar!");
        }
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

    public float manaBarPercent()   {
        return ((float)mana/(float)maxMana);
    }

    public void setSpellCastPercent(float percent)   {
        spellCastPercent = percent;
    }

    public float getSpellCastPercent()  {
        return spellCastPercent;
    }

    public boolean isCasting()  {
        return isCasting;
    }

    public void notCasting()    {
        isCasting = false;
    }

    public RaidMember getTarget() {
        return target;
    }

    public void setTarget(RaidMember target) {
        if(this.target != null) {
            this.target.unselected();
            this.target = target;
            this.target.selected();
        }
        else    {
            this.target = target;
            this.target.selected();
        }

    }
}
