package com.hoxseygaming.pockethealer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hoxsey on 2/17/2018.
 */

public class AudioManager {


    public static class AudioData implements Serializable {

        public float musicVolume;
        public float sfxVolume;

        public AudioData() {
            musicVolume = 0.5f;
            sfxVolume = 0.5f;
        }

        public void setData(float musicVolume, float sfxVolume){
            this.musicVolume = musicVolume;
            this.sfxVolume = sfxVolume;
        }
    }

    public static final float MIN_VOLUME = 0.0f;
    public static final float MAX_VOLUME = 1.0f;
    private static Preferences prefs = Gdx.app.getPreferences("settings");
    public static float musicVolume = 0.5f;
    public static float sfxVolume = 0.5f;
    private static Music music;
    private static ArrayList<Sound> soundEffects = new ArrayList<>();
    public static AudioData audioData = new AudioData();
    private static Assets assets;


    public static void loadAssets(Assets _assets) {
        assets = _assets;
    }

    /**
     * Plays the sfx. If the sound effect list contains the sfx then it will not add the sfx to the list.
     * @param sfx
     * @param looping
     */
    public static void playSFX(Sound sfx, boolean looping)   {
        if(sfx != null) {

            if(soundEffects.contains(sfx))   {
                stopSFX(sfx);
                if(looping)
                    sfx.loop(sfxVolume);
                else    {
                    sfx.play(sfxVolume);
                }
            }
            else {
                addSFX(sfx);
                if (looping)
                    sfx.loop(sfxVolume);
                else
                    sfx.play(sfxVolume);
            }
        }
    }

    /**
     * Stops the sfx.
     * @param sfx
     */
    public static void stopSFX(Sound sfx)    {
        if(sfx != null)    {
            sfx.stop();
        }
    }

    /**
     * Puases the sfx.
     * @param sfx
     */
    public static void pauseSFX(Sound sfx)   {
        if(sfx != null)    {
            sfx.pause();
        }
    }

    /**
     * Sets music to the new music object given and then plays music based on param values.
     * @param newMusic
     * @param looping
     */
    public static void playMusic(Music newMusic, boolean looping) {
        if(music != null) {
            setMusic(newMusic);
            music.setLooping(looping);
            music.setVolume(musicVolume);
            music.play();
        }
    }

    /**
     * Plays the music object if not null.
     */
    public static void playMusic() {
        if(music != null) {
            music.setLooping(true);
            music.setVolume(musicVolume);
            music.play();
        }
    }

    /**
     * Stops the music object if not null.
     */
    public static void stopMusic() {
        if(music != null)
            music.stop();
    }

    /**
     * Pauses music object if not null.
     */
    public static void pauseMusic()    {
        if(music != null)
            music.pause();
    }

    public static void setData(AudioData data)   {
        audioData.setData(data.musicVolume, data.sfxVolume);
        musicVolume = audioData.musicVolume;
        sfxVolume = audioData.sfxVolume;
    }

    public static AudioData getData() {
        audioData.setData(musicVolume, sfxVolume);
        return audioData;
    }

    /**
     * Disposes the music object.
     */
    public static void disposeMusic()  {
        try {
            music.dispose();
        } catch (NullPointerException e)  {
            System.out.println(e);
        }
    }

    /**
     * Disposes all Sound objects.
     */
    public static void disposeSFX()    {
        try {
            for(int i = 0; i < soundEffects.size(); i++)   {
                soundEffects.get(i).dispose();
            }
        }catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    /*
        Getters and Setters
     */

    /**
     * Sets Music objects.
     * @param newMusic
     */
    public static void setMusic(Music newMusic)  {
        music = newMusic;
    }

    /**
     * Gets current Music object.
     * @return
     */
    public static Music getMusic() {
        return music;
    }

    /**
     * Gets current list of SFX objects.
     * @return
     */
    public static ArrayList<Sound> getSoundEffects() {
        return soundEffects;
    }

    /**
     * Adds new Sound Object to SFX list.
     * @param sound
     */
    public static void addSFX(Sound sound) {
        soundEffects.add(sound);
    }

    /**
     * Gets music volume.
     * @return musicVolume
     */
    public static float getMusicVolume() {
        return musicVolume;
    }

    /**
     * Sets the music volume.
     * @param volume range 0.0f - 1.0f
     */
    public static void setMusicVolume(float volume) {
        musicVolume = volume;
    }

    /**
     * Gets the Sound effects volume.
     * @return sfxVolume
     */
    public static float getSfxVolume() {
        return sfxVolume;
    }

    /**
     * Sets the Sound effect's volume.
     * @param volume range 0.0f - 1.0f
     */
    public static void setSfxVolume(float volume) {
        sfxVolume = volume;
    }

    /**
     * Disposes all the music and sound effects.
     */
    public static void disposeAll()    {
        disposeMusic();
        disposeSFX();
    }
}
