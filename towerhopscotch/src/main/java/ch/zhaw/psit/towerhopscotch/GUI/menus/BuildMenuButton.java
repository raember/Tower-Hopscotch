package ch.zhaw.psit.towerhopscotch.GUI.menus;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.GUI.Text;

import java.awt.*;

public class BuildMenuButton {
    private String text;
    private int x;
    private int y;
    private int width;
    private int height;

    public BuildMenuButton(String text, int x, int y) {
        this.text = text;
        this.x = x;
        this.y = y;
    }

    public void update() {
        
    }

    public void render(Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        width = fm.stringWidth(text) + 20;
        height = 32;

        g.setColor(Color.BLACK);
        g.fillRoundRect(x - 10, y, width, height, 5, 5);
        Text.drawString(g, text, x, y, Color.WHITE, Assets.font32);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
