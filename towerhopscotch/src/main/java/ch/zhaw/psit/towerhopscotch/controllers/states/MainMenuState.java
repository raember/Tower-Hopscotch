package ch.zhaw.psit.towerhopscotch.controllers.states;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.GUI.Text;
import ch.zhaw.psit.towerhopscotch.GUI.input.MouseManager;
import ch.zhaw.psit.towerhopscotch.GUI.menus.Button;
import ch.zhaw.psit.towerhopscotch.controllers.Game;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import ch.zhaw.psit.towerhopscotch.models.tiles.TileList;

import java.awt.*;

/**
 * The state for the main menu
 * @author Nicolas Eckhart
 */
public class MainMenuState extends State {
    private Game game;
    private Button newGameButton;

    public MainMenuState(Game game, MouseManager mouseManager) {
        super(mouseManager);
        this.game = game;
        newGameButton = new Button("New Game", game.getWidth() / 8 * 4 - 70, game.getHeight() / 3 * 2);
    }

    /**
     * @inheritDoc
     */
    public void update() {
        newGameButton.update();

        if (newGameButton.isClicked()) {
            State gameState = game.createNewGameState();
            ((GameState) gameState).init();
            State.setState(gameState);
        }
    }

    /**
     * @inheritDoc
     */
    public void render(Graphics g) {
        for (int y = 0; y < game.getHeight(); y += Tile.TILE_HEIGHT) {
            for (int x = 0; x < game.getWidth(); x += Tile.TILE_WIDTH) {
                TileList.getTile(0).render(g, null, x, y);
            }
        }
        Text.drawString(g, "Main Menu", game.getWidth() / 2, game.getHeight() / 2, true, Color.WHITE, Assets.font128);
        newGameButton.render(g);
    }
}
