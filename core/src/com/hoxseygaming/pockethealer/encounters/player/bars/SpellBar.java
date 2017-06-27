package com.hoxseygaming.pockethealer.encounters.player.bars;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.hoxseygaming.pockethealer.encounters.EncounterData;
import com.hoxseygaming.pockethealer.encounters.spells.Spell;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/20/2017.
 */
public class SpellBar extends Group {

    public ArrayList<Spell> spells;
    //public Texture background;

    public SpellBar()   {
        spells = new ArrayList<Spell>();
        //background = EncounterData.blackBar;
    }

    public void addSpell(Spell spell)  {
        spells.add(spell);
        addActor(spells.get(spells.size()-1));
    }

    public Spell getSpell(int index)  {
        return spells.get(index);
    }

    public void clearSpells()   {
        spells.clear();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}

