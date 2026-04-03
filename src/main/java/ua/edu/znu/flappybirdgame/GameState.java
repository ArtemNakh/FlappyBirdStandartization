package ua.edu.znu.flappybirdgame;

import java.awt.*;

/**
 * Клас {@code GameState} представляє поточний стан гри Flappy Bird.
 * <p>
 * Він відповідає за логіку гри: рух пташки, генерацію та оновлення труб,
 * підрахунок очок, перевірку зіткнень та відображення графіки.
 * </p>
 */
public class GameState {
    /**
     * Ширина ігрового поля.
     */
    private final int gameWidth;

    /**
     * Висота ігрового поля.
     */
    private final int gameHeight;

    /**
     * Ігрова пташка.
     */
    private BirdInstance birdInstance;

    /**
     * Менеджер труб, що відповідає за їх генерацію та рух.
     */
    private final PipeManager pipeManager;

    /**
     * Менеджер очок, що відповідає за підрахунок результату.
     */
    private final ScoreManager scoreManager;

    /**
     * Лічильник тактів гри.
     */
    private int ticksCount;

    /**
     * Ознака завершення гри.
     */
    private boolean isGameOver;

    /**
     * Ознака початку гри.
     */
    private boolean isGameStarted;

    /**
     * Конструктор створює новий стан гри з заданими розмірами.
     *
     * @param width  ширина ігрового поля
     * @param height висота ігрового поля
     */
    public GameState(final int width, final  int height) {
        this.gameWidth = width;
        this.gameHeight = height;
        pipeManager = new PipeManager(width, height);
        scoreManager = new ScoreManager();
        reset();
    }




    /**
     * Скидає стан гри до початкового.
     * Створює нову пташку, обнуляє очки та лічильники.
     */
    public final void reset() {
        birdInstance = new BirdInstance(gameWidth / 2 - 10, gameHeight / 2 - 10, 20);
        pipeManager.reset();
        scoreManager.reset();
        ticksCount = 0;
        isGameOver = false;
        isGameStarted = false;
    }

    /**
     * Виконує дію стрибка пташки.
     * Якщо гра завершена — виконує {@link #reset()}.
     * Якщо гра ще не почалася — запускає її.
     * Інакше викликає {@link BirdInstance#jump()}.
     */
    public void jump() {
        if (isGameOver) {
            reset();
        } else if (isGameStarted) {
            birdInstance.jump();
        } else {
            isGameStarted = true;
        }
    }


    public void update() {
        ticksCount++;
        if (!isGameStarted) return;

        updateBird();
        updatePipes();
        checkCollisions();
        checkOutOfBounds();
    }

    private void updateBird() {
        birdInstance.fall(2, 15, ticksCount);
    }

    private void updatePipes() {
        pipeManager.update();
    }

    private void checkCollisions() {
        for (PipeInstance pipe : pipeManager.getPipes()) {
            if (pipe.intersects(birdInstance)) {
                isGameOver = true;
            }
            if (pipe.isBirdInScoreZone(birdInstance)) {
                scoreManager.increment();
            }
        }
    }

    private void checkOutOfBounds() {
        if (birdInstance.isOutOfBounds(gameHeight)) {
            isGameOver = true;
        }
    }



    /**
     * Малює графічні елементи гри на екрані.
     *
     * @param graphics графічний контекст для малювання
     */
    public void draw(final Graphics graphics) {
        drawBackground(graphics);
        drawGround(graphics);
        drawGameObjects(graphics);
        drawText(graphics);
    }

    private void drawBackground(Graphics graphics) {
        graphics.setColor(Color.cyan);
        graphics.fillRect(0, 0, gameWidth, gameHeight);
    }

    private void drawGround(Graphics graphics) {
        graphics.setColor(Color.orange);
        graphics.fillRect(0, gameHeight - 120, gameWidth, 120);

        graphics.setColor(Color.green);
        graphics.fillRect(0, gameHeight - 120, gameWidth, 20);
    }

    private void drawGameObjects(Graphics graphics) {
        birdInstance.draw(graphics);
        pipeManager.draw(graphics);
    }

    private void drawText(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Arial", Font.BOLD, 80));

        if (!isGameStarted) {
            graphics.drawString("Click to Begin", 75, gameHeight / 2 - 50);
        } else if (isGameOver) {
            graphics.drawString("Game Over", 100, gameHeight / 2 - 50);
        } else {
            graphics.drawString(String.valueOf(scoreManager.getScore()), gameWidth / 2 - 25, 100);
        }
    }

}
