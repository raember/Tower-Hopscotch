package ch.zhaw.psit.towerhopscotch.GUI.menus;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.GUI.Text;

import java.awt.*;

public class BuildMenuButton {
    private String text;
    private int x;
    private int y;

    public BuildMenuButton(String text, int xPos, int yPos) {
        this.text = text;
        x = xPos;
        y = yPos;
    }

    public void render(Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);

        g.setColor(Color.BLACK);
        g.fillRoundRect(x - 10, y, textWidth + 20, 32, 5, 5);
        Text.drawString(g, text, x, y, Color.WHITE, Assets.font32);
    }
}
