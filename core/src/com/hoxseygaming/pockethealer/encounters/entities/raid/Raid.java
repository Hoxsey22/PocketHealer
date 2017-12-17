package com.hoxseygaming.pockethealer.encounters.entities.raid;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Timer;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.HealingTracker;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.mechanics.Mechanic;
import com.hoxseygaming.pockethealer.encounters.spells.Spell;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.Buff;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class Raid extends Group {

    public ArrayList<RaidMember> raidMembers;
    public ArrayList<RaidMember> healers;
    public Timer raidDamageTimer;
    private Assets assets;
    public boolean isRaidAlive;
    public HealingTracker healingTracker;

    public Raid(int size, Assets assets)   {
        //super();
        this.assets = assets;
        setName("Raid");
        raidMembers = new ArrayList<>();
        healers = new ArrayList<>();
        preMade(size);
        raidDamageTimer = new Timer();
        isRaidAlive = true;
        healingTracker = new HealingTracker();
    }

    public void start(final Boss t)   {

        final Boss target = t;
        final boolean healerChannel = target.getPlayer().talentTree.getTalent("Healer Channel").isSelected();
        raidDamageTimer.schedule(new Timer.Task() {

            @Override
            public void run() {

                for (int i = 0; i < raidMembers.size(); i++) {
                    if(!raidMembers.get(i).isDead()) {
                        if( raidMembers.get(i).getRole().equalsIgnoreCase("Healer") && healerChannel) {
                            getRaidMemberWithLowestHp().receiveHealing(raidMembers.get(i).damage,false);
                        }
                        else {
                            target.takeDamage(raidMembers.get(i).getDamage());
                        }
                    }
                }

            }
        },3f,1f);
    }

    public void stop()  {
        raidDamageTimer.stop();
        raidDamageTimer.clear();
    }

    public void preMade(int size)   {
        switch(size) {
            case 3:
                addTank(1);
                addHealer(1);
                addDps(1);
                break;
            case 6:
                addTank(1);
                addHealer(1);
                addDps(4);
                break;
            case 9:
                addTank(2);
                addHealer(2);
                addDps(5);
                break;
            case 12:
                addTank(2);
                addHealer(3);
                addDps(7);
                break;
            case 15:
                addTank(2);
                addHealer(4);
                addDps(9);
                break;
            case 18:
                addTank(2);
                addHealer(5);
                addDps(11);
                break;
        }
    }

    public void customRaid(int tanks, int healers, int dps)    {
        addTank(tanks);
        addHealer(healers);
        addDps(dps);
    }

    public void addTank(int amount)   {
        for(int i = 0; i < amount; i++) {
            raidMembers.add(new RaidMember(raidMembers.size(), "Tank", assets));
            raidMembers.get(raidMembers.size() - 1).setAssets(assets);
            addActor(raidMembers.get(raidMembers.size() - 1));
        }
    }

    public void addHealer(int amount)   {
        for(int i = 0; i < amount; i++) {
            raidMembers.add(new RaidMember(raidMembers.size(), "Healer", assets));
            healers.add(raidMembers.get(raidMembers.size()-1));
            addActor(raidMembers.get(raidMembers.size() - 1));
        }
    }

    public void addDps(int amount)   {
        for(int i = 0; i < amount; i++) {
            raidMembers.add(new RaidMember(raidMembers.size(), "Dps", assets));
            addActor(raidMembers.get(raidMembers.size() - 1));
        }
    }

    public RaidMember getRaidMember(int index)   {
        return raidMembers.get(index);
    }

    public ArrayList<RaidMember> getRandomRaidMember(int amount) {
        ArrayList<RaidMember> raidMembers = new ArrayList<>();
        int counter = 0;
        ArrayList<RaidMember> temp = new ArrayList<>();
        temp.addAll(this.raidMembers);
        Collections.shuffle(temp);
        for (int i = 0; i < temp.size(); i++) {
            if(counter != amount) {
                if (!temp.get(i).isDead()) {
                    raidMembers.add(temp.get(i));
                    counter++;
                }
            }
            else
                return raidMembers;
        }
        return  raidMembers;
    }

    public ArrayList<RaidMember> getRandomRaidMember(int amount, ArrayList<RaidMember> group) {
        ArrayList<RaidMember> randomMembers = new ArrayList<>();
        int counter = 0;
        Collections.shuffle(group);
        for (int i = 0; i < group.size(); i++) {
            if(counter != amount) {
                if (!group.get(i).isDead()) {
                    randomMembers.add(group.get(i));
                    counter++;
                }
            }
            else
                return randomMembers;
        }
        return  randomMembers;
    }

    public Actor getRandomRaidMember()  {
        ArrayList<RaidMember> temp = new ArrayList<RaidMember>();
        temp.addAll(raidMembers);
        Collections.shuffle(temp);
        for (int i = 0; i < temp.size(); i++) {
            if (!temp.get(i).isDead())
                return temp.get(i);
        }
        return temp.get(0);
    }

    public ArrayList<RaidMember> getRaidMembersWithLowestHp(int cap)    {
        ArrayList<RaidMember> lowest = new ArrayList<>();
        ArrayList<RaidMember> temp = new ArrayList<>();
        temp.addAll(raidMembers);

        Collections.sort(temp);
        int counter = 0;
        for(int i = 0; i < temp.size(); i++) {
            if (!temp.get(i).isSelected()) {
                lowest.add(temp.get(i));
                counter++;
                if(counter == cap)    {
                    return lowest;
                }
            }
        }
        return null;
    }

    public RaidMember getRaidMemberWithLowestHp()    {
        ArrayList<RaidMember> temp = new ArrayList<>();
        temp.addAll(raidMembers);
        Collections.sort(temp);

        for(int i = 0; i <  temp.size(); i++)   {
            if(!temp.get(i).isDead())    {
                return temp.get(i);
            }
        }
        return null;

    }

    public ArrayList<RaidMember> getRaidMembersWithLowestHp(int cap, RaidMember target)    {
        ArrayList<RaidMember> lowest = new ArrayList<>(cap);
        ArrayList<RaidMember> temp = new ArrayList<>();
        temp.addAll(raidMembers);

        Collections.sort(temp);
        int counter = 0;
        for(int i = 0; i < temp.size(); i++) {
            if (!temp.get(i).isSelected()) {
                lowest.add(temp.get(i));
                counter++;
                if(counter == cap)    {
                    return lowest;
                }
            }
        }
        return null;
    }

    public void receiveHealing(int output)    {
        for(int i= 0; i <  raidMembers.size(); i++)   {
            raidMembers.get(i).receiveHealing(output, false);
        }
    }

    public void takeDamage(int damage)    {
        for(int i= 0; i <  raidMembers.size(); i++)   {
            raidMembers.get(i).takeDamage(damage);
        }
    }

    public boolean tanksAlive() {

        for(int i = 0; i < raidMembers.size(); i++)   {
            if(raidMembers.get(i).getRole() == "tank" && !raidMembers.get(i).isDead());
            return true;
        }
        return false;
    }

    public int healersAlive() {
        int counter = 0;
        for(int i = 0; i < raidMembers.size(); i++)   {
            if(raidMembers.get(i).getRole() == "healer");
            counter++;
        }
        return counter;
    }

    public int dpsAlive() {
        int counter = 0;
        for(int i = 0; i < raidMembers.size(); i++)   {
            if(raidMembers.get(i).getRole() == "dps");
            counter++;
        }
        return counter;
    }

    public boolean isRaidDead() {
        for(int i = 0; i < raidMembers.size(); i++)   {
            if(!raidMembers.get(i).isDead())
                return false;
        }
        return true;
    }

    public void reset() {
        for(int i = 0; i < raidMembers.size(); i++)   {
            raidMembers.get(i).reset();
        }
    }

    public ArrayList<RaidMember> getDebuffLessRaidMembers(Mechanic.Debuff debuff)    {
        ArrayList<RaidMember> debuffLess = new ArrayList<>();
        for(int i = 0; i <  raidMembers.size(); i++)   {
            if(!raidMembers.get(i).containsEffects(debuff))    {
                debuffLess.add(raidMembers.get(i));
            }
        }
        return  debuffLess;
    }

    public ArrayList<RaidMember> getBuffLessRaidMembers(Spell.EffectType buff)    {
        ArrayList<RaidMember> buffLess = new ArrayList<>();
        for(int i = 0; i <  raidMembers.size(); i++)   {
            if(!raidMembers.get(i).containsEffects(buff))    {
                buffLess.add(raidMembers.get(i));
            }
        }
        return  buffLess;
    }

    public ArrayList<RaidMember> getBuffLessRaidMembers(Buff buff)    {
        ArrayList<RaidMember> buffLess = new ArrayList<>();
        for(int i = 0; i <  raidMembers.size(); i++)   {
            if(!raidMembers.get(i).getStatusEffectList().contains(buff.getName()))    {
                buffLess.add(raidMembers.get(i));
            }
        }
        return  buffLess;
    }

    public void loadHealingStats()   {
        for(int i = 0; i < raidMembers.size(); i++)   {
            healingTracker.addHealingTracker(raidMembers.get(i).getHealingTracker());
        }
    }

    public int getRaidDamage()  {
        int totalDamage = 0;
        for(int i = 0; i < raidMembers.size(); i++)   {
            totalDamage = totalDamage + raidMembers.get(i).getDamage();
        }
        return totalDamage;
    }

    public HealingTracker getHealingTracker()   {
        return healingTracker;
    }


}
