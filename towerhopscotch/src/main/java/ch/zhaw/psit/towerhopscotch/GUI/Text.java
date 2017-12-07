package ch.zhaw.psit.towerhopscotch.GUI;

import java.awt.*;

/**
 * Custom text
 * @author Nicolas Eckhart
 */
public class Text {

    /**
     * Draw a String at the specified position.
     * @param g Graphics
     * @param text Text
     * @param xPos Poition X
     * @param yPos Position Y
     * @param center Centered
     * @param color Color
     * @param font Font
     */
    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color color, Font font) {
        g.setColor(color);
        g.setFont(font);

        int x = xPos;
        int y = yPos + 25;

        if (center) {
            FontMetrics fm = g.getFontMetrics(font);
            x = xPos - fm.stringWidth(text) / 2;
            y = (yPos - fm.getHeight() / 2) + fm.getAscent();
        }

        g.drawString(text, x, y);
    }
}
