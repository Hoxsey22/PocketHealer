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

    private Image bgFrame;
    private Image innerFrame;
    private Map map;
    private TextButton infoButton;
    private InfoFrame infoFrame;
    private ArrayList<BossIcon> bossIconsList;
    private Table table;
    private Text title;
    private Text body;
    private int page;
    private Player player;
    private Assets assets;

    public MapFrame(Player player, int page, Assets assets)    {
        setName("map frame "+page);
        this.setPage(page);
        this.setAssets(assets);
        setTable(new Table());
        this.setPlayer(player);
        getTable().setName("lowerTable");
        setName("Map "+page);
        create();
    }

    public void create()    {
        setBgFrame(new Image(getAssets().getTexture(getAssets().mapOuterFrame)));
        getBgFrame().setName("bg");
        getBgFrame().setPosition(0,0);
        addActor(getBgFrame());

        bossIconsList = new ArrayList<>();

        setInfoFrame(new InfoFrame(getAssets()));

        setInfoButton(new TextButton("INFO", getAssets().getSkin(), "small_button"));
        createInfoButtonListener();

        setInnerFrame(new Image(getAssets().getTexture(getAssets().mapInnerFrame)));
        getInnerFrame().setName("inner frame");
        getInnerFrame().setPosition(getBgFrame().getX() + getBgFrame().getWidth()/2 - getInnerFrame().getWidth()/2,
                getBgFrame().getY() + getBgFrame().getHeight() - getInnerFrame().getHeight() - 20);
        addActor(getInnerFrame());

        //texture
        setMap(new Map(getPage(), getInnerFrame(), getAssets()));
        addActor(getMap());

        getTable().setBounds(getMap().getImage().getX(), getInnerFrame().getY()+13, getMap().getImage().getWidth(),
                getInnerFrame().getHeight()- getMap().getImage().getHeight() - 30);
        getTable().align(Align.topLeft);

        createFont();

        getTable().row();
        getTable().add(getInfoButton()).bottom().padBottom(10);
    }

    public void changePage(int page)    {
        this.setPage(page);
        getTitle().setText("");
        getBody().setText("");
        getMap().changeMap(page);
    }

    public void showInfoButton()    {
        getInfoButton().setVisible(true);
    }

    public void disableInfoButton() {
        getInfoButton().setVisible(false);
    }

    private void createInfoButtonListener() {
        getInfoButton().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getParent().addActor(getInfoFrame());
                System.out.println("-----Info Frame hit");
            }
        });
    }

    public void createFont()    {

        setTitle(new Text("", 32, Color.YELLOW, false, getAssets()));
        getTitle().setWrap(true);
        getTitle().setAlignment(Align.center);
        getTitle().debug();

        getTable().add(getTitle().getLabel()).expandX().fillY();
        getTable().row();

        setBody(new Text("",24, Color.WHITE, false, getAssets()));
        getBody().setWrap(true);

        getBody().debug();

        getTable().add(getBody().getLabel()).width(getTable().getWidth()).expandX().expandY();

        addActor(getTable());

    }

    public void add(Boss boss) {
        boss.setDefeated(getPlayer().getLevel() > boss.getId());
        BossIcon bossIcon = new BossIcon(getAssets(),boss);
        getBossIconsList().add(bossIcon);
        getMap().add(bossIcon);
    }

    public void setTitle(String newTitle)  {
        getTitle().setText(newTitle);
    }

    public void setBody(String newBody)  {
        getBody().setText(newBody);
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
                add(new WildBoar(getAssets()));
                break;
            case 3:
                add(new Tiger(getAssets()));
                break;
            case 4:
                add(new GiantHornet(getAssets()));
                break;
            case 5:
                add(new Golem(getAssets()));
                break;
            case 6:
                add(new BanditLeader(getAssets()));
                break;
            case 7:
                add(new Hogger(getAssets()));
                break;
            case 8:
                add(new WampusCat(getAssets()));
                break;
            case 9:
                add(new Proctor(getAssets()));
                break;
            case 10:
                add(new Apprentice(getAssets()));
                break;
            case 11:
                add(new Sorcerer(getAssets()));
                break;
            case 12:
                add(new MotherSpider(getAssets()));
                break;
            case 13:
                add(new ZombieHorde(getAssets()));
                break;
            case 14:
                add(new BloodQueen(getAssets()));
                break;
            case 15:
                add(new Hydra(getAssets()));
                break;
            case 16:
                add(new DeathDragon(getAssets()));
                break;
        }
    }

    public void clearBossList() {

        //remove boss icons from their parent
        for(int i = 0; i < getBossIconsList().size(); i++)   {
            getBossIconsList().get(i).remove();
        }
        getBossIconsList().clear();

    }

    @Override
    public boolean remove() {
        clearBossList();
        disableInfoButton();
        return super.remove();
    }

    public void dispose()   {
    }

    public Image getBgFrame() {
        return bgFrame;
    }

    public void setBgFrame(Image bgFrame) {
        this.bgFrame = bgFrame;
    }

    public Image getInnerFrame() {
        return innerFrame;
    }

    public void setInnerFrame(Image innerFrame) {
        this.innerFrame = innerFrame;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public TextButton getInfoButton() {
        return infoButton;
    }

    public void setInfoButton(TextButton infoButton) {
        this.infoButton = infoButton;
    }

    public InfoFrame getInfoFrame() {
        return infoFrame;
    }

    public void setInfoFrame(InfoFrame infoFrame) {
        this.infoFrame = infoFrame;
    }

    public ArrayList<BossIcon> getBossIconsList() {
        return bossIconsList;
    }

    public void setBossIconsList(ArrayList<BossIcon> bossIconsList) {
        this.bossIconsList = bossIconsList;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Text getTitle() {
        return title;
    }

    public void setTitle(Text title) {
        this.title = title;
    }

    public Text getBody() {
        return body;
    }

    public void setBody(Text body) {
        this.body = body;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }
}
