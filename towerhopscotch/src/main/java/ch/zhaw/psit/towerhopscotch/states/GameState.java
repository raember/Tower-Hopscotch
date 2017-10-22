package ch.zhaw.psit.towerhopscotch.states;

import java.awt.*;
import ch.zhaw.psit.towerhopscotch.gfx.Assets;
import ch.zhaw.psit.towerhopscotch.models.Fortress;

public class GameState extends State {

    // x position of the testing rectangle
    private Fortress fortress;

    public GameState() {
        fortress = new Fortress(100, 100);
    }

    @Override
    public void update() { }

    @Override
    public void render(Graphics g) {
        fortress.render(g);
    }
}
