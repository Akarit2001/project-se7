package nl.tudelft.jpacman.audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class PacManSoundPlayer {

    public static void playEat() {
        playSound("src\\main\\resources\\audio\\eat.wav");
    }

    public static void playWlak() {
        playSound("src\\main\\resources\\audio\\walk.wav");
    }

    public static void playWin() {
        playSound("src\\main\\resources\\audio\\winwav.wav");
    }

    public static void playLost() {
        playSound("src\\main\\resources\\audio\\diewave.wav");
    }

    public static void playBtnClick() {
        playSound("src\\main\\resources\\audio\\mouseClick.wav");
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
