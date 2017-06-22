package com.hoxseygaming.pockethealer.oldcode.players;

import com.badlogic.gdx.math.Rectangle;
import com.hoxseygaming.pockethealer.oldcode.players.spells.Spell;

/**
 * Created by Hoxsey on 6/7/2017.
 */
public class SpellButton extends Rectangle {
    private Spell spell;

    public SpellButton(Spell spell, int x, int y)    {
        this.spell = spell;
        this.x = x;
        this.y = y;
        width = 80;
        height = 80;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }
}
