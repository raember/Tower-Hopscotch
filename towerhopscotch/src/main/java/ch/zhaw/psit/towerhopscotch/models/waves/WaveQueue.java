package ch.zhaw.psit.towerhopscotch.models.waves;

import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.maps.Map;

import java.util.ArrayList;

public class WaveQueue {
    private Map map;
    private ArrayList<Wave> queue;
    private Player player;

    public WaveQueue(Map map, int waveCount, Player player) {
        this.map = map;
        this.player = player;
        queue = new ArrayList<>();
        generateWaves(waveCount);
    }

    private void generateWaves(int waveCount) {
        for (int i = 0; i < waveCount; i++) {
            add(new Wave(map, player));
        }
    }

    public void add(Wave wave) {
        queue.add(wave);
    }

    public Wave top() {
        return queue.get(lastIndex());
    }

    public Wave pop() {
        Wave wave = top();
        queue.remove(lastIndex());
        return wave;
    }

    public int size() {
        return queue.size();
    }

    public boolean allWavesDestroyed() {
        return size() == 0;
    }

    private int lastIndex() {
        return queue.size() - 1;
    }
}


