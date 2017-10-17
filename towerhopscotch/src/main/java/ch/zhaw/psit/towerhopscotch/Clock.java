package ch.zhaw.psit.towerhopscotch;

public class Clock {
    private static int FPS = 60;
    private double timePerTick = 1000000000 / FPS;
    private double deltaTime;
    private long lastTime, timer;
    private int ticks;

    public Clock() {
        lastTime = System.nanoTime();
        deltaTime = 0;
        timer = 0;
        ticks = 0;
    }

    public void tick() {
        long currentTime = System.nanoTime();
        deltaTime += (currentTime - lastTime) / timePerTick;
        timer += currentTime - lastTime;
        lastTime = currentTime;
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    public void increaseTicks() {
        ticks++;
    }

    public void decreaseDeltaTime() {
        deltaTime--;
    }

    public void printFPS() {
        if(timer >= 1000000000) {
            System.out.println(ticks);
            ticks = 0;
            timer = 0;
        }
    }
}
