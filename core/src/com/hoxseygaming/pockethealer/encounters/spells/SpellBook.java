package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 9/1/2017.
 */

public class SpellBook extends Group{

    public static final String HEAL = "Heal";
    public static final String FLASH_HEAL = "Flash Heal";
    public static final String RENEW = "Renew";
    public static final String BARRIER = "Barrier";
    public static final String GREATER_HEAL = "Greater Heal";
    public static final String DIVINE_HYMN = "Divine Hymn";
    public static final String HOLY_NOVA = "Holy Nova";
    public static final String LIGHTWELL = "Lightwell";
    public static final String SMITE = "Smite";
    public static final String PRAYER_OF_MENDING = "Prayer of Mending";
    public static final String DISPEL = "Dispel";
    public static final String HOLY_SHOCK = "Holy Shock";
    public static final String PENANCE = "Penance";
    public static final String DIVINE_PROTECTION = "Divine Protection";
    public static final String BLESSED_GARDEN = "Blessed Garden";

    public Player owner;
    public ArrayList<Spell> spells;
    public Assets assets;

    public SpellBook(Player player) {
        owner = player;
        spells = new ArrayList<>();
        assets = owner.getAssets();
        createSpells();
        placeSpellPosition();
        setName("Spell Book");
        //setBounds(spells.get(3).getX(), spells.get(3).getY(),(spells.get(11).getX() + spells.get(11).getWidth())-spells.get(3).getX(),
                //(spells.get(0).getX() +spells.get(0).getHeight())-spells.get(3).getY());
    }

    public SpellBook(Player player, int addedPoints) {
        owner = player;
        spells = new ArrayList<>();
        assets = owner.getAssets();
        createSpells();
        placeSpellPosition();
        setName("Spell Book");
        //setBounds(spells.get(3).getX(), spells.get(3).getY(),(spells.get(11).getX() + spells.get(11).getWidth())-spells.get(3).getX(),
                //(spells.get(0).getX() +spells.get(0).getHeight())-spells.get(3).getY());
    }

    public void createSpells()  {

        // add spell to list
        spells.add(new Heal(owner, 0, assets));
        spells.add(new Renew2(owner, 0, assets));
        spells.add(new FlashHeal(owner, 0, assets));
        spells.add(new Barrier(owner, 0, assets));
        spells.add(new HolyNova(owner, 0, assets));
        spells.add(new GreaterHeal(owner, 0, assets));
        spells.add(new Smite(owner, 0, assets));
        spells.add(new Lightwell(owner, 0, assets));
        spells.add(new PrayerOfMending(owner, 0, assets));
        spells.add(new Dispel(owner, assets));
        spells.add(new HolyShock(owner, assets));
        spells.add(new Penance(owner, assets));
        spells.add(new DivineHymn(owner, 0, assets));
        spells.add(new DivineProtection(owner, assets));
        spells.add(new BlessedGarden(owner, assets));
        // add spell to group
        for(int i = 0; i < spells.size(); i++)   {
            addActor(spells.get(i));
        }
    }

    public void placeSpellPosition()   {

        for(int i = 0; i < 3; i++)   {
            for(int j = 0; j < 5; j++)   {
                spells.get(i*5+j).setBounds(30+spells.get(i).getWidth()*j+30*j,610-spells.get(i).getWidth()*i-30*i,60,60);

            }
        }

    }

    public Spell selectSpell(float x, float y)  {
        Spell hit = hit(x,y);
        if(hit != null) {
            if(isSpellSelectable(hit))  {
                return getCopySpell(hit);
            }
        }
        return null;

    }

    public Spell hit(float x, float y) {
        Actor hit = hit(x,y,false);
        if(hit != null)    {
            Spell spell = getSpell(hit.getName());
            return spell;
        }
        return null;
    }

    public Spell getSpell(String name)   {
        for(int i = 0; i < spells.size(); i++)   {
            if(spells.get(i).getName().equalsIgnoreCase(name))    {
                return spells.get(i);
            }
        }
        System.out.println("Cannot find spell!");
        return null;
    }

    private boolean isSpellSelectable(Spell spell)    {
        if(owner.getLevel() >= spell.levelRequirement)    {
            return true;
        }
        return false;
    }

    public Spell getCopySpell(Spell spell)    {
        switch (spell.getName())    {
            case HEAL:
                return new Heal(owner,0,assets);
            case FLASH_HEAL:
                return new FlashHeal(owner,0,assets);
            case GREATER_HEAL:
                return new GreaterHeal(owner,0,assets);
            case HOLY_NOVA:
                return new HolyNova(owner,0,assets);
            case RENEW:
                return new Renew2(owner,0,assets);
            case BARRIER:
                return new Barrier(owner,0,assets);
            case LIGHTWELL:
                return new Lightwell(owner,0,assets);
            case SMITE:
                return new Smite(owner,0,assets);
            case PRAYER_OF_MENDING:
                return new PrayerOfMending(owner,0, assets);
            case DISPEL:
                return new Dispel(owner, assets);
            case HOLY_SHOCK:
                return new HolyShock(owner, assets);
            case PENANCE:
                return new Penance(owner, assets);
            case DIVINE_HYMN:
                //if(owner.getTalentTree().getTalent(owner.getTalentTree().HOLY_FOCUS).isSelected()) {
                    return new DivineHymn(owner, 0, assets);
               // }
                //break;
            case DIVINE_PROTECTION:
                if(owner.getTalentTree().getTalent(owner.getTalentTree().CRITICAL_HEALER_II).isSelected()) {
                    return new DivineProtection(owner, assets);
                }
                break;
            case BLESSED_GARDEN:
                if(owner.getTalentTree().getTalent(owner.getTalentTree().AOD).isSelected()) {
                    return new BlessedGarden(owner, assets);
                }
                break;

        }
        return null;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public void setSpells(ArrayList<Spell> spells) {
        this.spells = spells;
    }

    public float getLeft() {
        return getSmallestX();
    }

    public float getBottom()    {
        return getSmallestY();
    }

    public float getRight() {
        return getLargestX() + spells.get(0).getWidth();
    }

    public float getTop()   {
        return getLargestY() + spells.get(0).getHeight();
    }

    private float getLargestY() {
        float largest = 0;
        for(int i = 0; i < spells.size(); i++)   {
            if(largest == 0)    {
                largest = spells.get(i).getY();
            }
            else if(largest < spells.get(i).getY())    {
                largest = spells.get(i).getY();
            }
        }
        return largest;
    }

    private float getSmallestY()    {
        float smallest = 0;
        for(int i = 0; i < spells.size(); i++)   {
            if(smallest == 0)    {
                smallest = spells.get(i).getY();
            }
            else if(smallest > spells.get(i).getY())    {
                smallest = spells.get(i).getY();
            }
        }
        return smallest;
    }

    private float getLargestX() {
        float largest = 0;
        for(int i = 0; i < spells.size(); i++)   {
            if(largest == 0)    {
                largest = spells.get(i).getX();
            }
            else if(largest < spells.get(i).getX())    {
                largest = spells.get(i).getX();
            }
        }
        return largest;
    }

    private float getSmallestX()    {
        float smallest = 0;
        for(int i = 0; i < spells.size(); i++)   {
            if(smallest == 0)    {
                smallest = spells.get(i).getX();
            }
            else if(smallest > spells.get(i).getX())    {
                smallest = spells.get(i).getX();
            }
        }
        return smallest;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for(int i = 0; i < spells.size(); i++)   {
            if(!isSpellSelectable(spells.get(i))) {
                batch.draw(assets.getTexture(assets.shadowIcon), spells.get(i).getX(), spells.get(i).getY(),
                        spells.get(i).getWidth(), spells.get(i).getHeight());
            }
        }
    }
}
