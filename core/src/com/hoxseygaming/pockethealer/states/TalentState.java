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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.PocketHealer;
import com.hoxseygaming.pockethealer.talent.Talent;
import com.hoxseygaming.pockethealer.talent.TalentBook;
import com.hoxseygaming.pockethealer.talent.TalentWindow;

/**
 * Created by Hoxsey on 7/2/2017.
 */
public class TalentState extends State {

    public Stage stage;
    public Player player;
    public Assets assets;
    public TalentBook talentBook;
    public TalentWindow talentWindow;
    public Texture background;

    public TalentState(StateManager sm, Player player) {
        super(sm);
        assets = player.getAssets();
        this.player = player;


        background = assets.getTexture(assets.talentStateBg);

        stage = new Stage(viewport);

        talentBook = new TalentBook(player);

        talentWindow = new TalentWindow(talentBook,assets);
        stage.addActor(talentWindow);
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
                System.out.println("[ x: "+coord.x+" y: "+coord.y+"]" );
                if(coord.y > 70) {
                    Talent selectedTalent = talentWindow.hit(coord.x, coord.y);
                    if (selectedTalent != null) {
                        System.out.println("Talent selected: " + selectedTalent.name);
                        System.out.println("Selected talent's partner is : " + selectedTalent.partner.name);
                        selectedTalent.selected();
                        talentWindow.tooltip.fire(selectedTalent,(int)coord.x,(int)coord.y);
                        System.out.println("Is Talent selected? -" + selectedTalent.isSelected());
                    }
                }
                else    {
                    Actor done = talentWindow.hit(coord.x,coord.y,false);
                    if(done != null) {
                        if (done.getName().equals("done")) {
                            player.setTalentBook(talentBook);
                            sm.push(new MapState(sm,player));
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

    public void handleTooltip() {
        talentBook.addListener( new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                System.out.println("[ x: "+x+" y: "+y+"]" );
                if(y > 70) {
                    System.out.println("********");
                    Talent selectedTalent = (Talent) talentWindow.hit(x, y, false);
                    if (selectedTalent != null) {
                        System.out.println("Talent selected: " + selectedTalent.name);
                        System.out.println("Selected talent's partner is : " + selectedTalent.partner.name);
                        talentWindow.tooltip.fire(selectedTalent,(int)x,(int)y);
                        selectedTalent.selected();
                        System.out.println("Is Talent selected? -" + selectedTalent.isSelected());
                    }
                }
                else    {
                    Actor done = talentWindow.hit(x,y,false);
                    if(done != null) {
                        if (done.getName().equals("done")) {
                            player.setTalentBook(talentBook);
                            sm.push(new MapState(sm,player));
                        }
                    }
                }


                return true;
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                //Vector2 coord = stage.screenToStageCoordinates(new Vector2(x,y));
                Talent hoverOverTalent = (Talent) talentWindow.hit(x,y, false);
                if(hoverOverTalent != null)
                    talentWindow.tooltip.fire(hoverOverTalent,(int)x,(int)y);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                talentWindow.tooltip.setActive(false);
            }


        });
    }

    @Override
    public void update(float dt) {
        handleInput();
       // handleTooltip();
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(Color.TAN.r,Color.TAN.g,Color.TAN.b,Color.TAN.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        update(Gdx.graphics.getDeltaTime());

        sb.setProjectionMatrix(stage.getBatch().getProjectionMatrix());
        sb.begin();
            sb.draw(background, 0,0,PocketHealer.WIDTH, PocketHealer.HEIGHT);
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();


    }

    @Override
    public void dispose() {

    }
}
