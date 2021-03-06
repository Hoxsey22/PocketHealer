package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.hoxseygaming.pockethealer.encounters.spells.StatusEffect.Debuff.Debuff;

/**
 * Created by Hoxsey on 7/20/2017.
 */

class DebuffIconButton extends ImageButton {

    private Assets assets;
    private String description;
    private String name;

    public DebuffIconButton(Debuff debuff, Assets assets)   {
        super(new TextureRegionDrawable(new TextureRegion(debuff.getIcon())));
        setName(debuff.getName());
        setBounds(0,0,50,50);

        this.assets = assets;

        description = debuff.getDescription();

    }

    public String getDescription()  {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
