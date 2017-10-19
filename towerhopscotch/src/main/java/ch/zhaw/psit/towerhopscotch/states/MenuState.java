package ch.zhaw.psit.towerhopscotch.states;

import java.awt.*;
import ch.zhaw.psit.towerhopscotch.gfx.Assets;

public class MenuState extends State {

    // x position of the testing rectangle
    private int rectXPos = 0;

    @Override
    public void update() {
        rectXPos++;
        if(rectXPos > 600-50) {
            rectXPos = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(rectXPos, 50, 50, 50);
    }
}
