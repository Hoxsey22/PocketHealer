package com.hoxseygaming.pockethealer.talent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.PocketHealer;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 7/2/2017.
 */
public class TalentBook extends Group{

    public ArrayList<Talent> talents;
    public Talent continuousRenewal;
    public Talent lifeboom;
    public Talent perseverance;
    public Talent burstHealer;
    public Talent innerFocus;
    public Talent workTogether;
    public Assets assets;
    public int hSpacing;

    public TalentBook(Assets assets) {
        this.assets = assets;
        talents = new ArrayList<>();

        continuousRenewal = new Talent("Continuous Renewal", 1, "Heal will now refresh any renews on a target.");
        lifeboom = new Talent("Lifeboom",1,"The last tick of renew will do half the total healing.");
        perseverance = new Talent("Perseverance",1,"Every third time casting Heal will get 300% increase.");
        burstHealer = new Talent("Burst Healer",1,"Heal will now heal two targets, one with the lowest hp and current target.");
        innerFocus = new Talent("Inner Focus",1,"Receive mana regeneration every 3 seconds.");
        workTogether = new Talent("Work Together",1,"All healers will now heal the lowest hp raid member every second.");

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
        setImages();


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

    public void setImages() {
        continuousRenewal.setImage(assets.getTexture(assets.continuousRenewalIcon));
        lifeboom.setImage(assets.getTexture(assets.lifeboomIcon));
        perseverance.setImage(assets.getTexture(assets.perseveranceIcon));
        burstHealer.setImage(assets.getTexture(assets.burstHealerIcon));
        innerFocus.setImage(assets.getTexture(assets.innerFocusIcon));
        workTogether.setImage(assets.getTexture(assets.workTogetherIcon));
        for (int i = 0; i < talents.size(); i++)
            talents.get(i).selectedImage = assets.getTexture(assets.selectedTalent);
    }

    public void setPositions()  {
        talents.get(0).setPosition(25, PocketHealer.HEIGHT - 180);
        talents.get(1).setPosition(talents.get(0).getX() +talents.get(0).getWidth(), talents.get(0).getY());
        for(int i = 2; i < talents.size(); i = i * 2) {
                talents.get(i).setPosition(talents.get(i-2).getX(), talents.get(i-2).getY() - talents.get(i-2).getHeight());
                talents.get(i+1).setPosition(talents.get(i).getX() + talents.get(i).getWidth(), talents.get(i).getY());

                /*
                continuousRenewal.setPosition(25, PocketHealer.HEIGHT - 125);
                lifeboom.setPosition(continuousRenewal.bounds.x + hSpacing, continuousRenewal.bounds.y);

                perseverance.setPosition(continuousRenewal.bounds.x, continuousRenewal.bounds.y - 80);
                burstHealer.setPosition(perseverance.bounds.x + hSpacing, perseverance.bounds.y);

                innerFocus.setPosition(perseverance.bounds.x, perseverance.bounds.y - 80);
                workTogether.setPosition(innerFocus.bounds.x + hSpacing, innerFocus.bounds.y);
                */
        }
    }

    /*
    public void draw(Batch batch, float alpha)  {
        batch.begin();
        for (int i = 0; i < talents.size(); i++) {
            batch.draw(talents.get(i).image, talents.get(i).bounds.x, talents.get(i).bounds.y,
                    talents.get(i).bounds.width, talents.get(i).bounds.height);
            if(talents.get(i).isSelected())    {
                batch.draw(selectedTalent, talents.get(i).bounds.x, talents.get(i).bounds.y,
                        talents.get(i).bounds.width, talents.get(i).bounds.height);
            }
        }
        batch.end();
    }
    */
}
