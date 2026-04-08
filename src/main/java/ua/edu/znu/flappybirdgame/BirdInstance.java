//package ua.edu.znu.flappybirdgame;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Rectangle;
//
///**
// * Клас {@code Bird} представляє пташку у грі Flappy Bird.
// * Відповідає за її рух (стрибок та падіння) та відображення на екрані.
// */
//public class BirdInstance {
//
//    /** Імпульс стрибка (зменшення швидкості). */
//    private static final int JUMP_IMPULSE = 10;
//
//    /** Допустиме відхилення від нижньої межі поля. */
//    private static final int OUT_OF_BOUNDS_OFFSET = 120;
//
//    /**
//     * Геометрична форма пташки,
//     * яка використовується для відображення та перевірки зіткнень.
//     */
//    private Rectangle shape;
//
//    /**
//     * Вертикальна швидкість руху
//     * пташки (негативне значення — стрибок, позитивне — падіння).
//     */
//    private int verticalMotion;
//
//    /**
//     * Конструктор створює нову пташку із заданими координатами та розміром.
//     *
//     * @param xPos     початкова координата X
//     * @param yPos     початкова координата Y
//     * @param sizeBird розмір пташки (ширина та висота)
//     */
//    public BirdInstance(final int xPos, final int yPos, final int sizeBird) {
//        shape = new Rectangle(xPos, yPos, sizeBird, sizeBird);
//    }
//
//    /**
//     * Повертає координату X центру об'єкта.
//     *
//     * @return значення X центру
//     */
//    public int getCenterX() {
//        return shape.x + shape.width / 2;
//    }
//
//    /**
//     * Повертає координату Y верхнього лівого кута об'єкта.
//     *
//     * @return значення Y
//     */
//    public int getY() {
//        return shape.y;
//    }
//
//    /**
//     * Виконує стрибок пташки.
//     * Якщо вона вже падає, швидкість падіння обнуляється,
//     * після чого додається імпульс стрибка.
//     */
//    public void jump() {
//        if (verticalMotion > 0) {
//            verticalMotion = 0;
//        }
//        verticalMotion -= 10;
//    }
//
//    /**
//     * Оновлює стан падіння пташки під дією гравітації.
//     *
//     * @param gravity      сила гравітації (збільшення швидкості падіння)
//     * @param maxFallSpeed максимальна швидкість падіння
//     * @param tick         номер такту гри (для контролю частоти оновлення)
//     */
//    public void fall(final int gravity, final int maxFallSpeed,
//                     final int tick) {
//        if (tick % 2 == 0 && verticalMotion < maxFallSpeed) {
//            verticalMotion += gravity;
//        }
//        shape.y += verticalMotion;
//    }
//
//    /**
//     * Перевірка за виход зони.
//     */
//    public boolean isOutOfBounds(final int gameHeight) {
//        return getY() > gameHeight - 120 || getY() < 0;
//    }
//
//    /**
//     * Перевіряє, чи птах перетинається з переданою геометричною формою труби.
//     *
//     * @param pipeShape геометрична форма труби,
//     з якою перевіряється зіткнення
//     * @return {@code true}, якщо птах перетинається з трубою;
//     * {@code false} інакше
//     */
//    public boolean intersects(final Rectangle pipeShape) {
//        return this.shape.intersects(pipeShape);
//    }
//
//    /**
//     * Перевіряє, чи знаходиться птах у зоні нарахування очок.
//     *
//     * @param pipeCenterX координата X центру труби
//     * @param offset      допустиме відхилення від центру труби
//     * @return {@code true}, якщо птах знаходиться у межах зони очок;
//     * {@code false} інакше
//     */
//    public boolean isInScoreZone(final int pipeCenterX, final int offset) {
//        return this.getCenterX() > pipeCenterX - offset
//                && this.getCenterX() < pipeCenterX + offset;
//    }
//
//
//    /**
//     * Повертає копію геометричної форми пташки.
//     * Використовується для перевірки зіткнень.
//     *
//     * @return новий об'єкт {@link Rectangle}, що описує форму пташки
//     */
//    public Rectangle getShape() {
//        return new Rectangle(shape);
//    }
//
//    /**
//     * Скидає стан пташки до початкових координат і розміру.
//     *
//     * @param xPos     нова координата X
//     * @param yPos     нова координата Y
//     * @param sizeBird новий розмір пташки
//     */
//    public void reset(final int xPos, final int yPos, final int sizeBird) {
//        shape = new Rectangle(xPos, yPos, sizeBird, sizeBird);
//        verticalMotion = 0;
//    }
//
//    /**
//     * Малює пташку на графічному контексті.
//     *
//     * @param graphics графічний контекст, який використовується для малювання
//     */
//    public void draw(final Graphics graphics) {
//        graphics.setColor(Color.red);
//        graphics.fillRect(shape.x, shape.y, shape.width, shape.height);
//    }
//}

package ua.edu.znu.flappybirdgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Клас {@code BirdInstance} представляє пташку у грі Flappy Bird.
 * Відповідає за її рух (стрибок та падіння) та відображення на екрані.
 */
public class BirdInstance {
    /** Імпульс стрибка (зменшення швидкості). */
    private static final int JUMP_IMPULSE = 10;

    /** Допустиме відхилення від нижньої межі поля. */
    private static final int OUT_BOUNDS_OFFSET = 120;

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
     * @param xPos     початкова координата X
     * @param yPos     початкова координата Y
     * @param sizeBird розмір пташки (ширина та висота)
     */
    public BirdInstance(final int xPos, final int yPos, final int sizeBird) {
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
     * @return значення Y верхнього лівого кута
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
        verticalMotion -= JUMP_IMPULSE;
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
     * Перевіряє, чи пташка вийшла за межі ігрового поля.
     *
     * @param gameHeight висота ігрового поля
     * @return {@code true}, якщо пташка вийшла за межі;
     * {@code false} інакше
     */
    public boolean isOutOfBounds(final int gameHeight) {
        return getY() > gameHeight - OUT_BOUNDS_OFFSET || getY() < 0;
    }

    /**
     * Перевіряє, чи птах перетинається з переданою геометричною формою труби.
     *
     * @param pipeShape геометрична форма труби, з якою перевіряється зіткнення
     * @return {@code true}, якщо птах перетинається з трубою;
     * {@code false} інакше
     */
    public boolean intersects(final Rectangle pipeShape) {
        return this.shape.intersects(pipeShape);
    }

    /**
     * Перевіряє, чи знаходиться птах у зоні нарахування очок.
     *
     * @param pipeCenterX координата X центру труби
     * @param offset      допустиме відхилення від центру труби
     * @return {@code true}, якщо птах знаходиться у межах зони очок;
     * {@code false} інакше
     */
    public boolean isInScoreZone(final int pipeCenterX, final int offset) {
        return this.getCenterX() > pipeCenterX - offset
                && this.getCenterX() < pipeCenterX + offset;
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
     * @param xPos     нова координата X
     * @param yPos     нова координата Y
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
        graphics.setColor(Color.RED);
        graphics.fillRect(shape.x, shape.y, shape.width, shape.height);
    }
}
