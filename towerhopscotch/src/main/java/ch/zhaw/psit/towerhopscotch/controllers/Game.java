package ch.zhaw.psit.towerhopscotch.controllers;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.GUI.Display;
import ch.zhaw.psit.towerhopscotch.GUI.input.MouseManager;
import ch.zhaw.psit.towerhopscotch.controllers.states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * The class which contains the display and the frame limiter
 * @author Nicolas Eckhart
 */
public class Game implements Runnable {
    private Display display;
    private String title;
    private int width, height;
    private Clock clock;
    private boolean running = false;
    private Thread thread;

    // All game states
    private State gameState;
    private State gameOverState;
    private State mainMenuState;
    private State victoryState;

    // Inputs
    private MouseManager mouseManager;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        mouseManager = new MouseManager();
    }

    /**
     * Initialize
     */
    private void init() {
        // Initialize the display, initialize the game clock and the assets
        display = new Display(title, width, height);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        clock = new Clock();
        Assets.initialize();

        // Initialize the game states
        gameState = new GameState(this, mouseManager);
        gameOverState = new GameOverState(this, mouseManager);
        mainMenuState = new MainMenuState(this, mouseManager);
        victoryState = new VictoryState(this, mouseManager);

        // Set the current state
        State.setState(mainMenuState);
    }

    /**
     * Update the active state
     */
    private void update() {
        if (State.getState() != null)
            State.getState().update();
    }

    /**
     * Create the Graphics and render the state
     */
    private void render() {
        BufferStrategy bufferStrategy = display.getCanvas().getBufferStrategy();
        if (bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        display.getCanvas().setBackground(new Color(238, 195, 154));

        Graphics g = bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        if (State.getState() != null)
            State.getState().render(g);

        bufferStrategy.show();
        g.dispose();
    }

    /**
     * Run the thread
     */
    public void run() {
        init();

        // Game loop
        while (running) {
            clock.tick();

            if (clock.getDeltaTime() >= 1) {
                update();
                render();
                clock.increaseTicks();
                clock.decreaseDeltaTime();
            }

            clock.printFPS();
        }

        stop();
    }

    /**
     * Start the thread
     */
    public synchronized void start() {
        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Stop the thread
     */
    public synchronized void stop() {
        if (!running)
            return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public State getGameState() {
        return gameState;
    }

    public State getGameOverState() {
        return gameOverState;
    }

    public State getMainMenuState() {
        return mainMenuState;
    }

    public State getVictoryState() {
        return victoryState;
    }

    public State createNewGameState() {
        gameState = new GameState(this, mouseManager);
        return getGameState();
    }
}