package com.hoxseygaming.pockethealer;

import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.spells.Barrier;
import com.hoxseygaming.pockethealer.encounters.spells.FlashHeal;
import com.hoxseygaming.pockethealer.encounters.spells.Heal;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Renew;
import com.hoxseygaming.pockethealer.encounters.spells.Spell;
import com.hoxseygaming.pockethealer.encounters.player.bars.SpellBar;
import com.hoxseygaming.pockethealer.talent.TalentBook;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Player {

    public int maxMana;
    public int mana;
    public RaidMember target;
    public Raid raid;
    public SpellBar spellBar;
    public ArrayList<Spell> spells;
    public float spellCastPercent;
    public boolean isCasting;
    public Assets assets;
    public TalentBook talentBook;
    public int level;

    public Player(Assets assets) {
        level = 1;
        maxMana = 1000;
        mana = 1000;
        spellCastPercent = 0;
        spells = new ArrayList<Spell>();
        setAssets(assets);
        //addDebuggingSpell();
        isCasting = false;
        talentBook = new TalentBook(this);
    }

    public void addDebuggingSpell() {
        spells.add(new Heal(spells.size(),this, assets));
        spellBar.addSpell(spells.get(spells.size()-1));

        spells.add(new FlashHeal(spells.size(),this, assets));
        spellBar.addSpell(spells.get(spells.size()-1));

        spells.add(new Renew(spells.size(),this, assets));
        spellBar.addSpell(spells.get(spells.size()-1));

        spells.add(new Barrier(spells.size(),this, assets));
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

    public Spell getSpell(String name) {
        for(int i = 0; i < spells.size(); i++)   {
            if(name.equals(spells.get(i).name))    {
                return spells.get(i);
            }
        }
        return null;
    }

    public void setLevel(int level)  {
        this.level = level;
    }

    public int getLevel()   {
        return level;
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

    public void setRaid(Raid raid)   {
        this.raid = raid;
    }

    public void setTalentBook(TalentBook talentBook) {
        this.talentBook = talentBook;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public Assets getAssets()   {
        return assets;
    }

    public void createSpellBar()    {
        spellBar = new SpellBar(assets);
    }
}
