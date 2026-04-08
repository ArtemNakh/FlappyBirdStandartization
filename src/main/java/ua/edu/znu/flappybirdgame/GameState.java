package ua.edu.znu.flappybirdgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Клас {@code GameState} представляє поточний стан гри Flappy Bird.
 * <p>
 * Відповідає за логіку гри: рух пташки, генерацію та оновлення труб,
 * підрахунок очок, перевірку зіткнень та відображення графіки.
 * </p>
 */
public class GameState {
    /**
     * Розмір пташки (ширина та висота у пікселях).
     */
    private static final int BIRD_SIZE = 20;

    /**
     * Зсув пташки від центру при початковому розташуванні.
     */
    private static final int BIRD_OFFSET = 10;

    /**
     * Сила гравітації, яка збільшує швидкість падіння.
     */
    private static final int GRAVITY = 2;

    /**
     * Максимальна швидкість падіння пташки.
     */
    private static final int MAX_FALL_SPEED = 15;

    /**
     * Висота землі у нижній частині ігрового поля.
     */
    private static final int GROUND_HEIGHT = 120;

    /**
     * Товщина верхнього шару землі (зелена трава).
     */
    private static final int GROUND_TOP = 20;

    /**
     * Розмір шрифту для текстових повідомлень.
     */
    private static final int FONT_SIZE = 80;

    /**
     * Горизонтальний відступ для текстових повідомлень.
     */
    private static final int TEXT_OFFSET_X = 75;

    /**
     * Вертикальний відступ для текстових повідомлень.
     */
    private static final int TEXT_OFFSET_Y = 50;

    /**
     * Горизонтальний відступ для відображення рахунку.
     */
    private static final int SCORE_OFFSET_X = 25;

    /**
     * Вертикальний відступ для відображення рахунку.
     */
    private static final int SCORE_OFFSET_Y = 100;


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
     * Менеджер труб.
     */
    private final PipeManager pipeManager;

    /**
     * Менеджер очок.
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
    public GameState(final int width, final int height) {
        this.gameWidth = width;
        this.gameHeight = height;
        pipeManager = new PipeManager(width, height);
        scoreManager = new ScoreManager();
        reset();
    }

    /**
     * Скидає стан гри до початкового.
     */
    public final void reset() {
        birdInstance = new BirdInstance(gameWidth / 2 - BIRD_OFFSET,
                gameHeight / 2 - BIRD_OFFSET, BIRD_SIZE);
        pipeManager.reset();
        scoreManager.reset();
        ticksCount = 0;
        isGameOver = false;
        isGameStarted = false;
    }

    /**
     * Виконує дію стрибка пташки.
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

    /**
     * Оновлює стан гри.
     */
    public void update() {
        ticksCount++;
        if (!isGameStarted) {
            return;
        }

        birdInstance.fall(GRAVITY, MAX_FALL_SPEED, ticksCount);
        pipeManager.update();
        checkCollisions();
        checkOutOfBounds();
    }

    /**
     * Перевіряє зіткнення птаха з трубами та нарахування очок.
     */
    private void checkCollisions() {
        for (final PipeInstance pipe : pipeManager.getPipes()) {
            if (pipe.intersects(birdInstance)) {
                isGameOver = true;
            }
            if (pipe.isBirdInScoreZone(birdInstance)) {
                scoreManager.increment();
            }
        }
    }

    /**
     * Перевіряє, чи птах вийшов за межі ігрового поля.
     */
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

    private void drawBackground(final Graphics graphics) {
        graphics.setColor(Color.CYAN);
        graphics.fillRect(0, 0, gameWidth, gameHeight);
    }

    private void drawGround(final Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(0, gameHeight - GROUND_HEIGHT,
                gameWidth, GROUND_HEIGHT);

        graphics.setColor(Color.GREEN);
        graphics.fillRect(0, gameHeight - GROUND_HEIGHT, gameWidth, GROUND_TOP);
    }

    private void drawGameObjects(final Graphics graphics) {
        birdInstance.draw(graphics);
        pipeManager.draw(graphics);
    }

    private void drawText(final Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));

        if (!isGameStarted) {
            graphics.drawString("Click to Begin",
                    TEXT_OFFSET_X, gameHeight / 2 - TEXT_OFFSET_Y);
        }
        if (isGameOver) {
            graphics.drawString("Game Over",
                    TEXT_OFFSET_X + SCORE_OFFSET_X,
                    gameHeight / 2 - TEXT_OFFSET_Y);
        } else {
            graphics.drawString(String.valueOf(scoreManager.getScore()),
                    gameWidth / 2 - SCORE_OFFSET_X, SCORE_OFFSET_Y);
        }
    }
}
