package ua.edu.znu.flappybirdgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Клас {@code PipeInstance} представляє трубу у грі Flappy Bird.
 * <p>
 * Відповідає за геометричну форму труби, її рух та відображення
 * на графічному контексті. Труби використовуються для створення перешкод,
 * які гравець має долати.
 * </p>
 */
public class PipeInstance {
    /** Геометрична форма труби. */
    private final Rectangle shape;

    /**
     * Конструктор створює нову трубу із заданими координатами та розмірами.
     *
     * @param xPos       координата X верхнього лівого кута труби
     * @param yPos       координата Y верхнього лівого кута труби
     * @param widthPipe  ширина труби
     * @param heightPipe висота труби
     */
    public PipeInstance(final int xPos, final int yPos,
                        final int widthPipe, final int heightPipe) {
        shape = new Rectangle(xPos, yPos, widthPipe, heightPipe);
    }

    /**
     * Перевіряє, чи перетинається труба з пташкою.
     *
     * @param birdInstance екземпляр {@link BirdInstance},
     *                     з яким перевіряється зіткнення
     * @return {@code true}, якщо є перетин; {@code false} інакше
     */
    public boolean intersects(final BirdInstance birdInstance) {
        return birdInstance.intersects(this.shape);
    }

    /**
     * Повертає координату X центру труби.
     *
     * @return значення X центру труби
     */
    public int getCenterX() {
        return shape.x + shape.width / 2;
    }

    /**
     * Перевіряє, чи є труба верхньою.
     *
     * @return {@code true}, якщо труба верхня; {@code false} інакше
     */
    public boolean isTopPipe() {
        return shape.y == 0;
    }

    /**
     * Перевіряє, чи знаходиться птах у зоні нарахування очок.
     *
     * @param currentBird поточний птах
     * @return {@code true}, якщо птах у зоні очок; {@code false} інакше
     */
    public boolean isBirdInScoreZone(final BirdInstance currentBird) {
        final int scoreZoneOffset = 10;
        return isTopPipe()
                && currentBird.isInScoreZone(getCenterX(), scoreZoneOffset);
    }

    /**
     * Повертає координату X труби.
     *
     * @return значення X труби
     */
    public int getX() {
        return shape.x;
    }

    /**
     * Повертає ширину труби.
     *
     * @return ширина труби
     */
    public int getWidth() {
        return shape.width;
    }

    /**
     * Переміщує трубу ліворуч на задану кількість пікселів.
     *
     * @param speed швидкість руху труби
     */
    public void move(final int speed) {
        shape.x -= speed;
    }

    /**
     * Повертає копію геометричної форми труби.
     * Використовується для перевірки зіткнень.
     *
     * @return новий об'єкт {@link Rectangle}, що описує форму труби
     */
    public Rectangle getShape() {
        return new Rectangle(shape);
    }

    /**
     * Малює трубу на графічному контексті.
     * Труба відображається у темно-зеленому кольорі.
     *
     * @param graphics графічний контекст для малювання
     */
    public void draw(final Graphics graphics) {
        graphics.setColor(Color.GREEN.darker().darker());
        graphics.fillRect(shape.x, shape.y, shape.width, shape.height);
    }
}
