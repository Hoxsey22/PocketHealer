package com.hoxseygaming.pockethealer.reformat.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.hoxseygaming.pockethealer.reformat.EncounterData;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/20/2017.
 */
public class SpellBar extends Group {

    public ArrayList<Spell> spells;
    public Texture background;

    public SpellBar()   {
        spells = new ArrayList<Spell>();
        background = EncounterData.blackBar;
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
        for(int i = 0; i < spells.size(); i++)
            batch.draw(background,spells.get(i).getX()-5, spells.get(i).getY()-5, 90, 90);

        super.draw(batch, parentAlpha);
    }
}

