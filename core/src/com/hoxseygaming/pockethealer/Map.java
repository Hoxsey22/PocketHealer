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
    public Image frame;
    public ArrayList<BossIcon> bossPoints;
    public int page;
    public Assets assets;

    public Map(int page, Image frame, Assets assets)    {
        setName("image");

        this.assets = assets;
        this.frame = frame;
        bossPoints = new ArrayList<>();
        setDebug(true);
        changeMap(page);
    }

    public void changeMap(int page)    {
        this.page = page;

        if(image != null) {
            image.clear();
        }

        image = new Image(assets.getTexture(assets.maps.get(page-1)));
        image.setBounds(frame.getX()+15, frame.getY()+frame.getHeight()- 388-12, 407, 388);

    }

    public void add(BossIcon bossIcon)   {
        BossIcon biTemp = bossIcon;
        biTemp.setPosition(image.getX()+ assets.bossIconPosition.get(bossIcon.getBoss().getId()-2).x,
                image.getY() + assets.bossIconPosition.get(bossIcon.getBoss().getId()-2).y);
        addActor(biTemp);
        bossPoints.add(biTemp);
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
