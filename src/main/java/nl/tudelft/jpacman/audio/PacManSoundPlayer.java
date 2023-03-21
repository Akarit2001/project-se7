package nl.tudelft.jpacman.audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class PacManSoundPlayer {
    Clip packmanClip;

    public PacManSoundPlayer() {
        try {
            this.packmanClip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            System.out.println(e);
        }

    }

    public static void playEat() {
        playSound("src\\main\\resources\\audio\\eat.wav");
    }

    public static void playWlak() {
        playSound("src\\main\\resources\\audio\\walk2.wav");
    }

    public static void playWin() {
        playSound("src\\main\\resources\\audio\\win2.wav");
    }

    public static void playLost() {
        playSound("src\\main\\resources\\audio\\die2.wav");
    }

    public static void playBtnClick() {
        playSound("src\\main\\resources\\audio\\mouseClick.wav");
    }

    public void playBgSound() {
        String filsePath = "src\\main\\resources\\audio\\bgsound2.wav";
        try {
            File file = new File(filsePath);
            if (file.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                packmanClip.open(audioInputStream);
                packmanClip.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void StopBgSound() {
        this.packmanClip.stop();
        this.packmanClip.close();
    }

    public static void playSound(String filsePath) {
        try {
            File file = new File(filsePath);
            if (file.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
