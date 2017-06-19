package com.hoxseygaming.pockethealer.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hoxseygaming.pockethealer.PocketHealer;
import com.hoxseygaming.pockethealer.SpellButton;
import com.hoxseygaming.pockethealer.spells.Spell;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 5/27/2017.
 */
public class Player {
    private int maxMana;
    private int mana;
    private Party party;
    private ShapeRenderer shapeRenderer;
    private ArrayList<SpellButton> spellbutton;

    public Player() {
        spellbutton = new ArrayList<SpellButton>(4);
        spellbutton.add(new SpellButton(Spell.HEAL,20,80));
        spellbutton.add(new SpellButton(Spell.FLASH_HEAL,135,80));
        spellbutton.add(new SpellButton(Spell.RENEW,250,80));
        spellbutton.add(new SpellButton(Spell.BARRIER,365,80));
        maxMana = 1000;
        mana = 1000;
        shapeRenderer = new ShapeRenderer();
    }

    public Player(ArrayList<SpellButton> spellButton) {
        this.spellbutton= spellbutton;
        maxMana = 1000;
        mana = 1000;
        shapeRenderer = new ShapeRenderer();
    }

    public Member getTarget()   {
        return party.target.getMember();
    }

    public void draw(SpriteBatch sb)  {

        // manabar
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.rect(10,185,460,40);
            shapeRenderer.setColor(Color.BLUE);
            shapeRenderer.rect(15,190,450*((float)mana/(float)maxMana),30);
        shapeRenderer.end();

        //spells
        shapeRenderer.setColor(Color.BLACK);
        for(int i = 0; i <  spellbutton.size(); i++) {



            //background of spell button
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.rect(15 + (i * 115), 75, 90,90);
            shapeRenderer.end();
            sb.begin();
                sb.draw(getSpell(i).getImage(), spellbutton.get(i).x, spellbutton.get(i).y,
                        spellbutton.get(i).width, spellbutton.get(i).height);
            sb.end();

            // cooldown visual
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.setColor(0,0,0,0.83f);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.rect(15 + (i * 115), 75, 90,(90*( spellbutton.get(i).getSpell().getCdCounter() /(spellbutton.get(i).getSpell().getCd()*10))));
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);

        }
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public Spell getSpell(int pos) {
        return spellbutton.get(pos).getSpell();
    }

    public void setSpell(int pos, Spell spell) {
        spellbutton.get(pos).setSpell(spell);
    }

    public ArrayList<SpellButton> getSpellButtons() {
        return spellbutton;
    }

    public void setSpellButtons(ArrayList<SpellButton> spells) {
        this.spellbutton = spellbutton;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void useMana(int mana)   {
        this.mana -= mana;
    }
}
