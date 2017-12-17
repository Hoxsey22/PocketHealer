package com.hoxseygaming.pockethealer;

import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.player.bars.CastBar;
import com.hoxseygaming.pockethealer.encounters.player.bars.ManaBar;
import com.hoxseygaming.pockethealer.encounters.player.bars.SpellBar;
import com.hoxseygaming.pockethealer.encounters.spells.SpellBook;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Player {

    public static class PlayerData implements Serializable{
        public int level;
        public int totalPoints;
        public int unusedPoints;
        public ArrayList<String> talents;
        public ArrayList<String> spells;

        public PlayerData() {
            level = 0;
            totalPoints = 0;
            unusedPoints = 0;
            talents = new ArrayList<>();
            spells = new ArrayList<>();
        }

        public void setData(int level, int totalPoints, int usedPoints, ArrayList<String> talents, ArrayList<String> spells){
            this.level = level;
            this.totalPoints = totalPoints;
            this.unusedPoints = usedPoints;
            this.talents = talents;
            this.spells = spells;
        }
    }

    private final int originCritical = 10;
    public int maxMana;
    public int mana;
    public ManaBar manaBar;
    public CastBar castBar;
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
    private PlayerData playerData;
    public boolean holyShockIncrease;



    public Player(Assets assets) {
        level = 0;
        maxMana = 1000;
        mana = 1000;
        spellCastPercent = 0;
        setAssets(assets);
        //addDebuggingSpell();
        isCasting = false;
        //talentBook = new TalentBook(this);
        talentTree = new TalentTree(this,15,15);
        spellBook = new SpellBook(this);
        createSpellBar();
        criticalChance = originCritical;

        manaBar = new ManaBar(this, assets);

        castBar = new CastBar(this, assets);
        castBar.anchor(manaBar);
        playerData = new PlayerData();
    }
    /*
    public void addDebuggingSpell() {
        spells.add(new Heal(this, spells.size(), assets));
        spellBar.addSpell(spells.get(spells.size()-1));

        //spells.add(new FlashHeal(this, spells.size(), assets));
        //spellBar.addSpell(spells.get(spells.size()-1));

        spells.add(new RenewEffect(this, spells.size(), assets));
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
        if(level > this.level)
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

    public ManaBar getManaBar() {
        return manaBar;
    }

    public void setManaBar(ManaBar manaBar) {
        this.manaBar = manaBar;
    }

    public CastBar getCastBar() {
        return castBar;
    }

    public void setCastBar(CastBar castBar) {
        this.castBar = castBar;
    }

    public void receiveMana(int amount)   {
        mana = mana +amount;
        if(mana > maxMana)    {
            mana = maxMana;
        }
    }

    public void save()  {
        GameData.save(this);
    }

    public void load()  {
        GameData.load(this);
    }

    public PlayerData getData()   {
        playerData.setData(getLevel(), talentTree.getTotalPoints(), talentTree.getUnusedPoints(), talentTree.getData(), spellBar.getData());
        return playerData;
    }

    public void setData(PlayerData data)   {
        setLevel(data.level);
        talentTree.setTotalPoints(data.totalPoints);
        talentTree.setUnusedPoints(data.unusedPoints);
        talentTree.loadTalents(data.talents);
        spellBar.loadSpells(data.spells);
        System.out.println("- Player loaded.");
    }


}
