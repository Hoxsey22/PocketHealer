package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.Boss;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1.BanditLeader;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1.GiantHornet;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1.Golem;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1.Tiger;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage1.WildBoar;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2.Apprentice;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2.Hogger;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2.Proctor;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2.Sorcerer;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage2.WampusCat;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3.BloodQueen;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3.DeathDragon;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3.Hydra;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3.MotherSpider;
import com.hoxseygaming.pockethealer.encounters.entities.bosses.stage3.ZombieHorde;

import java.util.ArrayList;

/**
 * Created by Hoxsey on 7/30/2017.
 */

public class MapFrame extends Group {

    public Image bgFrame;
    public Image innerFrame;
    public Map map;
    public TextButton talentButton;
    public TextButton startButton;
    public TextButton spellButton;
    public TextButton infoButton;
    public InfoFrame infoFrame;
    public ArrayList<BossIcon> bossIconsList;
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

        bossIconsList = new ArrayList<>();

        infoFrame = new InfoFrame(assets);
        //infoFrame.debug();

        infoButton = new TextButton("INFO", assets.getSkin(), "small_button");
        createInfoButtonListener();
        //infoButton.setName("INFO");

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

        createFont();

        table.row();
        table.add(infoButton).bottom().padBottom(10);
        //infoButton.setVisible(false);
    }

    public void changePage(int page)    {
        this.page = page;
        title.setText("");
        body.setText("");
        map.changeMap(page);
    }

    public void showInfoButton()    {
        //table.add(infoButton).bottom().center().padBottom(10);
        infoButton.setVisible(true);
    }

    public void disableInfoButton() {
        infoButton.setVisible(false);
        //table.removeActor(infoButton);
        //infoButton.remove();
    }

    private void createInfoButtonListener() {
        infoButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getParent().addActor(infoFrame);
                System.out.println("-----Info Frame hit");
            }
        });
    }

    public void createFont()    {

        title = new Text("", 32, Color.YELLOW, false, assets);
        //title.setFontSize(32);
        //title.setFontColor(Color.YELLOW);
        title.setWrap(true);
        title.setAlignment(Align.center);
        title.debug();

        table.add(title.getLabel()).expandX().fillY();
        table.row();

        body = new Text("",24, Color.WHITE, false, assets);
        //body.setFontSize(24);
        //body.setFontColor(Color.WHITE);
        body.setWrap(true);

        body.debug();

        table.add(body.getLabel()).width(table.getWidth()).expandX().expandY();

        addActor(table);

    }

    public void add(Boss boss) {
        boss.setDefeated(player.getLevel() > boss.getId());
        BossIcon bossIcon = new BossIcon(assets,boss);
        bossIconsList.add(bossIcon);
        map.add(bossIcon);
    }

  /*  public TextButton hitButton(float x, float y)   {
        Actor hit = hit(x,y, false);

        if(hit != null)    {

            switch (hit.getName())   {
                case "TALENTS":
                    return talentButton;
                case "START":
                    return startButton;
                case "SPELLS":
                    return spellButton;
                case "INFO":
                    return infoButton;
            }

        }
        return null;
    }*/

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
                add(new WildBoar(assets));
                break;
            case 3:
                add(new Tiger(assets));
                break;
            case 4:
                add(new GiantHornet(assets));
                break;
            case 5:
                add(new Golem(assets));
                break;
            case 6:
                add(new BanditLeader(assets));
                break;
            case 7:
                add(new Hogger(assets));
                break;
            case 8:
                add(new WampusCat(assets));
                break;
            case 9:
                add(new Proctor(assets));
                break;
            case 10:
                add(new Apprentice(assets));
                break;
            case 11:
                add(new Sorcerer(assets));
                break;
            case 12:
                add(new MotherSpider(assets));
                break;
            case 13:
                add(new ZombieHorde(assets));
                break;
            case 14:
                add(new BloodQueen(assets));
                break;
            case 15:
                add(new Hydra(assets));
                break;
            case 16:
                add(new DeathDragon(assets));
                break;
        }
    }

    public void clearBossList() {

        //remove boss icons from their parent
        for(int i = 0; i < bossIconsList.size(); i++)   {
            bossIconsList.get(i).remove();
        }
        bossIconsList.clear();

    }

    @Override
    public boolean remove() {
        clearBossList();
        disableInfoButton();
        return super.remove();
    }

    public void dispose()   {
        /*
        talentButton.dispose();
        spellButton.dispose();
        startButton.dispose();
        */
    }
}
