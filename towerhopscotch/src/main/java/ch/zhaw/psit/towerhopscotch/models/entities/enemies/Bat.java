package ch.zhaw.psit.towerhopscotch.models.entities.enemies;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.Game;
import ch.zhaw.psit.towerhopscotch.maps.Layer;

import java.awt.*;

public class Bat extends Enemy {
    public Bat(Game game, Layer onLayer, float x, float y) {
        super(game, onLayer, x, y, Enemy.DEFAULT_WIDTH, Enemy.DEFAULT_HEIGHT, 100, 10, 2.0f);
    }

    public void update() {
        move();
    }

    public void render(Graphics g) {
        renderEnemy(g,Assets.enemies.get("bat"));
    }
}
