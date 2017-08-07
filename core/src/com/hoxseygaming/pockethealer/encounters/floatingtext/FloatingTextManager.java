package com.hoxseygaming.pockethealer.encounters.floatingtext;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/29/2017.
 */
public class FloatingTextManager {

    private RaidMember owner;
    private ArrayList<FloatingText> floatingTexts;
    private FloatingText floatingText;
    private int idCounter;


    public FloatingTextManager(RaidMember owner)    {
        this.owner = owner;
        floatingTexts = new ArrayList<>();
        floatingText = new FloatingText(0,owner,0,0);
        idCounter = 0;

    }

    public void add(int damage, int type)   {
        floatingText.setId(idCounter);
        floatingText.setType(type);
        floatingText.setDamage(""+damage);

        floatingTexts.add(floatingText);
        floatingTexts.get(getIndex(idCounter)).startAnimation();
        idCounter++;
    }

    public void add(int damage, int type, boolean isCritical)   {
        floatingText.setId(idCounter);
        floatingText.setType(type);
        floatingText.setDamage(""+damage);

        floatingTexts.add(floatingText);
        if(isCritical)    {
            floatingTexts.get(getIndex(idCounter)).getFloatingText().setFontSize(32);
        }
        floatingTexts.get(getIndex(idCounter)).startAnimation();
        idCounter++;
    }

    public boolean remove(FloatingText floatingText)    {
        int removeIndex = getIndex(floatingText.getId());
        if(removeIndex != -1) {
            //floatingText.dispose();
            floatingTexts.remove(removeIndex);
            return true;
        }
        else {
            System.out.println("Index not found!");
            return false;
        }
    }

    public boolean removeInActiveText(FloatingText floatingText)    {
        if(!floatingText.isAnimating)
            return remove(floatingText);
        return false;
    }

    public int getIndex(int id)    {
        for (int i = 0; i < floatingTexts.size(); i++)  {
            if(floatingTexts.get(i).getId() == id)
                return i;
        }
        return -1;
    }

    public void clear() {
        floatingTexts.clear();
        idCounter = 0;
    }

    public void draw(Batch batch, float alpha)  {

        for (int i = 0; i < floatingTexts.size(); i++) {
            if(!removeInActiveText(floatingTexts.get(i)))
                floatingTexts.get(i).draw(batch, alpha);
        }
        
    }
}
