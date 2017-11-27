package ch.zhaw.psit.towerhopscotch.controllers;

import ch.zhaw.psit.towerhopscotch.GUI.input.MouseManager;
import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.GUI.Display;
import ch.zhaw.psit.towerhopscotch.controllers.states.GameOverState;
import ch.zhaw.psit.towerhopscotch.controllers.states.GameState;
import ch.zhaw.psit.towerhopscotch.controllers.states.MainMenuState;
import ch.zhaw.psit.towerhopscotch.controllers.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

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

    // Inputs
    private MouseManager mouseManager;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        mouseManager = new MouseManager();
    }

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

        // Set the current state
        State.setState(mainMenuState);
    }

    private void update() {
        if (State.getState() != null)
            State.getState().update();
    }

    private void render() {
        BufferStrategy bufferStrategy = display.getCanvas().getBufferStrategy();
        if (bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        display.getCanvas().setBackground(new Color(238,195,154));

        Graphics g = bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        if (State.getState() != null)
            State.getState().render(g);

        bufferStrategy.show();
        g.dispose();
    }

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

    public synchronized void start() {
        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

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

    public MouseManager getMouseManager() {
        return mouseManager;
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
}