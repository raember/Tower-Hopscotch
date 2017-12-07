package ch.zhaw.psit.towerhopscotch.models.waves;

import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.entities.enemies.*;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.maps.Map;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A wave contains the enemies on their layers.
 * @author Nicolas Eckhart, Stefan Bösch
 */
public class Wave {
    private Map map;

    private List<Enemy> hellEnemies;
    private List<Enemy> earthEnemies;
    private List<Enemy> heavenEnemies;
    private Player player;


    public Wave(Map map, Player player) {
        this.map = map;
        hellEnemies = new ArrayList<>();
        earthEnemies = new ArrayList<>();
        heavenEnemies = new ArrayList<>();
        this.player = player;
        generateEnemies(3);
    }

    /**
     * Generate specific count of enemies on each layer
     * @param count Count
     */
    private void generateEnemies(int count) {
        generateEnemiesOnLayer(map.getHell(), count);
        generateEnemiesOnLayer(map.getEarth(), count);
        generateEnemiesOnLayer(map.getHeaven(), count);
    }

    /**
     * Generate enemies on layer
     * @param layer Layer
     * @param count Count
     */
    private void generateEnemiesOnLayer(Layer layer, int count) {
        // Enemies are generated below the screen all with the same x position as the starting tile
        // but varying y positions.
        Random random = new Random();
        for (int i = 1; i <= count; i++) {
            int enemyType = random.nextInt(7 - 1 + 1) + 1;
            int randomOffset = (2 * (random.nextInt(40 - 15 + 1) + 15));
            int startingHeight = layer.getStartY() + (i * Tile.TILE_HEIGHT) + randomOffset;
            Enemy enemy = null;
            switch (enemyType) {
                case 1:
                    enemy = new Rat(layer, layer.getStartX(), startingHeight, player);
                    break;
                case 2:
                    enemy = new Bat(layer, layer.getStartX(), startingHeight, player);
                    break;
                case 3:
                    enemy = new Skeleton(layer, layer.getStartX(), startingHeight, player);
                    break;
                case 4:
                    enemy = new Spider(layer, layer.getStartX(), startingHeight, player);
                    break;
                case 5:
                    enemy = new Goblin(layer, layer.getStartX(), startingHeight, player);
                    break;
                case 6:
                    enemy = new Slime(layer, layer.getStartX(), startingHeight, player);
                    break;
                case 7:
                    enemy = new Imp(layer, layer.getStartX(), startingHeight, player);
                    break;
            }
            switch (layer.getLayerType()) {
                case HELL:
                    hellEnemies.add(enemy);
                    break;
                case EARTH:
                    earthEnemies.add(enemy);
                    break;
                case HEAVEN:
                    heavenEnemies.add(enemy);
                    break;
            }
        }
    }

    /**
     * Check if there are no enemies on the layers
     * @return All enemies destroyed
     */
    public boolean waveDestroyed() {
        return map.getHell().getEnemies().size() + map.getEarth().getEnemies().size() + map.getHeaven().getEnemies().size() == 0;
    }

    public List<Enemy> getHellEnemies() {
        return hellEnemies;
    }

    public List<Enemy> getEarthEnemies() {
        return earthEnemies;
    }

    public List<Enemy> getHeavenEnemies() {
        return heavenEnemies;
    }
}
