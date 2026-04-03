package ua.edu.znu.flappybirdgame;

/**Клас {@code FlappyBirdGame}
 * є основним керуючим класом гри Flappy Bird.
 */
public final class FlappyBirdGame {

    /** Константа ширини ігрового поля. */
    private static final int GAME_WIDTH = 800;

    /** Константа висоти ігрового поля. */
    private static final int GAME_HEIGHT = 800;

    /** Статичний екземпляр Singleton. */
    private static final FlappyBirdGame INSTANCE = new FlappyBirdGame();


//    /** Єдиний екземпляр гри (Singleton). */
//    private static FlappyBirdGame gameInstance;

    /** Стан гри, що містить логіку та дані. */
    private final GameState gameState;

    /** Ігрове вікно, яке відповідає за графічне відображення. */
    private final GameWindow gameWindow;


    /**
     * Приватний конструктор створює новий екземпляр гри.
     * Ініціалізує стан гри та ігрове вікно.
     */
    private FlappyBirdGame() {
        gameState = new GameState(GAME_WIDTH, GAME_HEIGHT);
        gameWindow = new GameWindow(this, gameState);
    }


    /**
     * Повертає єдиний екземпляр гри.
     *
     * @return екземпляр {@link FlappyBirdGame}
     */
    public static FlappyBirdGame getInstance() {
        return INSTANCE;
    }

    /**
     * Оновлює стан гри та виконує перемальовування вікна.
     */
    public void updateGame() {
        gameState.update();
        gameWindow.repaint();
    }

    /**
     * Виконує дію стрибка пташки.
     * Викликає метод {@link GameState#jump()}.
     */
    public void jump() {
        gameState.jump();
    }
}
