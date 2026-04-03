package ua.edu.znu.flappybirdgame;


import javax.swing.*;

/** Клас {@code GameWindow} відповідає за створення та відображення
 * основного графічного вікна гри Flappy Bird.
 */
public class GameWindow {
    /** Основне вікно гри. */
    private final JFrame frame;

    /** Панель рендерингу, яка відповідає за графічне відображення. */
    private final GameRenderer renderer;

    /** Ігровий таймер, що керує циклом оновлення гри. */
    private final Timer gameTimer;

    /**Конструктор створює нове ігрове вікно.
     * @param game  екземпляр {@link FlappyBirdGame}, який керує логікою гри
     * @param state екземпляр {@link GameState}, що містить поточний стан гри
     */
    public GameWindow(final FlappyBirdGame game, final GameState state) {
        frame = new JFrame("Flappy Bird");
        renderer = new GameRenderer(state);

        // таймер з окремим GameLoopHandler
        gameTimer = new Timer(20, new GameLoopHandler(game));

        frame.add(renderer);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        // слухачі
        frame.addMouseListener(new MouseInputHandler(game));
        frame.addKeyListener(new KeyboardInputHandler(game));

        frame.setResizable(false);
        frame.setVisible(true);

        gameTimer.start();
    }

    /**
     * Виконує перемальовування ігрового вікна.
     * Викликає метод {@link GameRenderer#repaint()}.
     */
    public void repaint() {
        renderer.repaint();
    }
}
