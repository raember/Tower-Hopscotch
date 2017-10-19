package ch.zhaw.psit.towerhopscotch;

import ch.zhaw.psit.towerhopscotch.gui.Display;
import ch.zhaw.psit.towerhopscotch.states.GameState;
import ch.zhaw.psit.towerhopscotch.states.MenuState;
import ch.zhaw.psit.towerhopscotch.states.State;
import ch.zhaw.psit.towerhopscotch.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    private Display display;
    private String title;
    private int width, height;
    private Clock clock;
    private boolean running = false;
    private Thread thread;

    private BufferStrategy bufferStrategy;
    private Graphics graphicsObject;

    // All game states
    private State gameState;
    private State menuState;

    Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    private void init() {
        // Initialize the display, initialize the game clock and the assets
        display = new Display(title, width, height);
        clock = new Clock();
        Assets.init();

        // Initialize the game states
        gameState = new GameState();
        menuState = new MenuState();

        // Set the current state
        State.setState(gameState);
    }

    private void update() {
        if(State.getState() != null)
            State.getState().update();
    }

    private void render() {
        bufferStrategy = display.getCanvas().getBufferStrategy();
        if(bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        graphicsObject = bufferStrategy.getDrawGraphics();
        graphicsObject.clearRect(0, 0, width, height);

        if(State.getState() != null)
            State.getState().render(graphicsObject);

        bufferStrategy.show();
        graphicsObject.dispose();
    }

    public void run() {
        init();

        // Game loop
        while(running) {
            clock.tick();

            if(clock.getDeltaTime() >= 1) {
                update();
                render();
                clock.increaseTicks();
                clock.decreaseDeltaTime();
            }

            clock.printFPS();
        }

        stop();
    }

    synchronized void start() {
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    synchronized void stop() {
        if(!running)
            return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}