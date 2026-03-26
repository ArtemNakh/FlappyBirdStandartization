package ua.edu.znu.flappybirdgame;

import java.awt.*;

public class GameState {
    private final int gameWidth, gameHeight;
    private Bird bird;
    private final PipeManager pipeManager;
    private final ScoreManager scoreManager;
    private int ticksCount;
    private boolean isGameOver;
    private boolean isGameStarted;

    public GameState(int width, int height) {
        this.gameWidth = width;
        this.gameHeight = height;
        pipeManager = new PipeManager(width, height);
        scoreManager = new ScoreManager();
        reset();
    }

    public void reset() {
        bird = new Bird(gameWidth / 2 - 10, gameHeight / 2 - 10, 20);
        pipeManager.reset();
        scoreManager.reset();
        ticksCount = 0;
        isGameOver = false;
        isGameStarted = false;
    }

    public void jump() {
        if (isGameOver) reset();
        if (!isGameStarted) isGameStarted = true;
        else bird.jump();
    }

    public void update() {
        ticksCount++;
        if (!isGameStarted) return;

        pipeManager.update();
        bird.fall(2, 15, ticksCount);

        for (Pipe pipe : pipeManager.getPipes()) {
            // зіткнення
            if (pipe.getShape().intersects(bird.getShape())) {
                isGameOver = true;
            }

            // підрахунок очок: коли пташка проходить центр верхньої труби
            int scoreZoneOffset = 10;
            Rectangle shape = pipe.getShape();
            if (shape.y == 0 && // беремо тільки верхні труби
                    bird.getShape().x + bird.getShape().width / 2 > shape.x + shape.width / 2 - scoreZoneOffset &&
                    bird.getShape().x + bird.getShape().width / 2 < shape.x + shape.width / 2 + scoreZoneOffset) {
                scoreManager.increment();
            }
        }

        if (bird.getShape().y > gameHeight - 120 || bird.getShape().y < 0) {
            isGameOver = true;
        }
    }


    public void draw(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(0, 0, gameWidth, gameHeight);

        g.setColor(Color.orange);
        g.fillRect(0, gameHeight - 120, gameWidth, 120);

        g.setColor(Color.green);
        g.fillRect(0, gameHeight - 120, gameWidth, 20);

        bird.draw(g);
        pipeManager.draw(g);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 80));

        if (!isGameStarted) g.drawString("Click to Begin", 75, gameHeight / 2 - 50);
        if (isGameOver) g.drawString("Game Over", 100, gameHeight / 2 - 50);
        if (!isGameOver && isGameStarted) g.drawString(String.valueOf(scoreManager.getScore()), gameWidth / 2 - 25, 100);
    }
}
