package ch.zhaw.psit.towerhopscotch.controllers.states;

import ch.zhaw.psit.towerhopscotch.controllers.Game;
import ch.zhaw.psit.towerhopscotch.GUI.input.MouseManager;
import ch.zhaw.psit.towerhopscotch.models.entities.enemies.Enemy;
import ch.zhaw.psit.towerhopscotch.models.maps.Map;
import ch.zhaw.psit.towerhopscotch.GUI.menus.BuildMenu;
import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.waves.Wave;
import ch.zhaw.psit.towerhopscotch.models.waves.WaveQueue;

import java.awt.*;
import java.util.Iterator;

public class GameState extends State {
    private Game game;
    private MouseManager mouseManager;
    private Player player;
    private Map map;
    private WaveQueue waveQueue;
    private Wave currentWave;
    private BuildMenu menu;

    public GameState(Game game, MouseManager mouseManager) {
        this.game = game;
        this.mouseManager = mouseManager;
        init();
    }

    public void init() {
        player = new Player();
        map = new Map("src/main/resources/maps/map1.txt");
        waveQueue = new WaveQueue(map, 1);
        currentWave = waveQueue.pop();
        menu = new BuildMenu();
    }

    @Override
    public void update() {
        map.update();
        menu.update();

        if(player.isDead())
            State.setState(game.getGameOverState());

        if(currentWave.waveDestroyed()) {
            if(waveQueue.allWavesDestroyed()) {
                State.setState(game.getVictoryState());
            } else {
                currentWave = waveQueue.pop();
            }
        } else {
            // Update enemies
            Player player = getPlayer();
            Iterator<Enemy> iterator = currentWave.getEnemies().iterator();
            while (iterator.hasNext()) {
                Enemy enemy = iterator.next();
                enemy.update();

                // Remove enemy if it has reached the players fortress
                if (enemy.reachedDestination()) {
                    player.decreaseHealth(enemy.getDamage());
                    iterator.remove();
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        map.render(g);

        for (Enemy enemy : currentWave.getEnemies())
            enemy.render(g);

        menu.render(g);
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMap() {
        return map;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }
}
