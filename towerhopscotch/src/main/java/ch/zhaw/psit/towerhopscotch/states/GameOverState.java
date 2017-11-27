package ch.zhaw.psit.towerhopscotch.states;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.GUI.Text;
import ch.zhaw.psit.towerhopscotch.GUI.menus.Button;
import ch.zhaw.psit.towerhopscotch.Game;
import ch.zhaw.psit.towerhopscotch.input.MouseManager;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tiles.TileList;

import java.awt.*;

public class GameOverState extends State {
    private Game game;
    private MouseManager mouseManager;
    private Button mainMenuButton;
    private Button newGameButton;

    public GameOverState(Game game, MouseManager mouseManager) {
        this.game = game;
        this.mouseManager = mouseManager;
        mainMenuButton = new Button("Main Menu", game.getWidth() / 8 * 2, game.getHeight() / 3 * 2);
        newGameButton = new Button("New Game", game.getWidth() / 8 * 5 - 15, game.getHeight() / 3 * 2);
    }

    public void update() {
        mainMenuButton.update();
        newGameButton.update();

        if(mainMenuButton.isClicked())
            State.setState(game.getMainMenuState());

        if(newGameButton.isClicked()) {
            State gameState = game.getGameState();
            ((GameState) gameState).init();
            State.setState(gameState);
        }
    }

    public void render(Graphics g) {
        for(int y = 0; y < game.getHeight(); y += Tile.TILE_HEIGHT) {
            for(int x = 0; x < game.getWidth(); x += Tile.TILE_WIDTH) {
                TileList.getTile(0).render(g, x, y);
            }
        }
        Text.drawString(g, "Game Over", game.getWidth() / 2, game.getHeight() / 2, true, Color.WHITE, Assets.font128);
        mainMenuButton.render(g);
        newGameButton.render(g);
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }
}
