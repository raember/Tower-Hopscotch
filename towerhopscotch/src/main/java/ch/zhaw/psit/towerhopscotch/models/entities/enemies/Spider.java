package ch.zhaw.psit.towerhopscotch.models.entities.enemies;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;

import java.awt.*;

public class Spider extends Enemy {
    public Spider(Layer onLayer, int x, int y, Player player) {
        super(onLayer, x, y, Enemy.DEFAULT_WIDTH, Enemy.DEFAULT_HEIGHT, 100, 1, 1.0f, player, 10);
    }

    public void update() {
        move();
    }

    public void render(Graphics g) {
        renderEnemy(g, Assets.enemies.get("spider"));
    }
}
