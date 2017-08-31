package com.hoxseygaming.pockethealer.encounters.spells;

import com.badlogic.gdx.audio.Sound;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.encounters.entities.raid.RaidMember;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 6/18/2017.
 */
public class HolyNova extends Spell {

    public Sound sfx;

    public HolyNova(int index, Player player, Assets assets) {
        super(player, "Holy Nova", "", EffectType.HEALMULTIPLE, 30, 35, 3f, index,assets);
        image = assets.getTexture("barrier_icon.png");
        sfx = assets.getSound("sfx/barrier_sfx.mp3");
        setCriticalChance(30);
    }

    @Override
    public void castSpell() {
        if(isCastable())    {
            useMana();
            applySpell();
        }
    }

    public void applySpell()    {
        sfx.play(0.3f);
        startCooldownTimer();
        owner.getTarget().receiveHealing(output);
        ArrayList<RaidMember> targets = getOwner().raid.getRaidMembersWithLowestHp(3);
        for(int i = 0; i < targets.size(); i++)   {
            targets.get(i).receiveHealing(output,criticalChance.isCritical());
        }
    }
}
