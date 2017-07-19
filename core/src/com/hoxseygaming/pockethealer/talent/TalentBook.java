package com.hoxseygaming.pockethealer.talent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.PocketHealer;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 7/2/2017.
 */
public class TalentBook extends Group{

    public Player owner;
    public ArrayList<Talent> talents;
    public Talent continuousRenewal;
    public Talent lifeboom;
    public Talent perseverance;
    public Talent burstHealer;
    public Talent innerFocus;
    public Talent workTogether;
    public Assets assets;
    public int hSpacing;

    public TalentBook(Player owner) {
        this.owner = owner;
        assets = owner.getAssets();
        talents = new ArrayList<>();

        continuousRenewal = new Talent(owner,"Continuous Renewal", 1, "Heal will now refresh any renews on a target.");
        lifeboom = new Talent(owner,"Lifeboom",1,"The last tick of renew will do the total healing.");
        perseverance = new Talent(owner,"Perseverance",1,"Every third Heal will get be 2x the normal value.");
        burstHealer = new Talent(owner,"Burst Healer",1,"Heal will now heal two targets, one with the lowest hp and current target.");
        innerFocus = new Talent(owner,"Inner Focus",1,"Receive mana regeneration every 3 seconds.");
        workTogether = new Talent(owner,"Work Together",1,"All healers will now heal the lowest hp raid member every second.");

        continuousRenewal.setPartner(lifeboom);
        perseverance.setPartner(burstHealer);
        innerFocus.setPartner(workTogether);

        addTalent(continuousRenewal);
        addTalent(lifeboom);
        addTalent(perseverance);
        addTalent(burstHealer);
        addTalent(innerFocus);
        addTalent(workTogether);

        hSpacing = (int)continuousRenewal.getWidth();

        setPositions();

    }

    public Talent getTalent(String name)   {
        for(int i = 0; i < talents.size(); i++)   {
            if(name.equals(talents.get(i).name))    {
                return talents.get(i);
            }
        }
        return null;
    }

    public void selectTalent(String name)   {
        switch (name)   {
            case "Continuous Renewal":
                continuousRenewal.selected();
                break;
            case "Lifeboom":
                lifeboom.selected();
                break;
            case "Perseverance":
                perseverance.selected();
                break;
            case "Burst Healer":
                burstHealer.selected();
                break;
            case "Inner Focus":
                innerFocus.selected();
                break;
            case "Work Together":
                workTogether.selected();
                break;
        }
    }

    public void addTalent(Talent talent) {
        talents.add(talent);
        addActor(talent);
    }

    public void setPositions()  {
        talents.get(0).setPosition(25, PocketHealer.HEIGHT - 180);
        talents.get(1).setPosition(talents.get(0).getX() +talents.get(0).getWidth(), talents.get(0).getY());
        for(int i = 2; i < talents.size(); i = i * 2) {
                talents.get(i).setPosition(talents.get(i-2).getX(), talents.get(i-2).getY() - talents.get(i-2).getHeight());
                talents.get(i+1).setPosition(talents.get(i).getX() + talents.get(i).getWidth() + 10, talents.get(i).getY());
        }
    }
}
