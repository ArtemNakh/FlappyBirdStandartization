package ua.edu.znu.flappybirdgame;

/**Клас {@code Main} є точкою входу у програму Flappy Bird.
 * <p>
 * Він містить метод {@link #main(String[])}, який запускає гру,
 * створюючи єдиний екземпляр {@link FlappyBirdGame} через метод
 * {@link FlappyBirdGame#getInstance()}.
 * </p>
 */
public final class MainClass {

    /**
     * Приватний конструктор, який забороняє створення екземпляра класу.
     */
     private MainClass() {
    }

    /**
     * Головний метод програми, який виконується при запуску.
     */
    public static void main(final String[] args) {
        FlappyBirdGame.getInstance();
    }
}
