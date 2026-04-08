package ua.edu.znu.flappybirdgame;

/**
 * Клас {@code MainClass} є точкою входу у програму Flappy Bird.
 * <p>
 * Він містить головний метод {@link #main(String[])}, який
 * ініціалізує єдиний екземпляр гри {@link FlappyBirdGame}.
 * </p>
 */
public final class MainClass {

    /**
     * Приватний конструктор, який забороняє створення екземпляра класу.
     */
    private MainClass() {
        // Заборонено створювати екземпляри цього класу
    }

    /**
     * Головний метод програми, який виконується при запуску.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(final String[] args) {
        new FlappyBirdGame();
    }
}
