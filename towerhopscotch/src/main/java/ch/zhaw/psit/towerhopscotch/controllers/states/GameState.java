package ch.zhaw.psit.towerhopscotch.controllers.states;

import ch.zhaw.psit.towerhopscotch.controllers.Game;
import ch.zhaw.psit.towerhopscotch.GUI.input.MouseManager;
import ch.zhaw.psit.towerhopscotch.models.maps.Map;
import ch.zhaw.psit.towerhopscotch.GUI.menus.BuildMenu;
import ch.zhaw.psit.towerhopscotch.models.Player;

import java.awt.*;

public class GameState extends State {
    private Game game;
    private MouseManager mouseManager;
    private Player player;
    private Map map;
    private BuildMenu menu;

    public GameState(Game game, MouseManager mouseManager) {
        this.game = game;
        this.mouseManager = mouseManager;
        init();
    }

    public void init() {
        player = new Player();
        map = new Map("src/main/resources/maps/map1.txt");
        menu = new BuildMenu();
    }

    @Override
    public void update() {
        map.update();
        menu.update();

        if(player.isDead())
            State.setState(game.getGameOverState());
    }

    @Override
    public void render(Graphics g) {
        map.render(g);
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
