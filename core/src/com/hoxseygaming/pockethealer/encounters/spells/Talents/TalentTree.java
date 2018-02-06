package com.hoxseygaming.pockethealer.encounters.spells.Talents;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 9/1/2017.
 */

public class TalentTree extends Group{

    public static final String LIFEBOOM = "Lifeboom";
    public static final String HEALER_CHANNEL = "Healer Channel";
    public static final String RENEWING_NOVA = "Renewing Nova";
    public static final String AOD = "Aspect of the Druid";
    public static final String CRITICAL_HEALER = "Critical Healer";
    public static final String BARRIER_MASTER = "Barrier Master";
    public static final String DISCIPLINE = "Discipline";
    public static final String CRITICAL_HEALER_II = "Critical Healer II";
    public static final String HASTE_BUILD = "Haste Build";
    public static final String SUPER_NOVA = "Super Nova";
    public static final String MASTERING_HEALING = "Mastering Healing";
    public static final String HOLY_FOCUS = "Holy Focus";


    public Player owner;
    public ArrayList<Talent> talents;
    public int unusedPoints;
    public int totalPoints;
    public Assets assets;

    public TalentTree(Player player) {
        owner = player;
        talents = new ArrayList<>();
        unusedPoints = 0;
        totalPoints = 0;
        assets = owner.getAssets();
        createTalents();
        placeTalentPosition();
        //setBounds(spells.get(3).getX(), spells.get(3).getY(),(spells.get(11).getX() + spells.get(11).getWidth())-spells.get(3).getX(),
                //(spells.get(0).getX() +spells.get(0).getHeight())-spells.get(3).getY());
    }

    public TalentTree(Player player, int unusedPoints, int totalPoints) {
        owner = player;
        talents = new ArrayList<>();
        this.unusedPoints = unusedPoints;
        this.totalPoints = totalPoints;
        assets = owner.getAssets();
        createTalents();
        placeTalentPosition();
        //setBounds(spells.get(3).getX(), spells.get(3).getY(),(spells.get(11).getX() + spells.get(11).getWidth())-spells.get(3).getX(),
                //(spells.get(0).getX() +spells.get(0).getHeight())-spells.get(3).getY());
    }

    public void createTalents()  {
        talents.add(new Talent(this, 1, LIFEBOOM, "After Renew expires, half of renew's healing will heal the ally unit.", assets.getTexture(assets.lifeboomIcon), assets));
        talents.add(new Talent(this, 2, HEALER_CHANNEL, "Healers in the raid will no longer deal damage, but will instead heal.", talents.get(talents.size()-1),
                assets.getTexture(assets.workTogetherIcon), assets));
        talents.add(new Talent(this, 3, RENEWING_NOVA, "Holy Nova now put a Renew on each target that was healed.", talents.get(talents.size()-1),
                assets.getTexture(assets.renewingNovaIcon), assets));
        talents.add(new Talent(this, 4, AOD,"Renew now does more healing and faster ticks.", talents.get(talents.size()-1),
                assets.getTexture(assets.aodIcon), assets));

        talents.add(new Talent(this, 5,CRITICAL_HEALER,"Increase the critical strike chance of all spells.",
                assets.getTexture(assets.smiteIcon), assets));
        talents.add(new Talent(this, 6, BARRIER_MASTER, "The cooldown of Barrier is now 1.0 second and the cost is reduced to 15.", talents.get(talents.size()-1),
                assets.getTexture(assets.tankIcon), assets));
        talents.add(new Talent(this, 7, DISCIPLINE, "Barrier absorbs more damage. Smite does more healing and damage. Also when Smite is critical," +
                        " it will place a small barrier on the target.",talents.get(talents.size()-1), assets.getTexture(assets.disciplineIcon), assets));
        talents.add(new Talent(this, 8,CRITICAL_HEALER_II, "Any spells that are critical will place a barrier for 50% of the amount healed. Smite's barrier increase as well. Smite will now apply atonement.",
                talents.get(talents.size()-1), assets.getTexture(assets.criticalHealer2Icon), assets));

        talents.add(new Talent(this, 9,HASTE_BUILD,"All spell are 0.25 seconds faster.", assets.getTexture(assets.flashIcon), assets));
        talents.add(new Talent(this, 10, SUPER_NOVA,"Holy Nova and Prayer of Mending now heal one additional target.", talents.get(talents.size()-1),
                assets.getTexture(assets.superNovaIcon), assets));
        talents.add(new Talent(this, 11,HOLY_FOCUS,"Divine Hymn gives 15% mana back and Lightwell will be available",talents.get(talents.size()-1),
                assets.getTexture(assets.divineHymnIcon), assets));
        talents.add(new Talent(this, 12, MASTERING_HEALING,"All heal now have a 40% chance of increase it output based on missing health. In addition, Heal will now heal a second ally unit.",
                talents.get(talents.size()-1), assets.getTexture(assets.innerFocusIcon), assets));

        for(int i = 0; i < talents.size(); i++)   {
            addActor(talents.get(i));
        }


    }

    public void placeTalentPosition()   {

        for(int i = 0; i < talents.size(); i++)   {
            talents.get(i).setBounds(assets.talentPositions.get(i).x,assets.talentPositions.get(i).y, 75, 75);
        }

    }

    public Talent hit(float x, float y) {
        Actor hit = hit(x,y,false);
        if(hit != null)    {
            Talent talent = getTalent(hit.getName());
            return talent;
        }
        return null;
    }

    public Talent getTalent(String name)   {
        for(int i =0; i < talents.size(); i++)   {
            if(talents.get(i).getName().equalsIgnoreCase(name))    {
                return talents.get(i);
            }
        }
        System.out.println("Cannot find talent!");
        return null;
    }

    public boolean usePoint(Talent selectedTalent)  {
        if(isTalentSelectable(selectedTalent))    {
            selectedTalent.setSelected(true);
            unusedPoints--;
            return true;
        }
        return false;
    }

    private boolean isTalentSelectable(Talent talent)    {
        if(getUnusedPoints() < 1)    {
            //System.out.println("Not enough points.");
            return false;
        }
        if(talent.hasPreReq()) {
            if (!talent.getPreReq().isSelected()) {
               // System.out.println(talent.getPreReq().getName() + " needs to be selected before selecting " + talent.getName());
                return false;
            }
        }
        if(talent.getTotalPointRequirement() > getTotalPoints())    {
            //System.out.println("Not enough points in your talent tree.");
            return false;
        }
        return !talent.isSelected();
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void addPoint()  {
        unusedPoints++;
        totalPoints++;
    }

    public ArrayList<Talent> getTalents() {
        return talents;
    }

    public void setTalents(ArrayList<Talent> talents) {
        this.talents = talents;
    }

    public void loadTalents(ArrayList<String> talentNames)    {
        clearTalents();
        for(int i = 0; i < talentNames.size(); i++)   {
            getTalent(talentNames.get(i)).setSelected(true);
        }
        System.out.println("-- Talents loaded.");
    }

    public int getUnusedPoints() {
        return unusedPoints;
    }

    public void setUnusedPoints(int unusedPoints) {
        this.unusedPoints = unusedPoints;
    }

    public float getLeft() {
        return getSmallestX();
    }

    public float getBottom()    {
        return getSmallestY();
    }

    public float getRight() {
        return getLargestX() + talents.get(0).getWidth();
    }

    public float getTop()   {
        return getLargestY() + talents.get(0).getHeight();
    }

    private float getLargestY() {
        float largest = 0;
        for(int i = 0; i < talents.size(); i++)   {
            if(largest == 0)    {
                largest = talents.get(i).getY();
            }
            else if(largest < talents.get(i).getY())    {
                largest = talents.get(i).getY();
            }
        }
        return largest;
    }

    private float getSmallestY()    {
        float smallest = 0;
        for(int i = 0; i < talents.size(); i++)   {
            if(smallest == 0)    {
                smallest = talents.get(i).getY();
            }
            else if(smallest > talents.get(i).getY())    {
                smallest = talents.get(i).getY();
            }
        }
        return smallest;
    }

    private float getLargestX() {
        float largest = 0;
        for(int i = 0; i < talents.size(); i++)   {
            if(largest == 0)    {
                largest = talents.get(i).getX();
            }
            else if(largest < talents.get(i).getX())    {
                largest = talents.get(i).getX();
            }
        }
        return largest;
    }

    private float getSmallestX()    {
        float smallest = 0;
        for(int i = 0; i < talents.size(); i++)   {
            if(smallest == 0)    {
                smallest = talents.get(i).getX();
            }
            else if(smallest > talents.get(i).getX())    {
                smallest = talents.get(i).getX();
            }
        }
        return smallest;
    }

    /**
     * All talents will no longer be selected.
     */
    public void clearTalents()  {
        for (int i = 0; i <  talents.size(); i++)   {
            talents.get(i).setSelected(false);
        }
    }

    public void reset() {
        clearTalents();
        unusedPoints = totalPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public ArrayList<String> getData()  {
        ArrayList<String> talentData = new ArrayList<>();
        for(int i = 0; i < talents.size(); i++)   {
            if(talents.get(i).isSelected())    {
                talentData.add(talents.get(i).getName());
            }
        }
        return talentData;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for(int i = 0; i < talents.size(); i++)   {
            if(!isTalentSelectable(talents.get(i)) && !talents.get(i).isSelected()) {
                batch.draw(assets.getTexture(assets.shadowIcon), talents.get(i).getX(), talents.get(i).getY(),
                        talents.get(i).getWidth(), talents.get(i).getHeight());
            }
        }
    }
}
