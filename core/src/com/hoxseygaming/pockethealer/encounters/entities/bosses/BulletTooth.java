package com.hoxseygaming.pockethealer.encounters.entities.bosses;

import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.encounters.entities.raid.Raid;

/**
 * Created by Hoxsey on 7/20/2017.
 */

public class BulletTooth extends Boss {

    public BulletTooth(String name, int maxHp, Assets assets) {
        super(name, maxHp, new Raid(15, assets), assets);
    }

    @Override
    public void start() {

    }
}
