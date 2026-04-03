package ua.edu.znu.flappybirdgame;

import java.util.*;
import java.awt.*;
import java.util.List;

/**
 * Клас {@code PipeManager} відповідає за керування трубами у грі Flappy Bird.
 */
public class PipeManager {
    /**
     * Максимальна кількість труб на екрані.
     */
    private static final int MAX_PIPES = 6;

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
        for (int i = 0; i < 4; i++) {
            addPipe(true);
        }
    }

    /**
     * Оновлює стан труб.
     */
    public void update() {
        pipeInstances.forEach(pipeInstance -> pipeInstance.move(10));
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
        final int pipeHeight = 50 + random.nextInt(300);
        final int pipeWidth = 100;
        final int pipeGap = 300;
        final int groundHeight = 120;
        final int distance = start ? 300 : 600;

        final int xPos = start ? gameWidth + pipeWidth + pipeInstances.size() * distance
                : pipeInstances.getLast().getX() + distance;


        pipeInstances.add(new PipeInstance(xPos, gameHeight - pipeHeight - groundHeight, pipeWidth, pipeHeight));
        pipeInstances.add(new PipeInstance(xPos, 0, pipeWidth, gameHeight - pipeHeight - pipeGap));
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
