package ch.zhaw.psit.towerhopscotch.models;

import java.awt.*;
import ch.zhaw.psit.towerhopscotch.gfx.Assets;

public class Fortress {

    private int health;
    private float x, y;

    public Fortress(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update() {

    }

    public void render(Graphics g) {
        g.drawImage(Assets.fortress, (int) x, (int) y, null);
    }
}
