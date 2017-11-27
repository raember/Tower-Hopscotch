package ch.zhaw.psit.towerhopscotch.GUI.menus;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.GUI.Text;
import ch.zhaw.psit.towerhopscotch.input.MouseManager;
import ch.zhaw.psit.towerhopscotch.states.GameState;
import ch.zhaw.psit.towerhopscotch.states.State;

import java.awt.*;

public class Button {
    private String text;
    private int x;
    private int y;
    private int width;
    private int height;
    private Color buttonColor;

    public Button(String text, int x, int y) {
        this.text = text;
        this.x = x;
        this.y = y;
        buttonColor = Color.BLACK;
    }

    public void update() {
        if(width > 0 && height > 0) {
            isClicked();
        }
    }

    public void render(Graphics g) {
        width = (text.split("").length * 23) + 20;
        height = 32;

        g.setColor(buttonColor);
        g.fillRoundRect(rectX(), y, width, height, 5, 5);
        Text.drawString(g, text, x, y, false, Color.WHITE, Assets.font32);
    }

    public boolean isClicked() {
        MouseManager mouseManager = getMouseManager();
        if(mouseManager.isLeftPressed()) {
            int mouseX = mouseManager.getMouseX();
            int mouseY = mouseManager.getMouseY();
            if((mouseX > rectX() && mouseX < rectX() + width) && (mouseY > y && mouseY < y + height)) {
                buttonColor = new Color(0, 102, 0);
                return true;
            }
        }
        buttonColor = Color.BLACK;
        return false;
    }

    private MouseManager getMouseManager() {
        return State.getState().getMouseManager();
    }

    private int rectX() {
        return x - 10;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
