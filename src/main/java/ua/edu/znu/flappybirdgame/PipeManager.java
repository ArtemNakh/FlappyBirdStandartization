package ua.edu.znu.flappybirdgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.awt.Graphics;

/**
 * Клас {@code PipeManager} відповідає за керування трубами у грі Flappy Bird.
 */
public class PipeManager {
    /**
     * Максимальна кількість труб на екрані.
     */
    private static final int MAX_PIPES = 6;

    /**
     * Початкова кількість труб при старті гри.
     */
    private static final int INITIAL_PIPES = 4;

    /**
     * Швидкість руху труб (пікселів за такт).
     */
    private static final int PIPE_SPEED = 10;

    /**
     * Мінімальна висота труби.
     */
    private static final int MIN_PIPE_HEIGHT = 50;

    /**
     * Максимальна додаткова висота труби (рандом).
     */
    private static final int PIPE_HEIGHT_VAR = 300;

    /**
     * Ширина труби.
     */
    private static final int PIPE_WIDTH = 100;

    /**
     * Відстань між верхньою та нижньою трубою (зазор).
     */
    private static final int PIPE_GAP = 300;

    /**
     * Висота землі.
     */
    private static final int GROUND_HEIGHT = 120;

    /**
     * Відстань між трубами при старті.
     */
    private static final int START_DISTANCE = 300;

    /**
     * Відстань між трубами під час гри.
     */
    private static final int GAME_DISTANCE = 600;

    /**
     * Список активних труб.
     */
    private final List<PipeInstance> pipeInstances = new ArrayList<>();

    /**
     * Генератор випадкових чисел для створення висоти труб.
     */
    private final Random random = new Random();

    /**
     * Ширина ігрового поля.
     */
    private final int gameWidth;

    /**
     * Висота ігрового поля.
     */
    private final int gameHeight;

    /**
     * Конструктор створює новий менеджер труб.
     *
     * @param width  ширина ігрового поля
     * @param height висота ігрового поля
     */
    public PipeManager(final int width, final int height) {
        this.gameWidth = width;
        this.gameHeight = height;
    }

    /**
     * Скидає стан труб.
     * Очищає список і додає початковий набір труб.
     */
    public void reset() {
        pipeInstances.clear();
        for (int i = 0; i < INITIAL_PIPES; i++) {
            addPipe(true);
        }
    }

    /**
     * Оновлює стан труб.
     */
    public void update() {
        pipeInstances.forEach(pipeInstance -> pipeInstance.move(PIPE_SPEED));
        pipeInstances.removeIf(pipeInstance ->
                pipeInstance.getX() + pipeInstance.getWidth() < 0);

        if (pipeInstances.size() < MAX_PIPES) {
            addPipe(false);
        }
    }

    /**
     * Додає нову пару труб (верхню та нижню).
     *
     * @param start якщо {@code true}, труби додаються на початку гри;
     *              якщо {@code false}, труби додаються після останньої труби
     */
    private void addPipe(final boolean start) {
        final int pipeHeight = MIN_PIPE_HEIGHT
                + random.nextInt(PIPE_HEIGHT_VAR);
        final int distance = start ? START_DISTANCE : GAME_DISTANCE;

        final int xPos = start
                ? gameWidth + PIPE_WIDTH + pipeInstances.size() * distance
                : pipeInstances.get(pipeInstances.size() - 1).getX() + distance;

        pipeInstances.add(new PipeInstance(
                xPos,
                gameHeight - pipeHeight - GROUND_HEIGHT,
                PIPE_WIDTH,
                pipeHeight));

        pipeInstances.add(new PipeInstance(
                xPos,
                0,
                PIPE_WIDTH,
                gameHeight - pipeHeight - PIPE_GAP));
    }

    /**
     * Повертає список активних труб у вигляді незмінної колекції.
     *
     * @return список труб
     */
    public List<PipeInstance> getPipes() {
        return Collections.unmodifiableList(pipeInstances);
    }

    /**
     * Малює всі труби на графічному контексті.
     *
     * @param graphics графічний контекст для малювання
     */
    public void draw(final Graphics graphics) {
        pipeInstances.forEach(pipeInstance -> pipeInstance.draw(graphics));
    }
}
