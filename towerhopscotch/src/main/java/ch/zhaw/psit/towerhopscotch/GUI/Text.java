package ch.zhaw.psit.towerhopscotch.GUI;

import java.awt.*;

public class Text {
    public static void drawString(Graphics g, String text, int xPos, int yPos, Color color, Font font) {
        g.setColor(color);
        g.setFont(font);

        int x = xPos;
        int y = yPos + 25;

        g.drawString(text, x, y);
    }
}
