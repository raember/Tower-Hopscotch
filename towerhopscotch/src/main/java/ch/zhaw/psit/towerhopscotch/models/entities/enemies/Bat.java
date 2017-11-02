package ch.zhaw.psit.towerhopscotch.models.entities.enemies;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.Game;

import java.awt.*;

public class Bat extends Enemy {
    public Bat(Game game, float x, float y) {
        super(game, x, y, Enemy.DEFAULT_WIDTH, Enemy.DEFAULT_HEIGHT);
    }

    public void update() {
        move();
    }

    public void render(Graphics g) {
        g.drawImage(Assets.enemies.get("bat"), (int) x, (int) y, width, height, null);
    }
}
