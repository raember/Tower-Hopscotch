package ch.zhaw.psit.towerhopscotch;

import ch.zhaw.psit.towerhopscotch.controller.Game;
import com.sun.media.jfxmedia.logging.Logger;

public class Launcher {
    public static void main(String args[]) {
        Logger.setLevel(Logger.INFO);
        Logger.logMsg(Logger.INFO, "\nHello world!");

        Game game = new Game("Tower Hopscotch", 600, 600);
        game.start();
    }
}
