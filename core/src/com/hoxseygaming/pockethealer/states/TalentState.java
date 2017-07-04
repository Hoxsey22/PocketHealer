package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.PocketHealer;
import com.hoxseygaming.pockethealer.talent.Talent;
import com.hoxseygaming.pockethealer.talent.TalentBook;
import com.hoxseygaming.pockethealer.talent.TalentTooltip;

/**
 * Created by Hoxsey on 7/2/2017.
 */
public class TalentState extends State {

    public Image background;
    public Stage stage;
    public Player player;
    public Assets assets;
    public TalentBook talentBook;
    public TalentTooltip talentTooltip;
    public Image title;

    public TalentState(StateManager sm, Assets assets, Player player) {
        super(sm);
        this.assets = assets;

        title = new Image(assets.getTexture(assets.talentStateTitle));
        title.setBounds(PocketHealer.WIDTH/2 - (100), PocketHealer.HEIGHT -100, 200,100);

        background = new Image(assets.getTexture(assets.battleBg2));
        background.setSize(PocketHealer.WIDTH, PocketHealer.HEIGHT);

        stage = new Stage(new FitViewport(PocketHealer.WIDTH, PocketHealer.HEIGHT));

        this.player = player;

        talentBook = new TalentBook(assets);
        talentTooltip = new TalentTooltip(assets);

        stage.addActor(background);
        stage.addActor(title);
        stage.addActor(talentBook);
        stage.addActor(talentTooltip);
    }

    @Override
    protected void handleInput() {
        Gdx.input.setInputProcessor(stage/*new InputProcessor() {
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
                Talent selectedTalent = (Talent) talentBook.hit(coord.x,coord.y, false);
                if(selectedTalent != null)  {
                    System.out.println("Talent selected: "+selectedTalent.name);
                    System.out.println("Selected talent's partner is : "+selectedTalent.partner.name);
                    selectedTalent.selected();
                }
                System.out.println("Is Talent selected? -"+selectedTalent.isSelected());
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
        }*/);

    }

    public void handleTooltip() {
        talentBook.addListener( new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //Vector2 coord = stage.screenToStageCoordinates(new Vector2(x,y));
                Talent hoverOverTalent = (Talent) talentBook.hit(x,y, false);
                talentTooltip.fire(hoverOverTalent,(int)x,(int)y);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                talentTooltip.setLocation(-100,-100);
                talentTooltip.setActive(false);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Talent selectedTalent = (Talent) talentBook.hit(x,y, false);
                if(selectedTalent != null)  {
                    System.out.println("Talent selected: "+selectedTalent.name);
                    System.out.println("Selected talent's partner is : "+selectedTalent.partner.name);
                    selectedTalent.selected();
                }
                System.out.println("Is Talent selected? -"+selectedTalent.isSelected());

                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void update(float dt) {
        handleInput();
        handleTooltip();
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(Color.TAN.r,Color.TAN.g,Color.TAN.b,Color.TAN.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        update(Gdx.graphics.getDeltaTime());
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose() {

    }
}
