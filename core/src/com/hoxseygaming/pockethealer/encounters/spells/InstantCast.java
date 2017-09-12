package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.audio.Sound;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 8/31/2017.
 */

public abstract class InstantCast extends Spell {

    public Sound spellSFX;
    public int numOfTargets;
    public int MIN_NUM_OF_TARGETS;
    public ArrayList<RaidMember> targets;

    /**
     *
     * @param player
     * @param name
     * @param description
     * @param effectType
     * @param output
     * @param cost
     * @param cooldown
     * @param spellSFX
     * @param index
     * @param assets
     */
    public InstantCast(Player player, String name, String description, EffectType effectType,
                       int numOfTargets, int output, int cost, float cooldown, Sound spellSFX, int index, Assets assets) {
        super(player, name, description, effectType, output, cost, cooldown, index, assets);
        this.spellSFX = spellSFX;
        this.numOfTargets = numOfTargets;
        MIN_NUM_OF_TARGETS = numOfTargets;
    }

    @Override
    public void castSpell() {
        if(isCastable())    {
            useMana();
            startCooldownTimer();
            spellSFX.play(0.3f);
            applySpell(getTarget());

        }

    }

    @Override
    public void applySpell(RaidMember target)    {
        target.receiveHealing(output, criticalChance.isCritical());
        if(numOfTargets > 1) {
            getRandomTargets();
            for (int i = 0; i < targets.size(); i++) {
                targets.get(i).receiveHealing(output, criticalChance.isCritical());
            }
        }
    }

    @Override
    public void stop() {

    }

    public void getRandomTargets()  {
        targets = getOwner().raid.getRaidMembersWithLowestHp(numOfTargets);
    }

    public Sound getSpellSFX() {
        return spellSFX;
    }

    public void setSpellSFX(Sound spellSFX) {
        this.spellSFX = spellSFX;
    }
}
