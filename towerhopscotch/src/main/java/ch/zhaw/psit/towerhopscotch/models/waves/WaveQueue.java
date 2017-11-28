package ch.zhaw.psit.towerhopscotch.models.waves;

import ch.zhaw.psit.towerhopscotch.models.entities.enemies.*;
import ch.zhaw.psit.towerhopscotch.models.maps.Map;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;

import java.util.ArrayList;
import java.util.Random;

public class WaveQueue {
    private Map map;
    private ArrayList<Wave> queue;

    public WaveQueue(Map map, int waveCount) {
        this.map = map;
        queue = new ArrayList<Wave>();
        generateWaves(waveCount);
    }

    private void generateWaves(int waveCount) {
        for(int i = 0; i < waveCount; i++) {
            add(new Wave(map));
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


