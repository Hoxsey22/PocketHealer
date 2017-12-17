package com.hoxseygaming.pockethealer.encounters.spells;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Periodical;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class Renew extends Periodical {

    private boolean isSelectedLifeboom;

    public ArrayList<Lifeboom> lifebooms;

    /**
     * @param index
     * @param player
     */
    public Renew(Player player, int index, Assets assets)  {
        super(player, "Renew2", "A small heal that is healed over time.", 0, EffectType.HEALOVERTIME,
                1,
                7,
                15,
                0.8f,
                10f,
                2f,
                assets.getSound(assets.hotSFX),
                index,
                assets);
        image = this.assets.getTexture(assets.renewIcon);
        lifebooms = new ArrayList<>();
        isSelectedLifeboom = false;
    }

    @Override
    public void checkLifeboom() {
        if(isSelectedLifeboom)    {
            ArrayList<RaidMember> randRM = owner.getRaid().getRandomRaidMember(3, owner.getRaid().getBuffLessRaidMembers(EffectType.LBHEAL));

            for (int i = 0; i < randRM.size(); i++) {
                if(!isTargetLifeboom(randRM.get(i)))    {
                    lifebooms.add(new Lifeboom(owner,this, assets));
                    lifebooms.get(lifebooms.size()-1).startDurationTimer(randRM.get(i));
                }
            }

        }
    }

    public boolean isTargetLifeboom(RaidMember t)  {
        for(int i = 0; i < lifebooms.size(); i++)   {
            if(t.getId() == lifebooms.get(i).target.getId())    {
                lifebooms.get(i).startDurationTimer(t);
                return true;
            }
        }
        return false;

    }

    @Override
    public void checkTalents() {

        resetDefault();

        if(owner.getTalentTree().getTalent(TalentTree.LIFEBOOM).isSelected())    {
            isSelectedLifeboom = true;
        }
        if(owner.getTalentTree().getTalent(TalentTree.AOD).isSelected())    {
            output = 10;
            duration = 12;
            speed = 1.5f;
            setCostPercentage(1.5f);
        }
        if(owner.getTalentTree().getTalent(TalentTree.CRITICAL_HEALER).isSelected())    {
            setCriticalChance(30);
        }
        if(owner.getTalentTree().getTalent(TalentTree.HASTE_BUILD).isSelected())    {
            speed = speed - 0.25f;
        }


    }

    public void resetDefault()  {
        isSelectedLifeboom = false;

        numOfTargets = MIN_NUM_OF_TARGETS;
        output = MIN_OUTPUT;
        cost = MIN_COST;
        cooldown = MIN_COOLDOWN;
        duration = MIN_DURATION;
        speed = MIN_SPEED;
    }

    @Override
    public void stop() {
        super.stop();
        for(int i = 0; i < lifebooms.size(); i++)   {
            lifebooms.get(i).stop();
            lifebooms.get(i).clear();
        }
        lifebooms.clear();
    }


}
