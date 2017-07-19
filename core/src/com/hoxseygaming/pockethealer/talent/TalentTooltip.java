package com.hoxseygaming.pockethealer.talent;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.hoxseygaming.pockethealer.Assets;

/**
 * Created by Hoxsey on 7/4/2017.
 */
public class TalentTooltip extends Actor {

    private TalentWindow parent;
    public Texture background;
    private Label.LabelStyle textStyle;
    private BitmapFont font;
    private Label title;
    private Label body;
    private Assets assets;
    public boolean isActive;
    public Table table;


    public TalentTooltip(TalentWindow talentWindow)  {
        parent = talentWindow;
        assets = parent.assets;
        table = new Table();


        isActive = false;
        setBounds(parent.getX() + (parent.getWidth()/2) - (351/2), parent.getY() + 40, 351,191);
        table.setBounds(getX()+20, getY()+getHeight()/2 -40, getWidth()-40, getHeight()-40);
        table.align(Align.left);
        create();
    }

    public void create()    {
        background = assets.getTexture(assets.toolTipFrame);
        textStyle = new Label.LabelStyle();
        font = assets.getFont(assets.talentTooltipFont);
        textStyle.font = font;

        title = new Label("",textStyle);
        title.setColor(Color.YELLOW);
        title.setWrap(true);
        title.setWidth(this.getWidth());


        body = new Label("",textStyle);
        body.setColor(Color.WHITE);
        body.setWrap(true);
        body.setWidth(this.getWidth());


        table.add(title).left();
        table.row();
        table.add(body).width(table.getWidth());
    }

    public void fire(Talent talent, int x, int y)    {
        title.setText(talent.name);
        body.setText(talent.description);
        isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(background, getX(), getY(), getWidth(), getHeight());
        table.draw(batch, parentAlpha);
        /*
        title.draw(batch, parentAlpha);
        body.draw(batch,parentAlpha);
        */
    }
}
