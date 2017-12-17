package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2.Apprentice;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1.BanditLeader;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3.BloodQueen;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1.GiantHornet;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1.Golem;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2.Hogger;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3.Hydra;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3.MotherSpider;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2.Proctor;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2.Sorcerer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1.Tiger;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2.WampusCat;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1.WildBoar;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3.ZombieHorde;

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
        setName("map frame "+page);
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

        //texture
        map = new Map(page, innerFrame,assets);
        addActor(map);

        table.setBounds(map.getImage().getX(), innerFrame.getY()+13, map.getImage().getWidth(),
                innerFrame.getHeight()- map.getImage().getHeight() - 30);
        table.align(Align.topLeft);

        talentButton = new Button("TALENTS", true, assets);
        talentButton.setPosition(innerFrame.getX(), innerFrame.getY() - talentButton.getHeight()-1);
        if(player.getTalentTree().getUnusedPoints() > 1)    {
            talentButton.setHighlight(true);
        } else {
            talentButton.setHighlight(false);
        }
        addActor(talentButton);

        startButton = new Button("START", true, assets);
        startButton.setPosition(talentButton.getX() + talentButton.getWidth() + 6, talentButton.getY());
        addActor(startButton);

        spellButton = new Button("SPELLS", true, assets);
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

    public void add(BossIcon bossIcon) {
        bossIcon.getBoss().setDefeated(player.getLevel() > bossIcon.getBoss().getId());
        map.add(bossIcon);
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

    public void setBoss(int index)   {

        switch (index) {
            case 2:
                add(new BossIcon(assets, new WildBoar(assets)));
                break;
            case 3:
                add(new BossIcon(assets, new Tiger(assets)));
                break;
            case 4:
                add(new BossIcon(assets, new GiantHornet(assets)));
                break;
            case 5:
                add(new BossIcon(assets, new Golem(assets)));
                break;
            case 6:
                add(new BossIcon(assets, new BanditLeader(assets)));
                break;
            case 7:
                add(new BossIcon(assets, new Hogger(assets)));
                break;
            case 8:
                add(new BossIcon(assets, new WampusCat(assets)));
                break;
            case 9:
                add(new BossIcon(assets, new Proctor(assets)));
                break;
            case 10:
                add(new BossIcon(assets, new Apprentice(assets)));
                break;
            case 11:
                add(new BossIcon(assets, new Sorcerer(assets)));
                break;
            case 12:
                add(new BossIcon(assets, new MotherSpider(assets)));
                break;
            case 13:
                add(new BossIcon(assets, new ZombieHorde(assets)));
                break;
            case 14:
                add(new BossIcon(assets, new BloodQueen(assets)));
                break;
            case 15:
                add(new BossIcon(assets, new Hydra(assets)));
                break;
        }
    }

    public void dispose()   {
        talentButton.dispose();
        spellButton.dispose();
        startButton.dispose();
        title.dispose();
        body.dispose();
    }
}
