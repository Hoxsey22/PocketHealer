package com.hoxseygaming.pockethealer;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.player.bars.SpellBar;
import com.hoxseygaming.pockethealer.encounters.spells.Heal;
import com.hoxseygaming.pockethealer.encounters.spells.Renew;
import com.hoxseygaming.pockethealer.encounters.spells.SpellBook;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Player {

    private final int originCritical = 10;


    public int maxMana;
    public int mana;
    public RaidMember target;
    public Raid raid;
    private Boss eTarget;
    public SpellBar spellBar;
    public float spellCastPercent;
    public boolean isCasting;
    public Assets assets;
    public TalentTree talentTree;
    public SpellBook spellBook;
    public int criticalChance;
    public int level;

    public Player(Assets assets) {
        level = 15;
        maxMana = 1000;
        mana = 1000;
        spellCastPercent = 0;
        setAssets(assets);
        //addDebuggingSpell();
        isCasting = false;
        //talentBook = new TalentBook(this);
        talentTree = new TalentTree(this,15);
        spellBook = new SpellBook(this);
        createSpellBar();
        criticalChance = originCritical;
    }
    /*
    public void addDebuggingSpell() {
        spells.add(new Heal(this, spells.size(), assets));
        spellBar.addSpell(spells.get(spells.size()-1));

        //spells.add(new FlashHeal(this, spells.size(), assets));
        //spellBar.addSpell(spells.get(spells.size()-1));

        spells.add(new Renew(this, spells.size(), assets));
        spellBar.addSpell(spells.get(spells.size()-1));

        spells.add(new Smite(this, spells.size(), assets));
        spellBar.addSpell(spells.get(spells.size()-1));

        spells.add(new HolyNova(this, spells.size(), assets));
        spellBar.addSpell(spells.get(spells.size()-1));
        spells.add(new Lightwell(this, spells.size(), assets));
        spellBar.addSpell(spells.get(spells.size()-1));


        for(int i = 0; i < spells.size(); i++)   {
            System.out.println(spellBar.getSpell(i).name+" added to spell bar!");
        }
    }
    */

    public void loadTalents()   {
        for (int i = 0; i <  spellBar.spells.size(); i++)    {
            spellBar.spells.get(i).checkTalents();
        }
    }

    public void newLevel(int level)  {
        if(level > this.level)    {
            this.level = level;
        }
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

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public Assets getAssets()   {
        return assets;
    }

    public void createSpellBar()    {
        spellBar = new SpellBar(this);
        spellBar.addSpell(3,new Heal(this,0,assets));
        spellBar.addSpell(1, new Renew(this, 0, assets));
    }

    public void reset() {
        mana = maxMana;
        stop();
        for (int i = 0; i < spellBar.spells.size(); i++) {
            spellBar.spells.get(i).resetCD();
        }
    }

    public void stop()  {
        for (int i = 0; i < spellBar.spells.size(); i++) {
            spellBar.spells.get(i).stop();
        }
    }

    public Boss getBoss() {
        return eTarget;
    }

    public void setBoss(Boss eTarget) {
        this.eTarget = eTarget;
    }

    public void addPoint()  {
        talentTree.addPoint();
    }

    public void setTalentTree(TalentTree talentTree) {
        this.talentTree = talentTree;
    }

    public Raid getRaid() {
        return raid;
    }

    public void setCasting(boolean casting) {
        isCasting = casting;
    }

    public TalentTree getTalentTree() {
        return talentTree;
    }

    public SpellBook getSpellBook() {
        return spellBook;
    }

    public void setSpellBook(SpellBook spellBook) {
        this.spellBook = spellBook;
    }

    public SpellBar getSpellBar() {
        return spellBar;
    }

    public void setSpellBar(SpellBar spellBar) {
        this.spellBar = spellBar;
    }

    public int getCriticalChance() {
        return criticalChance;
    }

    public void setCriticalChance(int criticalChance) {
        this.criticalChance = criticalChance;
    }
}
