package ch.zhaw.psit.towerhopscotch;

import ch.zhaw.psit.towerhopscotch.GUI.menus.BuildMenu;
import ch.zhaw.psit.towerhopscotch.controllers.Game;

public class Launcher {

    public static void main(String args[]) {
        Game game = new Game("Tower Hopscotch", 3*448 + 20, 640 + BuildMenu.HEIGHT);
        game.start();
    }
}
