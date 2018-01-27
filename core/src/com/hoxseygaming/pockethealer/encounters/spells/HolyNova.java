package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.audio.Sound;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Buff.RenewingNovaEffect;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;
import com.hoxseygaming.pockethealer.encounters.spells.Types.Castable;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class HolyNova extends Castable {

    public boolean isSelectedCriticalHealerII;
    public boolean isSelectedRenewingNova;
    public int numOfTargets;
    public ArrayList<RaidMember> targets;

    public Sound sfx;
    public ArrayList<Lifeboom> lifebooms;
    public ArrayList<Barrier> barriers;

    public HolyNova(Player player, int index, Assets assets) {
        super(player, "Holy Nova", "Heals multiple targets with the lowest health. Great for getting the raid healed up.",
                3, 2f, EffectType.HEALMULTIPLE, 25, 4.5f, 1f,assets.getSound(assets.healSFX), index,assets);
        image = assets.getTexture(assets.holyNovaIcon);
        sfx = assets.getSound(assets.hotSFX);
        lifebooms = new ArrayList<>();
        barriers = new ArrayList<>();
        numOfTargets = 3;
    }

    @Override
    public void checkTalents() {
        resetDefault();

        if(owner.getTalentTree().getTalent(TalentTree.RENEWING_NOVA).isSelected())    {
            isSelectedRenewingNova = true;
        }

        if(owner.getTalentTree().getTalent(TalentTree.CRITICAL_HEALER).isSelected())    {
            setCriticalChance(30);
        }

        if(owner.getTalentTree().getTalent(TalentTree.CRITICAL_HEALER_II).isSelected())    {
            isSelectedCriticalHealerII = true;
        }
        if(owner.getTalentTree().getTalent(TalentTree.SUPER_NOVA).isSelected())    {
            numOfTargets = 4;
        }

    }

    @Override
    public void applySpell(RaidMember tar)    {

        // main tar
        isCriticalHealerII(tar);
        System.out.println("checking renew");
        isRenewingNova(tar);

        if(numOfTargets > 1) {
            getRandomTargets();
            for (int i = 0; i < targets.size(); i++) {
                isCriticalHealerII(targets.get(i));
                isRenewingNova(targets.get(i));
            }
        }
    }

    /**
     * @param target
     * @description Checks and applies critical healer talent if not it just heals the target
     */
    public boolean isCriticalHealerII(RaidMember target)   {
        if(isSelectedCriticalHealerII) {
            if (criticalChance.isCritical()) {
                barriers.add(new Barrier(owner, (int) (output / 2f), 0, assets));
                barriers.get(barriers.size() - 1).applySpell(target);
                target.receiveHealing(output, true);
                return true;
            }
            else    {
                target.receiveHealing(output, false);
                return true;
            }
        } else    {
            target.receiveHealing(output, criticalChance.isCritical());
            return false;
        }
    }

    /**
     * @param target
     * @description Checks and applies renewing nova talent if not nothing.
     */
    public boolean isRenewingNova(RaidMember target) {
        if(isSelectedRenewingNova)    {
            target.addStatusEffect(new RenewingNovaEffect(owner));
            /*
            System.out.println("lb size: "+lifebooms.size());
            for(int i  = 0; i < lifebooms.size(); i++)   {
                System.out.println("lb target: "+lifebooms.get(i).getTarget().getId()+" vs target: "+target.getId());
                if(lifebooms.get(i).target.getId() == target.getId())    {
                    lifebooms.get(i).startDurationTimer(target);
                    return true;
                }
            }

            lifebooms.add(new Lifeboom(owner, assets));
            lifebooms.get(lifebooms.size()-1).startDurationTimer(target);

            return true;
            */
        }

        return false;
    }

    public void resetDefault()  {
        isSelectedRenewingNova = false;
        setCriticalChance(MIN_CRITICAL);
        isSelectedCriticalHealerII = false;
        numOfTargets = 3;
    }

    @Override
    public void stop() {
        super.stop();
        for(int i = 0; i < lifebooms.size(); i++)   {
            lifebooms.get(i).stop();
            lifebooms.get(i).clear();
        }
        lifebooms.clear();
        barriers.removeAll(barriers);
    }

    public void getRandomTargets()  {
        targets = getOwner().raid.getRaidMembersWithLowestHp(numOfTargets);
    }
}
