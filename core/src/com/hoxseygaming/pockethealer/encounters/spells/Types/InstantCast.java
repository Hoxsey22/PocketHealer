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

    public Sound spellSFX;

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
        spellType = "Instant";
        this.spellSFX = spellSFX;
        this.numOfTargets = numOfTargets;
    }

    @Override
    public void castSpell() {
        if(isCastable())    {
            useMana();
            startCooldownTimer();
            AudioManager.playSFX(spellSFX, false);
            //spellSFX.play(0.3f);
            applySpell(getOwnerTarget());

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
