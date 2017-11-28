package ch.zhaw.psit.towerhopscotch.models.waves;

import ch.zhaw.psit.towerhopscotch.models.entities.enemies.*;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.maps.Map;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Wave {
    private Map map;
    private ArrayList<Enemy> enemies;

    public Wave(Map map) {
        this.map = map;
        enemies = new ArrayList<Enemy>();
        generateEnemies(5);
    }

    private void generateEnemies(int count) {
        generateEnemiesOnLayer(map.getHell(), count);
        generateEnemiesOnLayer(map.getEarth(), count);
        generateEnemiesOnLayer(map.getHeaven(), count);
    }

    private void generateEnemiesOnLayer(Layer layer, int count) {
        // Enemies are generated below the screen all with the same x position as the starting tile
        // but varying y positions.
        Random random = new Random();
        for (int i = 1; i <= count; i++) {
            int enemyType = random.nextInt(7 - 1 + 1) + 1;
            int startingHeight = layer.getStartY() + (i * Tile.TILE_HEIGHT) + (2 * (random.nextInt(10 + 10 + 1) - 10));
            switch (enemyType) {
                case 1:
                    enemies.add(new Rat(layer, layer.getStartX(), startingHeight));
                    break;
                case 2:
                    enemies.add(new Bat(layer, layer.getStartX(), startingHeight));
                    break;
                case 3:
                    enemies.add(new Skeleton(layer, layer.getStartX(), startingHeight));
                    break;
                case 4:
                    enemies.add(new Spider(layer, layer.getStartX(), startingHeight));
                    break;
                case 5:
                    enemies.add(new Goblin(layer, layer.getStartX(), startingHeight));
                    break;
                case 6:
                    enemies.add(new Slime(layer, layer.getStartX(), startingHeight));
                    break;
                case 7:
                    enemies.add(new Imp(layer, layer.getStartX(), startingHeight));
                    break;
            }
        }
    }

    public boolean waveDestroyed() {
        return enemies.size() == 0;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
