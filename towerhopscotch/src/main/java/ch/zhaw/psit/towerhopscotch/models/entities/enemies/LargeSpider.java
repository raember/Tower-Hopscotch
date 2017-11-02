package ch.zhaw.psit.towerhopscotch.models.entities.enemies;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.Game;

import java.awt.*;

public class LargeSpider extends Enemy {
    public LargeSpider(Game game, float x, float y) {
        super(game, x, y, Enemy.DEFAULT_WIDTH, Enemy.DEFAULT_HEIGHT);
    }

    public void update() {
        move();
    }

    public void render(Graphics g) {
        g.drawImage(Assets.enemies.get("large_spider"), (int) x, (int) y, width, height, null);
    }
}
