package ch.zhaw.psit.towerhopscotch.util;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;

public class MusicPlayer implements Runnable {

    private ArrayList<String> musicFiles;
    private int currentSondIndex;

    public MusicPlayer(String... files) {

        ClassLoader classLoader = getClass().getClassLoader();
        String path = classLoader.getResource("audio/").getPath();

        musicFiles = new ArrayList<String>();
        for (String file : files)
            musicFiles.add(path + file + ".wav");
    }

    public void playSound(String fileName) {
        try {
            File soundFile = new File(fileName);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        playSound(musicFiles.get(currentSondIndex));


    }

}
