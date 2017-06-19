package com.hoxseygaming.pockethealer.reformat;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.SnapshotArray;
import com.hoxseygaming.pockethealer.players.Member;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Hoxsey on 6/16/2017.
 */
public class Raid extends Group {

    public ArrayList<RaidMember> children;

    public Raid()   {
        setName("Raid");
        // NEED TO CHANGE
        setBounds(0,0,0,0);
        children = new ArrayList<RaidMember>();

    }

    public void premade(int size)   {
        switch(size) {
            case 5:
                addTank(1);
                addHealer(1);
                addDps(3);
                break;
            case 10:
                addTank(2);
                addHealer(2);
                addDps(6);
                break;
            case 15:
                addTank(2);
                addHealer(3);
                addDps(10);
                break;
            case 20:
                addTank(2);
                addHealer(4);
                addDps(14);
                break;
        }


    }

    public void addTank(int amount)   {
        for(int i = 0; i < amount; i++) {
            children.add(new RaidMember(children.size(), "Tank"));
            addActor(children.get(children.size() - 1));
        }
    }

    public void addHealer(int amount)   {
        for(int i = 0; i < amount; i++) {
            children.add(new RaidMember(children.size(), "Healer"));
            addActor(children.get(children.size() - 1));
        }
    }

    public void addDps(int amount)   {
        for(int i = 0; i < amount; i++) {
            children.add(new RaidMember(children.size(), "Dps"));
            addActor(children.get(children.size() - 1));
        }
    }

    public RaidMember[] getRandomRaidMember(int amount) {
        RaidMember raidMembers [] = new RaidMember[amount];
        int counter = 0;
        ArrayList<RaidMember> temp = new ArrayList<RaidMember>();
        temp.addAll(children);
        Collections.shuffle(temp);
        for (int i = 0; i < temp.size(); i++) {
            if(counter != amount) {
                if (!temp.get(i).isDead()) {
                    raidMembers[counter] = temp.get(i);
                    counter++;
                }
            }
            else
                return raidMembers;
        }
        return  raidMembers;
    }

    public Actor getRandomRaidMember()  {
        ArrayList<RaidMember> temp = new ArrayList<RaidMember>();
        temp.addAll(children);
        Collections.shuffle(temp);
        for (int i = 0; i < temp.size(); i++) {
            if (!temp.get(i).isDead())
                return temp.get(i);
        }
        return temp.get(0);
    }

    public ArrayList<RaidMember> getRaidMembersWithLowestHp(int cap)    {
        ArrayList<RaidMember> lowest = new ArrayList<RaidMember>(cap);
        for (int i = 0; i < children.size(); i++)  {
            if(!children.get(i).isSelected()) {
                if (lowest.size() < cap) {
                    lowest.add(children.get(i));
                } else {
                    Collections.sort(lowest);
                    if (children.get(i).hp < lowest.get(0).getHp()) {
                        lowest.add(0, children.get(i));
                    }
                }
            }
        }
        return lowest;
    }

}
