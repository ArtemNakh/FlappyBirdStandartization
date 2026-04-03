package ua.edu.znu.flappybirdgame;

import java.util.*;
import java.awt.*;
import java.util.List;

public class PipeManager {
    private final ArrayList<Pipe> pipes = new ArrayList<>();
    private final Random random = new Random();
    private final int gameWidth, gameHeight;

    public PipeManager(int width, int height) {
        this.gameWidth = width;
        this.gameHeight = height;
    }

    public void reset() {
        pipes.clear();
        for (int i = 0; i < 4; i++) addPipe(true);
    }

    public void update() {
        pipes.forEach(pipe -> pipe.move(10));
        pipes.removeIf(pipe -> pipe.getShape().x + pipe.getShape().width < 0);
        if (pipes.size() < 6) addPipe(false);
    }

    private void addPipe(boolean start) {
        int pipeHeight = 50 + random.nextInt(300);
        int pipeWidth = 100;
        int pipeGap = 300;
        int groundHeight = 120;
        int distance = start ? 300 : 600;

        int x = start ? gameWidth + pipeWidth + pipes.size() * distance
                : pipes.get(pipes.size() - 1).getShape().x + distance;

        pipes.add(new Pipe(x, gameHeight - pipeHeight - groundHeight, pipeWidth, pipeHeight));
        pipes.add(new Pipe(x, 0, pipeWidth, gameHeight - pipeHeight - pipeGap));
    }

    public List<Pipe> getPipes() {
        return Collections.unmodifiableList(pipes);
    }

    public void draw(Graphics g) {
        pipes.forEach(pipe -> pipe.draw(g));
    }
}
