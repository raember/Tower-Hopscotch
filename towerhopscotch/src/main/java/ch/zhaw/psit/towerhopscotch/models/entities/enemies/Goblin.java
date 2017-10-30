package ch.zhaw.psit.towerhopscotch.models.entities.enemies;

import ch.zhaw.psit.towerhopscotch.Game;
import ch.zhaw.psit.towerhopscotch.GUI.Assets;

import java.awt.*;

public class Goblin extends Enemy {
    public Goblin(Game game, float x, float y) {
        super(game, x, y, Enemy.DEFAULT_WIDTH, Enemy.DEFAULT_HEIGHT);
    }

    public void update() {
        move();
    }

    public void render(Graphics g) {
        g.drawImage(Assets.enemy, (int) x, (int) y, width, height, null);
    }
}
