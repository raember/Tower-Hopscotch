package ch.zhaw.psit.towerhopscotch;

import ch.zhaw.psit.towerhopscotch.GUI.BuildMenu;

public class Launcher {

    public static void main(String args[]) {
        Game game = new Game("Tower Hopscotch", 3*448 + 20, 640 + BuildMenu.HEIGHT);
        game.start();
    }
}
