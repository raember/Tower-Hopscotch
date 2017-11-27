package ch.zhaw.psit.towerhopscotch;

public class Launcher {

    public static void main(String args[]) {
        Game game = new Game("Tower Hopscotch", 3*448, 640);
        game.start();
    }
}
