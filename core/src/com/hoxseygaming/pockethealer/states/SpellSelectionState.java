package com.hoxseygaming.pockethealer.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Button;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.PocketHealer;
import com.hoxseygaming.pockethealer.Text;
import com.hoxseygaming.pockethealer.encounters.player.bars.SpellBar;
import com.hoxseygaming.pockethealer.encounters.spells.Spell;
import com.hoxseygaming.pockethealer.encounters.spells.SpellBook;

import static com.badlogic.gdx.utils.Align.top;

/**
 * Created by Hoxsey on 7/2/2017.
 */
public class SpellSelectionState extends State {

    public Stage stage;
    public Player player;
    public Assets assets;
    public Image background;
    public Table titleTable;
    public Table descriptionTable;
    public Text title;
    public Text spellDescriptionName;
    public Text spellDescription;
    public Button done;
    public Spell selectedSpell;
    public SpellBook spellBook;
    public SpellBar spellBar;
    public boolean isSpellSelected;

    public SpellSelectionState(StateManager sm, Player player) {
        super(sm);
        assets = player.getAssets();

        this.player = player;

        spellBook = player.getSpellBook();
        spellBar = player.getSpellBar();

        done = new Button("DONE", true,assets);
        done.setPosition((spellBook.getRight() - spellBook.getLeft())/2 + spellBook.getLeft()-done.getWidth()/2, spellBar.getY()+spellBar.getHeight()+ 10);

        background = new Image(assets.getTexture(assets.spellBG));
        background.setBounds(0,0,PocketHealer.WIDTH, PocketHealer.HEIGHT);
        background.setName("bg");

        stage = new Stage(viewport);

        stage.addActor(background);
        stage.addActor(done);
        stage.addActor(spellBook);
        stage.addActor(spellBar);

        createText();
    }

    public void createText()    {

        // setting up title
        titleTable = new Table();
        titleTable.setName("Title Table");

        title  = new Text("Spell Selection", 45, Color.SKY, true, assets);
        title.setWrap(true);
        title.setAlignment(top);

        titleTable.setBounds(spellBook.getLeft(), spellBook.getTop(), spellBook.getRight() - spellBook.getLeft(), 80);
        titleTable.top();
        titleTable.add(title.getLabel());

        stage.addActor(titleTable);

        // setting up the description of the selected spell
        descriptionTable = new Table();
        descriptionTable.setBounds(spellBook.getLeft(), spellBook.getBottom() - 20 - 100, spellBook.getRight() - spellBook.getLeft(), 100);
        descriptionTable.top();

        spellDescriptionName = new Text("", 32, Color.BLACK, false, assets);
        spellDescription = new Text("", 24, Color.SKY, false, assets);
        spellDescription.setWrap(true);

        descriptionTable.add(spellDescriptionName.getLabel()).center();
        descriptionTable.row();
        descriptionTable.add(spellDescription.getLabel()).width(descriptionTable.getWidth());

        stage.addActor(descriptionTable);
        stage.setDebugAll(true);
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
                Vector2 coords = stage.screenToStageCoordinates(new Vector2((float)screenX,(float)screenY));

                if(done.pressed(coords.x, coords.y))    {
                    player.save();
                    sm.set(new MapState(sm, player));
                    return false;
                }
                // check if the user to selecting a spell from the spell book
                if(coords.y > spellBook.getBottom() - 10 && coords.y < spellBook.getTop()+10) {
                        Spell hit = spellBook.selectSpell(coords.x, coords.y);
                        if(hit != null) {
                            spellDescriptionName.setText(hit.getName());
                            spellDescription.setText(hit.getDescription());
                            selectedSpell = hit;
                            selectedSpell.setPosition(coords.x-selectedSpell.getWidth()/2, coords.y-selectedSpell.getHeight()/2);
                            isSpellSelected = true;
                        }
                        return false;
                }


                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                if(isSpellSelected) {
                    spellBar.addSpell(selectedSpell);
                }
                isSpellSelected = false;
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                Vector2 coords = stage.screenToStageCoordinates(new Vector2((float)screenX,(float)screenY));

                if(isSpellSelected) {
                    selectedSpell.setPosition(coords.x-selectedSpell.getWidth()/2, coords.y-selectedSpell.getHeight()/2);
                }
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
        if(isSpellSelected)    {
            stage.getBatch().begin();
            selectedSpell.draw(stage.getBatch(),1f);
            stage.getBatch().end();
        }


    }

    @Override
    public void dispose() {

    }
}
