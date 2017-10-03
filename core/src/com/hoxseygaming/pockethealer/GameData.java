package com.hoxseygaming.pockethealer;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.hoxseygaming.pockethealer.Player.PlayerData;

/**
 * Created by Hoxsey on 9/28/2017.
 */

public class GameData {

    private static final String SAVE_PATH = "data/saved_data.sav";

    public static boolean load(Player player)   {

        if(Gdx.files.internal(SAVE_PATH).exists()) {
            Json json = new Json();

            FileHandle fileHandle = new FileHandle(SAVE_PATH);

            player.setData(json.fromJson(PlayerData.class, fileHandle.readString()));

            System.out.println(player.getLevel());

            return true;
        }
        else {
            save(player);
            return false;
        }
    }

    public static boolean save(Player player)   {
        Json json = new Json();
        json.toJson(player.getData());
        System.out.println(json.prettyPrint(player.getData()));

        FileHandle fileHandle = new FileHandle(SAVE_PATH);
        fileHandle.writeString(json.toJson(player.getData()),false);


        return true;
    }



}
