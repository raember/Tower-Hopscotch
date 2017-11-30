package ch.zhaw.psit.towerhopscotch.models.tower;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;

import java.awt.*;


public class MonoTower extends Tower {
    public MonoTower(int price, Color color) {
        super(price, color);
    }

    public void render(Graphics g) {
        g.drawImage(Assets.enemies.get("spider"), (int) getPosition().getX(), (int) getPosition().getY(), 32, 32, null);
    }
}
