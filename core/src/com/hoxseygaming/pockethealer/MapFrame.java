package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * Created by Hoxsey on 7/30/2017.
 */

public class MapFrame extends Group {

    public Image bgFrame;
    public Image innerFrame;
    public Map map;
    public Button talentButton;
    public Button startButton;
    public Button spellButton;
    public Table table;
    public Text title;
    public Text body;
    public int page;
    public Player player;
    public Assets assets;

    public MapFrame(Player player, int page, Assets assets)    {
        this.page = page;
        this.assets = assets;
        table = new Table();
        this.player = player;
        table.setName("lowerTable");
        setName("Map "+page);
        create();
    }

    public void create()    {
        bgFrame = new Image(assets.getTexture(assets.mapOuterFrame));
        bgFrame.setName("bg");
        bgFrame.setPosition(0,0);
        addActor(bgFrame);

        innerFrame = new Image(assets.getTexture(assets.mapInnerFrame));
        innerFrame.setName("inner frame");
        innerFrame.setPosition(bgFrame.getX() + bgFrame.getWidth()/2 - innerFrame.getWidth()/2,
                bgFrame.getY() + bgFrame.getHeight() - innerFrame.getHeight() - 20);
        addActor(innerFrame);

        //image
        map = new Map(page, innerFrame,assets);
        addActor(map);

        table.setBounds(map.getImage().getX(), innerFrame.getY()+13, map.getImage().getWidth(),
                innerFrame.getHeight()- map.getImage().getHeight() - 30);
        table.align(Align.topLeft);

        talentButton = new Button("TALENTS", assets);
        talentButton.setPosition(innerFrame.getX(), innerFrame.getY() - talentButton.getHeight()-1);
        if(player.getTalentTree().getUnusedPoints() > 1)    {
            talentButton.setHighlight(true);
        } else {
            talentButton.setHighlight(false);
        }
        addActor(talentButton);

        startButton = new Button("START", assets);
        startButton.setPosition(talentButton.getX() + talentButton.getWidth() + 6, talentButton.getY());
        addActor(startButton);

        spellButton = new Button("SPELLS", assets);
        spellButton.setPosition(startButton.getX() + startButton.getWidth() + 6, startButton.getY());
        addActor(spellButton);

        createFont();
    }

    public void createFont()    {

        title = new Text("", 32, Color.YELLOW, false, assets);
        //title.setFontSize(32);
        //title.setFontColor(Color.YELLOW);
        title.setWrap(true);
        title.setAlignment(Align.center);

        table.add(title.getLabel());
        table.row();

        body = new Text("",24, Color.WHITE, false, assets);
        //body.setFontSize(24);
        //body.setFontColor(Color.WHITE);
        body.setWrap(true);

        table.add(body.getLabel()).width(table.getWidth());

        addActor(table);

    }

    public Button hitButton(float x, float y)   {
        Actor hit = hit(x,y, false);

        if(hit != null)    {

            switch (hit.getName())   {
                case "TALENTS":
                    return talentButton;
                case "START":
                    return startButton;
                case "SPELLS":
                    return spellButton;
            }

        }
        return null;
    }

    public void setTitle(String newTitle)  {
        title.setText(newTitle);
    }

    public void setBody(String newBody)  {
        body.setText(newBody);
    }

    public Map getMap() {
        return map;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void dispose()   {
        talentButton.dispose();
        spellButton.dispose();
        startButton.dispose();
        title.dispose();
        body.dispose();
    }
}
