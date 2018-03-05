package com.hoxseygaming.pockethealer;

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

    public float musicVolume = 0.5f;
    public float sfxVolume = 0.5f;
    public Music music;
    private ArrayList<Sound> soundEffects;
    public AudioData audioData;
    private Assets assets;

    public AudioManager()   {
        soundEffects = new ArrayList<>();
        audioData = new AudioData();
    }

    public AudioManager(AudioData audioData)   {
        this.musicVolume = audioData.musicVolume;
        this.sfxVolume = audioData.sfxVolume;
        soundEffects = new ArrayList<>();
    }


    public void loadAssets(Assets _assets) {
        assets = _assets;
    }

    /**
     * Plays the sfx. If the sound effect list contains the sfx then it will not add the sfx to the list.
     * @param sfx
     * @param looping
     */
    public  void playSFX(Sound sfx, boolean looping)   {
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
    public void stopSFX(Sound sfx)    {
        if(sfx != null)    {
            sfx.stop();
        }
    }

    /**
     * Puases the sfx.
     * @param sfx
     */
    public void pauseSFX(Sound sfx)   {
        if(sfx != null)    {
            sfx.pause();
        }
    }

    public void updateSFXVolume(float newsfxVolume)   {
        sfxVolume = newsfxVolume;
        /*for(int i = 0; i < soundEffects.size(); i++)   {
            if(soundEffects.get(i) != null){
                soundEffects.get(i).setVolume(sfxVolume,soundEffects.get(i));
            }
        }*/
    }

    /**
     * Sets music to the new music object given and then plays music based on param values.
     * @param newMusic
     * @param looping
     */
    public void playMusic(Music newMusic, boolean looping) {
        if(music == null) {
            music = newMusic;
        }
        else    {
            disposeMusic();
            music = newMusic;
        }
        music.setLooping(looping);
        music.setVolume(musicVolume);
        music.play();
    }

    /**
     * Plays the music object if not null.
     */
    public void playMusic() {
        if(music != null) {
            music.setLooping(true);
            music.setVolume(musicVolume);
            music.play();
        }
    }

    /**
     * Stops the music object if not null.
     */
    public Music stopMusic() {
        if(music != null)
            music.stop();
        return music;
    }

    /**
     * Pauses music object if not null.
     */
    public void pauseMusic()    {
        if(music != null)
            music.pause();
    }

    public void updateMusicVolume(float newVolume) {
        musicVolume = newVolume;
        if(music != null && music.isPlaying()) {
            music.setVolume(newVolume);
        }
    }




    /**
     * Disposes the music object.
     */
    public void disposeMusic()  {
        if(music != null) {
            music.stop();
            music.dispose();
        }
        System.out.println("Music has been disposed.");
    }

    /**
     * Disposes all Sound objects.
     */
    public void disposeSFX()    {
        for(int i = 0; i < soundEffects.size(); i++)   {
            if(soundEffects.get(i) != null) {
                soundEffects.get(i).stop();
                soundEffects.get(i).dispose();
            }
        }
        System.out.println("All SFX have been disposed.");
    }

    /*
        Getters and Setters
     */

    /**
     * Sets Music objects.
     * @param newMusic
     */
    public void setMusic(Music newMusic)  {
        music = newMusic;
    }

    /**
     * Gets current Music object.
     * @return
     */
    public Music getMusic() {
        return music;
    }

    /**
     * Gets current list of SFX objects.
     * @return
     */
    public ArrayList<Sound> getSoundEffects() {
        return soundEffects;
    }

    /**
     * Adds new Sound Object to SFX list.
     * @param sound
     */
    public void addSFX(Sound sound) {
        soundEffects.add(sound);
    }

    /**
     * Gets music volume.
     * @return musicVolume
     */
    public float getMusicVolume() {
        return musicVolume;
    }

    /**
     * Sets the music volume.
     * @param volume range 0.0f - 1.0f
     */
    public void setMusicVolume(float volume) {
        musicVolume = volume;
    }

    /**
     * Gets the Sound effects volume.
     * @return sfxVolume
     */
    public float getSfxVolume() {
        return sfxVolume;
    }

    /**
     * Sets the Sound effect's volume.
     * @param volume range 0.0f - 1.0f
     */
    public void setSfxVolume(float volume) {
        sfxVolume = volume;
    }


    public void setData(AudioData data)   {
        audioData.setData(data.musicVolume, data.sfxVolume);
        musicVolume = audioData.musicVolume;
        sfxVolume = audioData.sfxVolume;
    }

    public AudioData getData() {
        audioData.setData(musicVolume, sfxVolume);
        return audioData;
    }

    /**
     * Disposes all the music and sound effects.
     */
    public void disposeAll()    {
        disposeMusic();
        disposeSFX();
    }
}
