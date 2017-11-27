package ch.zhaw.psit.towerhopscotch.states;

import ch.zhaw.psit.towerhopscotch.Game;
import ch.zhaw.psit.towerhopscotch.maps.Map;
import ch.zhaw.psit.towerhopscotch.GUI.BuildMenu;

import java.awt.*;

public class GameState extends State {

    private Game game;
    private Map map;
    private BuildMenu menu;

    public GameState(Game game) {
        this.game = game;
        map = new Map(game,"src/main/resources/maps/map1.txt");
        menu = new BuildMenu();
    }

    @Override
    public void update() {
        map.update();
        menu.update();
    }

    @Override
    public void render(Graphics g) {
        map.render(g);
        menu.render(g);
    }

    public Map getMap() {
        return map;
    }
}
