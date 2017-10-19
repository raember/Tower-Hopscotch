package ch.zhaw.psit.towerhopscotch.controller;

import ch.zhaw.psit.towerhopscotch.gui.Display;

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
    private Graphics g;

    // x position of the testing rectangle
    private int rectXPos = 0;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    private void init() {
        display = new Display(title, width, height);
        clock = new Clock();
    }

    private void update() {
        rectXPos++;
        if(rectXPos > width-50) {
            rectXPos = 0;
        }
    }

    private void render() {
        bufferStrategy = display.getCanvas().getBufferStrategy();
        if(bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        // DRAW HERE
        g.fillRect(rectXPos,height / 2 - 25, 50, 50);

        bufferStrategy.show();
        g.dispose();
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

    public synchronized void start() {
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
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