package main.external;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Main
 *
 * @author Dafydd-Rhys Maund (2003900)
 */
public class Audio {

    private static float music = 50f;
    private static float effects = 50f;
    private static float musicVolume = -50f + (music * 0.50f);
    private static float effectsVolume = -50f + (music * 0.50f);
    private static final File clickPath = new File(System.getProperty("user.dir") + "\\src\\resources\\audio\\menu\\click.wav");
    private static final File musicPath = new File(System.getProperty("user.dir") + "\\src\\resources\\audio\\menu\\music.wav");
    private static final String settingsPath = System.getProperty("user.dir") + "\\src\\resources\\config\\settings.txt";
    private static Clip musicClip = null;

    public static void playMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);

        musicClip = AudioSystem.getClip();
        musicClip.open(audio);
        ((FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(musicVolume);
        musicClip.start();
        musicClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void playGameEffect(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (effectsVolume != -50f) {
            File effectPath = new File(path);
            AudioInputStream audio = AudioSystem.getAudioInputStream(effectPath);
            Clip clickEffect = AudioSystem.getClip();
            clickEffect.open(audio);
            ((FloatControl) clickEffect.getControl(FloatControl.Type.MASTER_GAIN)).setValue(effectsVolume);
            clickEffect.start();
        }
    }

    public static void clickEffect() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        if (effectsVolume != -50f) {
            AudioInputStream audio = AudioSystem.getAudioInputStream(clickPath);
            Clip clickEffect = AudioSystem.getClip();
            clickEffect.open(audio);
            ((FloatControl) clickEffect.getControl(FloatControl.Type.MASTER_GAIN)).setValue(effectsVolume);
            clickEffect.start();
        }
    }

    public static void setMusic(float volume) throws IOException {
        Audio.music = volume;
        musicVolume = -50f + (music * 0.50f);
        ((FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(musicVolume);
        writeValues();
    }

    public static float getMusic() {
        return music;
    }

    public static void setEffects(float volume) throws IOException {
        Audio.effects = volume;
        effectsVolume = -50f + (effects * 0.50f);
        writeValues();
    }

    public static float getEffects() {
        return effects;
    }

    public static void getValues() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(settingsPath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(":");
            if (Objects.equals(split[0], "musicVolume")) {
                music = Float.parseFloat(split[1]);
                musicVolume = -50f + (music * 0.50f);
            } else if (Objects.equals(split[0], "effectsVolume")) {
                effects = Float.parseFloat(split[1]);
                effectsVolume = -50f + (effects * 0.50f);
            }
        }
    }

    private static void writeValues() throws IOException {
        PrintWriter writer = new PrintWriter(settingsPath, StandardCharsets.UTF_8);

        writer.println("musicVolume:" + music);
        writer.println("effectsVolume:" + effects);
        writer.close();
    }

}