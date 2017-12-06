package ch.zhaw.psit.towerhopscotch.controllers.states;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.GUI.Text;
import ch.zhaw.psit.towerhopscotch.GUI.input.MouseManager;
import ch.zhaw.psit.towerhopscotch.GUI.menus.BuildMenu;
import ch.zhaw.psit.towerhopscotch.controllers.Game;
import ch.zhaw.psit.towerhopscotch.controllers.towerStrategies.*;
import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.entities.enemies.Enemy;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;
import ch.zhaw.psit.towerhopscotch.models.maps.Map;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.waves.Wave;
import ch.zhaw.psit.towerhopscotch.models.waves.WaveQueue;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The state in which the main game runs
 * @author Nicolas Eckhart, Stefan Bösch
 */
public class GameState extends State {
    private Game game;
    private Player player;
    private Map map;
    private WaveQueue waveQueue;
    private Wave currentWave;
    private BuildMenu menu;

    private ArrayList<TowerStrategy> towerStrategyList;
    private TowerStrategy towerStrategy;

    private Tile selectedTile;
    private Point selectedTilePoint;

    public GameState(Game game, MouseManager mouseManager) {
        super(mouseManager);
        this.game = game;
        init();
    }

    /**
     * Initialize the game
     */
    public void init() {
        player = new Player();
        player.addGold(500);
        map = new Map("src/main/resources/maps/map1.txt");
        waveQueue = new WaveQueue(map, 5, player);
        menu = new BuildMenu();
        towerStrategyList = new ArrayList<>();
        towerStrategyList.add(new PlaceMonoTowerStrategy());
        towerStrategyList.add(new PlaceDoubleTowerStrategy());
        towerStrategyList.add(new PlaceTripleTowerStrategy());
        towerStrategyList.add(new UpgradeTowerStrategy());
        towerStrategyList.add(new TearDownTowerStrategy());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void update() {
        long now = System.nanoTime();
        map.update(now);
        menu.update();

        if (player.isDead())
            State.setState(game.getGameOverState());

        choosePlaceTowerStrategy();

        if (towerStrategy != null && selectedTile != null) {
            if (selectedTile.isTowerPlaceable()) {
                if (towerStrategy.doTowerOperation(this, selectedTilePoint)) {
                    towerStrategy = null;
                }
                selectedTile = null;
                selectedTilePoint = null;
            }
        }

        if (mouseManager.isLeftPressed() && towerStrategy != null) {
            selectTile();
        }

        updateWaveQueue();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void render(Graphics g) {
        map.render(g);

        drawWavesPausedText(g);
        drawWavesRemainingText(g);

        if (towerStrategy != null) {
            towerStrategy.activeAction(this, g);
        } else {
            drawText(g,"");
        }

        menu.render(g);
    }

    /**
     * Check which menubutton gets clicked
     */
    private void choosePlaceTowerStrategy() {
        if (menu.placeSimpleTowerClicked()) {
            towerStrategy = towerStrategyList.get(0);
        }
        if (menu.placeDoubleTowerClicked()) {
            towerStrategy = towerStrategyList.get(1);
        }
        if (menu.placeTripleTowerClicked()) {
            towerStrategy = towerStrategyList.get(2);
        }
        if (menu.towerUpgradeButtonClicked()) {
            towerStrategy = towerStrategyList.get(3);
        }
        if (menu.towerDestroyButtonClicked()) {
            towerStrategy = towerStrategyList.get(4);
        }
    }

    /**
     * Select tile at current mouse position
     */
    private void selectTile() {
        if (map.isOnMap(mouseManager.getPosition())) {
            Layer layer = map.getLayer(mouseManager.getPosition());
            if (layer != null) {
                int offset = 0;
                switch (layer.getLayerType()) {
                    case HELL:
                        offset = 0;
                        break;
                    case EARTH:
                        offset = 10;
                        break;
                    case HEAVEN:
                        offset = 20;
                        break;
                }
                int x = mouseManager.getMouseX();
                x -= ((x - offset) % 32);
                int y = mouseManager.getMouseY();
                y = y - (y % 32);
                selectedTilePoint = new Point(x, y);
                selectedTile = layer.getTile(selectedTilePoint);
            }
        }
    }

    /**
     * Check if wave is defeated
     */
    private void updateWaveQueue() {
        if (currentWave == null || currentWave.waveDestroyed()) {
            if (waveQueue.allWavesDestroyed()) {
                State.setState(game.getVictoryState());
            } else if (menu.callNextWaveClicked()) {
                popWave();
            }
        } else {
            checkIfEnemiesReachedDestination(map.getHell());
            checkIfEnemiesReachedDestination(map.getEarth());
            checkIfEnemiesReachedDestination(map.getHeaven());
        }
    }

    /**
     * Get next wave
     */
    private void popWave() {
        currentWave = waveQueue.pop();
        map.getHell().setEnemies(currentWave.getHellEnemies());
        map.getEarth().setEnemies(currentWave.getEarthEnemies());
        map.getHeaven().setEnemies(currentWave.getHeavenEnemies());
    }

    /**
     * Check if all enemies on layer have reached the fortress
     * @param layer Layer
     */
    private void checkIfEnemiesReachedDestination(Layer layer) {
        Player player = getPlayer();
        Iterator<Enemy> iterator = layer.getEnemies().iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.update();

            if (enemy.teleport()) {
                iterator.remove();
            }

            // Remove enemy if it has reached the players fortress
            if (enemy.reachedDestination()) {
                player.decreaseHealth(enemy.getDamage());
                iterator.remove();
            }
        }
    }

    /**
     * Display wave-paused-text
     * @param g Graphics
     */
    private void drawWavesPausedText(Graphics g) {
        if (currentWave == null || currentWave.waveDestroyed())
            Text.drawString(g, "WAVES PAUSED", game.getWidth() / 2, 50, true, Color.WHITE, Assets.font32);
    }

    /**
     * Display text in the middle of the Screen
     * @param g Graphics
     * @param text Text
     */
    public void drawText(Graphics g, String text){
        Text.drawString(g, text, game.getWidth() / 2, 300, true, new Color(255,50,50), Assets.font32);
    }

    /**
     * Display number of remaining waves
     * @param g Graphics
     */
    private void drawWavesRemainingText(Graphics g) {
        Text.drawString(g, getWavesRemaining() + " WAVES", game.getWidth() - 100, 20, true, Color.BLACK, Assets.font32);
        Text.drawString(g, "REMAINING", game.getWidth() - 100, 50, true, Color.BLACK, Assets.font32);
    }

    /**
     * Get number of remaining waves
     * @return remainingWaves
     */
    private int getWavesRemaining() {
        if (currentWave == null || currentWave.waveDestroyed()) {
            return waveQueue.size();
        }
        return waveQueue.size() + 1;
    }

    /**
     * Get Player
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get Map
     * @return Map
     */
    public Map getMap() {
        return map;
    }
}
