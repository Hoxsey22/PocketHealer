package com.hoxseygaming.pockethealer;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;
import com.hoxseygaming.pockethealer.Player.PlayerData;

/**
 * Created by Hoxsey on 9/28/2017.
 */

public class GameData {

    private static Preferences prefs = Gdx.app.getPreferences("save");

    public static boolean load(Player player)   {

        if(prefs.contains("save"))  {
            Json json = new Json();
            player.setData(json.fromJson(PlayerData.class, prefs.getString("save")));
            System.out.println("save point found!");
            return true;
        }
        else {
            save(player);
            load(player);
            return false;
        }
    }

    public static boolean save(Player player)   {
        Json json = new Json();
        json.toJson(player.getData());
        System.out.println(json.prettyPrint(player.getData()));

        prefs.putString("save", json.toJson(player.getData()));
        prefs.flush();

        System.out.println("save successful!");
        return true;
    }

    public static boolean loadAudioSettings()   {

        if(prefs.contains("audio_settings"))  {
            System.out.println("save point found!");
            Json json = new Json();
            AudioManager.setData(json.fromJson(AudioManager.AudioData.class, prefs.getString("audio_settings")));
            return true;
        }
        else {
            saveAudioSettings();
            return loadAudioSettings();
        }
    }

    public static boolean saveAudioSettings()   {
        Json json = new Json();
        json.toJson(AudioManager.getData());
        System.out.println(json.prettyPrint(AudioManager.getData()));

        prefs.putString("audio_settings", json.toJson(AudioManager.getData()));
        prefs.flush();

        System.out.println("save successful!");
        return true;
    }

    public static void remove(String dataName)    {
        prefs.remove(dataName);
    }

    public static boolean doesDataExist(String dataName)    {
        return prefs.contains(dataName);
    }



}
