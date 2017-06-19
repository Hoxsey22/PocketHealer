package com.hoxseygaming.pockethealer.players;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Hoxsey on 5/27/2017.
 */
public class Party {

    private ArrayList<RaidFrame> frames;
    public ArrayList<Member> party;
    public RaidFrame target;
    public int deathCount;
    public Timer damageTimer;


    public Party()  {
        party = new ArrayList<Member>();
        frames = new ArrayList<RaidFrame>();
        deathCount = 0;
        damageTimer = new Timer();
    }

    public void draw(SpriteBatch sb)  {
        for(int i = 0; i < party.size(); i++)
            frames.get(i).draw(sb);
    }

    public void setTarget(float x, float y)   {
        for (int i = 0; i < getSize(); i++) {
            if(frames.get(i).getFrame().contains(x,y)) {
                if(target != null)
                    target.unselected();
                target = frames.get(i);
                target.selected();
            }
        }
    }

    public void addPremadeSizeParty(int size)   {
        switch (size)   {
            case 5:
                addTank();
                addHealer();
                for (int i = 0; i < 3; i++)
                    addDPS();
                break;
            case 10:
                addTank();
                addTank();
                addHealer();
                addHealer();
                for (int i = 0; i < 6; i++)
                    addDPS();
                break;
            case 15:
                addTank();
                addTank();
                for (int i = 0; i < 3; i++)
                    addDPS();
                for (int i = 0; i < 10; i++)
                    addDPS();
                break;
            case 20:
                addTank();
                addTank();
                for (int i = 0; i < 4; i++)
                    addDPS();
                for (int i = 0; i < 14; i++)
                    addDPS();
                break;
        }
    }

    public void addDPS()    {
        party.add(new Member(getSize()+1,100, Member.DPS, 10));
        addRaidFrame(party.get(getSize()-1).getId());
    }

    public void addHealer() {
        party.add(new Member(getSize()+1,100, Member.HEALER, 2));
        addRaidFrame(party.get(getSize()-1).getId());
    }

    public void addTank()   {
        party.add(new Member(getSize()+1,200, Member.TANK, 5));
        addRaidFrame(party.get(getSize()-1).getId());
    }

    private void addRaidFrame(int id) {
        frames.add(new RaidFrame(party.get(id-1)));
    }

    public Member swapTank(Member curTank)    {
        if(curTank.getId() != getSize() && getMember(curTank.getId()).getRole() == Member.TANK && !getMember(curTank.getId()).isDead())
            return getMember(curTank.getId());
        else
            return getRandomMember();
    }

    public Member getRandomMember() {
        ArrayList<Member> temp = new ArrayList<Member>();
        temp.addAll(party);
        Collections.shuffle(temp);
        for (int i = 0; i < temp.size(); i++) {
            if (!temp.get(i).isDead())
                return getMember(temp.get(i).getId() - 1);
        }
        return getMember(0);
    }

    public Member[] findTwo()   {
        Member theTwo [] = new Member[2];
        int counter = 0;
        ArrayList<Member> temp = new ArrayList<Member>();
        temp.addAll(party);
        Collections.shuffle(temp);
        for (int i = 0; i < getSize(); i++) {
            if(counter != 2) {
                if (!temp.get(i).isDead()) {
                    theTwo[counter] = getMember(temp.get(i).getId() - 1);
                    counter++;
                }
            }
            else
                return theTwo;
        }
        return  theTwo;
    }

    public void removeMember(Member m)  {
        party.remove(m.getId()-1);
    }

    public Member nextMember(Member m)    {
        return party.remove(m.getId());
    }

    public Member getMember(int pos)  {
        return party.get(pos);
    }

    public  RaidFrame getMemberFrame(int pos)  {
        return frames.get(party.indexOf(party.get(pos)));
    }

    public int getSize()    {
        return party.size();
    }

    public ArrayList<Member> getMemberWithLowestHp(int cap)    {
        ArrayList<Member> lowest = new ArrayList<Member>(cap);
        for (int i = 0; i < getSize(); i++)  {
            if(lowest.size() < cap)    {
                lowest.add(getMember(i));
            }
            else{
                Collections.sort(lowest);
                if(getMember(i).getHp() < lowest.get(0).getHp())    {
                    lowest.add(0,getMember(i));
                }
            }
        }
        return lowest;
    }

    public ArrayList<Member> getMemberWithLowestHp(int cap, Member currentTarget)    {
        ArrayList<Member> lowest = new ArrayList<Member>(cap);
        for (int i = 0; i < getSize(); i++)  {
            if(getMember(i) != currentTarget) {
                if (lowest.size() < cap) {
                    lowest.add(getMember(i));
                } else {
                    Collections.sort(lowest);
                    if (getMember(i).getHp() < lowest.get(0).getHp()) {
                        lowest.add(0, getMember(i));
                    }
                }
            }
        }
        return lowest;
    }

}
