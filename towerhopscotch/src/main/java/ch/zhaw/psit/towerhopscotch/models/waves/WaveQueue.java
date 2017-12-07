package ch.zhaw.psit.towerhopscotch.models.waves;

import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.maps.Map;

import java.util.ArrayList;

/**
 * The WaveQueue generates and stores the waves
 * with pop() the next wave gets released
 * @author Nicolas Eckhart
 */
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

    /**
     * Generate Waves
     * @param waveCount Count of waves
     */
    private void generateWaves(int waveCount) {
        for (int i = 0; i < waveCount; i++) {
            add(new Wave(map, player));
        }
    }

    /**
     * Add a wave to the queue
     * @param wave The wave to add
     */
    public void add(Wave wave) {
        queue.add(wave);
    }

    /**
     * Get the next wave but without removing it from the queue
     * @return The wave at the beginning of the queue
     */
    public Wave top() {
        return queue.get(lastIndex());
    }

    /**
     * Get and delete the next wave
     * @return The wave at the beginning ot teh queue
     */
    public Wave pop() {
        Wave wave = top();
        queue.remove(lastIndex());
        return wave;
    }

    /**
     * Get the count of waves in the queue
     * @return the count of waves
     */
    public int size() {
        return queue.size();
    }

    /**
     * Check if the queue is empty
     * @return True if empty
     */
    public boolean allWavesDestroyed() {
        return size() == 0;
    }

    private int lastIndex() {
        return queue.size() - 1;
    }
}


