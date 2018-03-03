package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by Hoxsey on 3/2/2018.
 */

public class WindowFrame extends Table {

    private WindowFrameStyle windowFrameStyle;
    public boolean isHidden = true;

    public WindowFrame(Skin skin)    {
        this(skin.get(WindowFrameStyle.class));
        setSkin(skin);
    }

    public WindowFrame(WindowFrameStyle windowFrameStyle) {
        this.windowFrameStyle = windowFrameStyle;
        init();

    }

    /**
     * Initializes bg, stage bg position, size and table alignment.
     */
    private void init() {
        // sets the current wfs bg to the tables(this) bg;
        setBackground(windowFrameStyle.background);
        // sets the position to the button of the screen
        // default size of the table
        setSize(200,200);
        // defaults the widgets to the top left of the table
        this.top().left();

    }

    /**
     * The stage bg and this will be added to the stage in order to start displaying the window frame.
     * @param stage The stage of which the window frame will be displayed.
     */
    public void show(Stage stage)  {
        if(stage != null)   {
            isHidden = false;
            pack();
            setPosition(stage.getWidth()/2 - getWidth()/2, stage.getHeight()/2 - getHeight()/2);
            stage.addActor(this);
        }
    }

    /**
     * Both the stage bg and window frame, remove themselves from the stage in order to stop displaying.
     */
    public void hide()  {
        isHidden = true;
        remove();
    }

    /**
     * Sets the background of the table (window)
     * @param background
     */
    public void setBackground(Image background) {
        setBackground(background.getDrawable());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(windowFrameStyle.stageBackground != null)    {
            windowFrameStyle.stageBackground.draw(batch,0,0, PocketHealer.WIDTH, PocketHealer.HEIGHT);
        }
        super.draw(batch, parentAlpha);
    }

    /**
     * This is the style for the WindowFrame class
     */
    public static class WindowFrameStyle   {

        public Drawable background;
        public Drawable stageBackground;

        public WindowFrameStyle()   {
        }

        public WindowFrameStyle (Drawable background, Drawable stageBackground) {
            this.background = background;
            this.stageBackground = stageBackground;
        }

        public WindowFrameStyle(WindowFrameStyle windowFrameStyle)   {
            this.background = windowFrameStyle.background;
            this.stageBackground = windowFrameStyle.stageBackground;
        }
    }
}

