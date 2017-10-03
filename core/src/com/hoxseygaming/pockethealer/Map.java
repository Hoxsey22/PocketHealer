package com.hoxseygaming.pockethealer;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 7/30/2017.
 */

public class Map extends Group {

    public Image image;
    public ArrayList<BossIcon> selects;
    public int page;
    public Assets assets;

    public Map(int page, Image frame, Assets assets)    {
        setName("image");
        this.page = page;
        this.assets = assets;
        selects = new ArrayList<>();
        setDebug(true);

        image = new Image(assets.getTexture(assets.maps.get(page-1)));
        image.setPosition(frame.getX()+15, frame.getY()+frame.getHeight()- image.getHeight()-12);
        //setBounds(image.getX(), image.getY(), image.getWidth(), image.getHeight());
        //addActor(image);
    }

    public void add(BossIcon bossIcon)   {
        BossIcon biTemp = bossIcon;
        biTemp.setPosition(image.getX()+ assets.bossIconPosition.get(bossIcon.getBoss().getId()-2).x-25,
                image.getY()+image.getHeight() + assets.bossIconPosition.get(bossIcon.getBoss().getId()-2).y-25);
        addActor(biTemp);
        selects.add(biTemp);
    }

    public BossIcon hit(float x, float y)   {
        BossIcon hit = (BossIcon) hit(x,y, false);

        if(hit != null)    {
            return hit;
        }
        return null;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        image.draw(batch, parentAlpha);
        super.draw(batch, parentAlpha);
    }
}
