package com.hoxseygaming.pockethealer.encounters.player.bars;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.spells.Heal;
import com.hoxseygaming.pockethealer.encounters.spells.Renew2;
import com.hoxseygaming.pockethealer.encounters.spells.Spell;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/20/2017.
 */
public class SpellBar extends Group {

    public ArrayList<Spell> spells;
    public Image image;
    public Player owner;
    public Assets assets;
    public ArrayList<Rectangle> positions;

    public SpellBar(Player owner) {
        setBounds(0,0,480,97);

        spells = new ArrayList<>(4);
        positions = new ArrayList<>(4);

        this.owner = owner;

        assets = owner.assets;

        image = new Image(assets.getTexture(assets.spellBar));
        image.setBounds(0,0,480,97);
        setName("Spell Bar");

        createPositions();
    }

    public void createPositions()   {
        for (int i = 0; i < 4; i++) {
            positions.add(new Rectangle(78+80*i,8,80,80));
        }
    }


    /**
     * This method will check if a spell actor collides with one of the available spaces and if so,
     * it will add the spell to the spell bar.
     *
     * @param spell
     * @return
     */
    public boolean addSpell(Spell spell)   {
        Rectangle spellBounds = new Rectangle(spell.getX(), spell.getY(), spell.getWidth(), spell.getHeight());
        for(int i = 0; i < positions.size(); i++)   {
            if(spellBounds.overlaps(positions.get(i)))    {
                addSpell(i, spell);
                return true;

            }
        }
        return false;
    }

    public void addSpell(int index, Spell spell)  {
        if(index > spells.size()-1)   {

            for(int i = 0; i < spells.size(); i++)   {
                if(spell.getName().equalsIgnoreCase(spells.get(i).getName()))    {
                    removeActor(spells.get(i));
                    spells.remove(i);
                    spells.add(spell);
                    addActor(spells.get(spells.size()-1));
                    resetSpellPosition();
                    return;
                }
            }
            spells.add(spell);
            addActor(spells.get(spells.size()-1));
            resetSpellPosition();
        }
        else    {
            swapSpell(index, spell);
            resetSpellPosition();
            /*
            removeActor(spells.get(index));
            spells.set(index,spell);
            spells.get(index).setPosition(positions.get(index).getX(), positions.get(index).getY());
            addActor(spells.get(index));
            */
        }
    }

    public void swapSpell(int index, Spell spell) {

        for(int i = 0; i < spells.size(); i++)   {

            if(spell.getName().equalsIgnoreCase(spells.get(i).getName()))    {
                removeActor(spells.get(i));
                spells.set(i, spells.get(index));
                spells.set(index, spell);
                addActor(spells.get(index));
                return;
            }

        }
        removeActor(spells.get(index));
        spells.set(index, spell);
        addActor(spells.get(index));

    }

    public void resetToDefault()    {
        clearSpells();
        addSpell(0,new Heal(owner,0, assets));
        addSpell(1,new Renew2(owner, 0,assets));
    }

    public void loadSpells(ArrayList<String> spellNames)    {
        clearSpells();
        if(spellNames.size() == 0)    {
            addSpell(0,new Heal(owner,0, assets));
            addSpell(1,new Renew2(owner, 0,assets));
        }
        else {

            for (int i = 0; i < spellNames.size(); i++) {
                addSpell(i,owner.getSpellBook().getCopySpell(owner.getSpellBook().getSpell(spellNames.get(i))));
            }
        }
        System.out.println("-- Spells loaded.");
    }


    public void resetSpellPosition()    {
        for(int i = 0; i < spells.size(); i++)   {
            spells.get(i).setPosition(positions.get(i).getX(), positions.get(i).getY());
        }
    }

    public Spell getSpell(int index)  {
        return spells.get(index);
    }

    public void clearSpells()   {
        spells.clear();
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        for(int i = 0; i < spells.size(); i++)   {
            if(positions.get(i).contains(x,y))  {
                return spells.get(i);
            }
        }
        return null;
    }

    public ArrayList<String> getData()  {
        ArrayList<String> spellData = new ArrayList<>();
        for (int i = 0; i < spells.size(); i++) {
            spellData.add(spells.get(i).getName());
        }
        return spellData;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        image.draw(batch, parentAlpha);
        for(int i = 0; i < 4; i++)   {
            if(i > spells.size()-1)    {
                batch.draw(assets.getTexture(assets.blankIcon),
                        positions.get(i).getX(),
                        positions.get(i).getY(),
                        positions.get(i).getWidth(),
                        positions.get(i).getHeight());
            }
        }
        super.draw(batch, parentAlpha);
    }
}

