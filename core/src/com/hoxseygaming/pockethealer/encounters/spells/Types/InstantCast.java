package com.hoxseygaming.pockethealer.encounters.spells.Types;

import com.badlogic.gdx.audio.Sound;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.AudioManager;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;
import com.hoxseygaming.pockethealer.encounters.spells.Spell;

/**
 * Created by Hoxsey on 8/31/2017.
 */

public abstract class InstantCast extends Spell {

    protected Sound spellSFX;

    /**
     *
     * @param player
     * @param name
     * @param description
     * @param output
     * @param costPercentage
     * @param cooldown
     * @param spellSFX
     * @param assets
     */
    public InstantCast(Player player, String name, String description, int levelRequirement,
                       int numOfTargets, int output, float costPercentage, float cooldown, Sound spellSFX, Assets assets) {
        super(player, name, description,levelRequirement, output, costPercentage, cooldown, assets);
        setSpellType("Instant");
        this.spellSFX = spellSFX;
        setNumOfTargets(numOfTargets);
    }

    @Override
    public void castSpell() {
        if(isCastable())    {
            useMana();
            startCooldownTimer();
            AudioManager.playSFX(spellSFX, false);
            applySpell(getOwnerTarget());
        }

    }

    @Override
    public void applySpell(RaidMember target)    {
        target.receiveHealing(getOutput(), getCriticalChance().isCritical());
        if(getNumOfTargets() > 1) {
            getRandomTargets();
            for (int i = 0; i < getTargets().size(); i++) {
                getTargets().get(i).receiveHealing(getOutput(), getCriticalChance().isCritical());
            }
        }
    }

    @Override
    public void stop() {

    }

    public void getRandomTargets() {
        setTargets(getOwner().getRaid().getRaidMembersWithLowestHp(getNumOfTargets()));
    }

    public Sound getSpellSFX() {
        return spellSFX;
    }

    public void setSpellSFX(Sound spellSFX) {
        this.spellSFX = spellSFX;
    }

}
