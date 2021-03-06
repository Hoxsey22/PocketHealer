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
import com.badlogic.gdx.utils.Align;
import com.hoxseygaming.pockethealer.Assets;
import com.hoxseygaming.pockethealer.Button;
import com.hoxseygaming.pockethealer.Player;
import com.hoxseygaming.pockethealer.PocketHealer;
import com.hoxseygaming.pockethealer.ShutterAnimation;
import com.hoxseygaming.pockethealer.Text;
import com.hoxseygaming.pockethealer.encounters.player.bars.SpellBar;
import com.hoxseygaming.pockethealer.encounters.spells.Spell;
import com.hoxseygaming.pockethealer.encounters.spells.SpellBook;

import static com.badlogic.gdx.utils.Align.top;

/**
 * Created by Hoxsey on 7/2/2017.
 */
public class SpellSelectionState extends State {

    private final Stage stage;
    private final Player player;
    private final Assets assets;
    private final Image background;
    private Table titleTable;
    private Table descriptionTable;
    private Text title;
    private Text spellDescriptionName;
    private Text spellDescription;
    private Text spellCost;
    private Text spellType;
    private Text spellCooldown;
    private final Button done;
    private Spell selectedSpell;
    private final SpellBook spellBook;
    private final SpellBar spellBar;
    private boolean isSpellSelected;
    private ShutterAnimation shutterAnimation;

    public SpellSelectionState(StateManager sm, Player player) {
        super(sm);
        assets = player.getAssets();

        this.player = player;

        spellBook = player.getSpellBook();
        spellBar = player.getSpellBar();

        done = new Button("DONE", false,assets);
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
        shutterAnimation = new ShutterAnimation(stage, assets, false);
        shutterAnimation.start();
    }

    private void createText()    {

        // setting up title
        titleTable = new Table();
        titleTable.setName("Title Table");

        title  = new Text("Spell Selection", 45, Color.SKY, true, assets);
        title.setWrap();
        title.setAlignment(top);

        titleTable.setBounds(spellBook.getLeft(), spellBook.getTop(), spellBook.getRight() - spellBook.getLeft(), 80);
        titleTable.top();
        titleTable.add(title.getLabel());

        stage.addActor(titleTable);

        // setting up the description of the selected spell
        descriptionTable = new Table();
        //descriptionTable.debug();
        descriptionTable.setBounds(spellBook.getLeft(), spellBook.getBottom() - 30 - 100, spellBook.getRight() - spellBook.getLeft(), 100);
        descriptionTable.top();

        spellDescriptionName = new Text("", 32, Color.BLACK, false, assets);

        spellDescription = new Text("", 24, Color.YELLOW, false, assets);
        spellDescription.setWrap();
        spellDescription.setAlignment(Align.center);

        spellCost = new Text("", 24, Color.WHITE, false, assets);
        spellCost.setWrap();
        spellCost.setAlignment(Align.center);

        spellType = new Text("", 24, Color.WHITE, false, assets);
        spellType.setWrap();
        spellType.setAlignment(Align.center);

        spellCooldown = new Text("", 24, Color.WHITE, false, assets);
        spellCooldown.setWrap();
        spellCooldown.setAlignment(Align.center);


        descriptionTable.add(spellDescriptionName.getLabel()).center();
        descriptionTable.row();
        descriptionTable.add(spellDescription.getLabel()).width(descriptionTable.getWidth()).expandX().center().padTop(5);
        descriptionTable.row();
        descriptionTable.add(spellCost.getLabel()).center();
        descriptionTable.row();
        descriptionTable.add(spellType.getLabel()).center();
        descriptionTable.row();
        descriptionTable.add(spellCooldown.getLabel()).center();

        stage.addActor(descriptionTable);
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
                    shutterAnimation = new ShutterAnimation(stage, assets, true, new Runnable() {
                        @Override
                        public void run() {
                            sm.set(new MapState(sm, player));
                        }
                    });
                    shutterAnimation.start();

                    return false;
                }
                // check if the user to selecting a spell from the spell book
                if(coords.y > spellBook.getBottom() - 10 && coords.y < spellBook.getTop()+10) {
                        Spell hit = spellBook.selectSpell(coords.x, coords.y);
                        if(hit != null) {
                            spellDescriptionName.setText(hit.getName());
                            spellDescription.setText(hit.getDescription());
                            spellCost.setText(100*(float)hit.getCost()/player.getMaxMana()+"% of base mana");
                            spellType.setText(hit.getSpellType());
                            spellCooldown.setText(hit.getCooldown()+" second cooldown");
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
        Gdx.gl.glClearColor(Color.BLACK.r,Color.BLACK.g,Color.BLACK.b,Color.BLACK.a);
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
