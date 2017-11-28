package ch.zhaw.psit.towerhopscotch;

import ch.zhaw.psit.towerhopscotch.GUI.menus.BuildMenu;
import ch.zhaw.psit.towerhopscotch.controllers.Game;
import ch.zhaw.psit.towerhopscotch.models.maps.Layer;

public class Launcher {

    public static void main(String args[]) {
        Game game = new Game("Tower Hopscotch", 3 * Layer.LAYER_WIDTH + 20, Layer.LAYER_HEIGHT + BuildMenu.HEIGHT);
        game.start();
    }
}
