package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Button;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.PocketHealer;
import com.hoxseygaming.pockethealer.Text;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.Talent;
import com.hoxseygaming.pockethealer.encounters.spells.Talents.TalentTree;

import static com.badlogic.gdx.utils.Align.center;
import static com.badlogic.gdx.utils.Align.top;

/**
 * Created by Hoxsey on 7/2/2017.
 */
public class TalentState extends State {

    public Stage stage;
    public Player player;
    public Assets assets;
    public Table lowerTable;
    public Table topTable;
    public Text talentTreeTitle;
    public Text title;
    public Text body;
    public TalentTree talentTree;
    public Image background;
    public Button select;
    public Button done;
    public Talent selectedTalent;
    public Text pointTracker;

    public TalentState(StateManager sm, Player player) {
        super(sm);
        assets = player.getAssets();

        this.player = player;

        talentTree = player.talentTree;
        talentTree.setName("Talent Tree");

        pointTracker = new Text("POINTS:",24,Color.WHITE,false, assets);
        pointTracker.setName("Point tracker");
        //pointTracker.setPosition(talentTree.getRight()-talentTree.getLeft()-pointTracker.getWidth()/2, talentTree.getTop() + 20 );

        select = new Button("SELECT", assets);
        select.setPosition(talentTree.getLeft(), 50);

        done = new Button("DONE", assets);
        done.setPosition(talentTree.getRight() - done.getWidth(), 50);

        talentTreeTitle = new Text("Talent Tree", 45, Color.SKY, true, assets);
        talentTreeTitle.setName("Talent Tree Title");
        //talentTreeTitle.setPosition(talentTree.getRight()-talentTree.getLeft()-talentTreeTitle.getWidth()/2, 730);

        background = new Image(assets.getTexture(assets.talentBg));
        background.setBounds(0,0,PocketHealer.WIDTH, PocketHealer.HEIGHT);
        background.setName("bg");

        stage = new Stage(viewport);
        stage.addActor(background);
        //stage.addActor(talentTreeTitle.getLabel());
        stage.addActor(talentTree);
        //stage.addActor(pointTracker.getLabel());
        stage.addActor(select);
        stage.addActor(done);
        //stage.setDebugAll(true);
        createText();
    }

    public void createText()    {
        lowerTable = new Table();
        lowerTable.setName("Lower Table");

        title  = new Text("", 24, Color.BLACK, false, assets);
        body  = new Text("", 16, Color.SKY, false, assets);
        body.setWrap(true);

        title.setWrap(true);
        title.setAlignment(top);

        lowerTable.setBounds(talentTree.getLeft(), talentTree.getButtom() - 20 - 100, talentTree.getRight() - talentTree.getLeft(), 100);
        lowerTable.top();
        lowerTable.add(title.getLabel());
        lowerTable.row();
        lowerTable.add(body.getLabel()).width(lowerTable.getWidth());
        stage.addActor(lowerTable);

        topTable = new Table();
        topTable.setName("Top Table");

        talentTreeTitle.setWrap(true);
        talentTreeTitle.setAlignment(center);

        pointTracker.setAlignment(center);
        topTable.setBounds(talentTree.getLeft(), talentTree.getTop(),talentTree.getRight()-talentTree.getLeft(), 90);
        topTable.top();
        topTable.add(talentTreeTitle.getLabel());
        topTable.row();
        topTable.add(pointTracker.getLabel()).width(topTable.getWidth());
        stage.addActor(topTable);





    }

    @Override
    protected void handleInput() {
        Gdx.input.setInputProcessor( new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector2 coord = stage.screenToStageCoordinates(new Vector2((float)screenX,(float)screenY));
                if(coord.y > talentTree.getButtom() - 10) {
                    Talent hit = talentTree.hit(coord.x, coord.y);
                    if (hit != null) {
                        selectedTalent = hit;
                        title.setText(hit.getName());
                        body.setText(hit.getDescription());
                    }
                    return false;
                }
                else    {
                    Actor hit = stage.hit(coord.x, coord.y, false);
                    if(hit != null) {
                        switch (hit.getName())  {
                            case "SELECT":
                                talentTree.usePoint(selectedTalent);
                                break;
                            case "DONE":
                                sm.set(new MapState(sm, player));
                                break;
                        }
                    }


                }




                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });


    }

    @Override
    public void update(float dt) {
        handleInput();
        pointTracker.setText("POINTS: "+talentTree.getUnusedPoints());
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(Color.WHITE.r,Color.WHITE.g,Color.WHITE.b,Color.WHITE.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        update(Gdx.graphics.getDeltaTime());

        sb.setProjectionMatrix(stage.getBatch().getProjectionMatrix());

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();


    }

    @Override
    public void dispose() {

    }
}
