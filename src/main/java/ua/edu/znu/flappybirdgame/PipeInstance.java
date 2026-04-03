package ua.edu.znu.flappybirdgame;

import java.awt.*;

/**
 * Клас {@code Pipe} представляє трубу у грі Flappy Bird.
 * <p>
 * Він відповідає за геометричну форму труби, її рух та відображення
 * на графічному контексті. Труби використовуються для створення перешкод,
 * які гравець має долати.
 * </p>
 */
public class PipeInstance {
    /**
     * Геометрична форма труби, яка
     * використовується для відображення та перевірки зіткнень.
     */
    private final Rectangle shape;

    /**
     * Конструктор створює нову трубу із заданими координатами та розмірами.
     *
     * @param xPos      координата X верхнього лівого кута труби
     * @param yPos      координата Y верхнього лівого кута труби
     * @param widthPipe  ширина труби
     * @param heightPipe висота труби
     */
    public PipeInstance(final int xPos, final int yPos, final int widthPipe, final int heightPipe) {
        shape = new Rectangle(xPos, yPos, widthPipe, heightPipe);
    }

    /**
     * Перевіряє, чи перетинається труба з пташкою.
     *
     * @param birdInstance екземпляр {@link BirdInstance},
     *                    з яким перевіряється зіткнення
     * @return {@code true}, якщо є перетин; {@code false} інакше
     */
    public boolean intersects(final BirdInstance birdInstance) {
        return shape.intersects(birdInstance.getShape());
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
     * Повертає значення X труби.
     */
    public int getX() {
        return shape.x;
    }
/**
            * Повертає значення ширини труби.
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
        graphics.setColor(Color.green.darker().darker());
        graphics.fillRect(shape.x, shape.y, shape.width, shape.height);
    }
}
