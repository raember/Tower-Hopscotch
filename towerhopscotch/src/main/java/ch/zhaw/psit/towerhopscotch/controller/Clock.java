package ch.zhaw.psit.towerhopscotch.controller;

class Clock {
    private static int FPS = 60;
    private double timePerTick = 1000000000 / FPS;
    private double deltaTime;
    private long lastTime, timer;
    private int ticks;

    Clock() {
        lastTime = System.nanoTime();
        deltaTime = 0;
        timer = 0;
        ticks = 0;
    }

    void tick() {
        long currentTime = System.nanoTime();
        deltaTime += (currentTime - lastTime) / timePerTick;
        timer += currentTime - lastTime;
        lastTime = currentTime;
    }

    double getDeltaTime() {
        return deltaTime;
    }

    void increaseTicks() {
        ticks++;
    }

    void decreaseDeltaTime() {
        deltaTime--;
    }

    void printFPS() {
        if(timer >= 1000000000) {
            System.out.println(ticks);
            ticks = 0;
            timer = 0;
        }
    }
}
