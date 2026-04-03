package ua.edu.znu.flappybirdgame;

import java.awt.*;

/**
 * Клас {@code Bird} представляє пташку у грі Flappy Bird.
 * Відповідає за її рух (стрибок та падіння) та відображення на екрані.
 */
public class BirdInstance {
    /**
     * Геометрична форма пташки,
     * яка використовується для відображення та перевірки зіткнень.
     */
    private Rectangle shape;

    /**
     * Вертикальна швидкість руху
     * пташки (негативне значення — стрибок, позитивне — падіння).
     */
    private int verticalMotion;

    /**
     * Конструктор створює нову пташку із заданими координатами та розміром.
     *
     * @param xPos    початкова координата X
     * @param yPos    початкова координата Y
     * @param sizeBird розмір пташки (ширина та висота)
     */
    public BirdInstance(final int xPos, final  int yPos, final  int sizeBird) {
        shape = new Rectangle(xPos, yPos, sizeBird, sizeBird);
    }
    /**
     * Повертає координату X центру об'єкта.
     *
     * @return значення X центру
     */
    public int getCenterX() {
        return shape.x + shape.width / 2;
    }

    /**
     * Повертає координату Y верхнього лівого кута об'єкта.
     *
     * @return значення Y
     */
    public int getY() {
        return shape.y;
    }

    /**
     * Виконує стрибок пташки.
     * Якщо вона вже падає, швидкість падіння обнуляється,
     * після чого додається імпульс стрибка.
     */
    public void jump() {
        if (verticalMotion > 0) {
            verticalMotion = 0;
        }
        verticalMotion -= 10;
    }

    /**
     * Оновлює стан падіння пташки під дією гравітації.
     *
     * @param gravity      сила гравітації (збільшення швидкості падіння)
     * @param maxFallSpeed максимальна швидкість падіння
     * @param tick         номер такту гри (для контролю частоти оновлення)
     */
    public void fall(final int gravity, final int maxFallSpeed,
                     final int tick) {
        if (tick % 2 == 0 && verticalMotion < maxFallSpeed) {
            verticalMotion += gravity;
        }
        shape.y += verticalMotion;
    }

    /**
     * Повертає копію геометричної форми пташки.
     * Використовується для перевірки зіткнень.
     *
     * @return новий об'єкт {@link Rectangle}, що описує форму пташки
     */
    public Rectangle getShape() {
        return new Rectangle(shape);
    }

    /**
     * Скидає стан пташки до початкових координат і розміру.
     *
     * @param xPos    нова координата X
     * @param yPos    нова координата Y
     * @param sizeBird новий розмір пташки
     */
    public void reset(final int xPos, final int yPos, final int sizeBird) {
        shape = new Rectangle(xPos, yPos, sizeBird, sizeBird);
        verticalMotion = 0;
    }

    /**
     * Малює пташку на графічному контексті.
     *
     * @param graphics графічний контекст, який використовується для малювання
     */
    public void draw(final Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillRect(shape.x, shape.y, shape.width, shape.height);
    }
}
