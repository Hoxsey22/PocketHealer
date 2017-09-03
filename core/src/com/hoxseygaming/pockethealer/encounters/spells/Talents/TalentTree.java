package com.hoxseygaming.pockethealer.encounters.spells.Talents;

import com.hoxseygaming.pockethealer.Player;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 9/1/2017.
 */

public class TalentTree {

    public Player owner;
    public int unusedPoints;
    public int totalPoints;
    public ArrayList<Talent> talents;

    public TalentTree() {
        talents = new ArrayList<>();

    }

    public void createTalents()  {
        talents.add(new Talent(1, "Lifeboom", "After Renew expires, two more Renew will spawn."));
        talents.add(new Talent(2, "Healer Channel", "Healers in the raid will no longer deal damage, but will instead heal.", talents.get(talents.size()-1)));
        talents.add(new Talent(3, "Renewing Nova", "Holy Nova now put a Renew on each target that was healed.", talents.get(talents.size()-1)));
        talents.add(new Talent(4, "Aspect of the Druid","Renew now does more healing and faster ticks.", talents.get(talents.size()-1)));

        talents.add(new Talent(5,"Critical Healer","Increase the critical strike chance of all spells."));
        talents.add(new Talent(6, "Shield", "Barrier now reflects 50% of the damage absorbed.", talents.get(talents.size()-1)));
        talents.add(new Talent(7, "Discipline", "Barrier absorbs more damage. Smite does more healing and when critical," +
                        " it will place a small barrier on the target.",talents.get(talents.size()-1)));
        talents.add(new Talent(8,"Critical Healer II", "Any spells that are critical will place a barrier for 50% of the amount healed.",talents.get(talents.size()-1)));

        talents.add(new Talent(9,"Haste Build","All spell 0.5 seconds faster."));
        talents.add(new Talent(10, "Super Nova","Holy Nova now heals one additional target.", talents.get(talents.size()-1)));
        talents.add(new Talent(11,"Resurgence","All critical single target heals will now give mana back",talents.get(talents.size()-1)));
        talents.add(new Talent(12, "Holy Focus","Divine Hymn gives 15% mana back and the cooldown is reduced by 15 seconds.", talents.get(talents.size()-1)));
    }

    public void addPoint()  {
        unusedPoints++;
        totalPoints++;
    }

    public void usePoint(Talent talent)  {

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




}
