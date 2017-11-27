package ch.zhaw.psit.towerhopscotch.states;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.GUI.Text;
import ch.zhaw.psit.towerhopscotch.GUI.menus.Button;
import ch.zhaw.psit.towerhopscotch.Game;
import ch.zhaw.psit.towerhopscotch.input.MouseManager;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tiles.TileList;

import java.awt.*;

public class MainMenuState extends State {
    private Game game;
    private MouseManager mouseManager;
    private Button newGameButton;

    public MainMenuState(Game game, MouseManager mouseManager) {
        this.game = game;
        this.mouseManager = mouseManager;
        newGameButton = new Button("New Game", game.getWidth() / 8 * 4 - 70, game.getHeight() / 3 * 2);
    }

    public void update() {
        newGameButton.update();

        if(newGameButton.isClicked()) {
            State gameState = game.getGameState();
            ((GameState) gameState).init();
            State.setState(gameState);
        }
    }

    public void render(Graphics g) {
        for(int y = 0; y < game.getHeight(); y += Tile.TILE_HEIGHT) {
            for(int x = 0; x < game.getWidth(); x += Tile.TILE_WIDTH) {
                TileList.getTile(0).render(g,null, x, y);
            }
        }
        Text.drawString(g, "Main Menu", game.getWidth() / 2, game.getHeight() / 2, true, Color.WHITE, Assets.font128);
        newGameButton.render(g);
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }
}
