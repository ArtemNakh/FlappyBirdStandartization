package ua.edu.znu.flappybirdGame;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;


/**
 * Клас {@code FlappyBirdGame} реалізує основну логіку гри Flappy Bird.
 * <p>
 * Він відповідає за:
 * <ul>
 *   <li>Ініціалізацію вікна гри та графічного рендерера.</li>
 *   <li>Створення та рух пташки та труб.</li>
 *   <li>Обробку подій клавіатури та миші.</li>
 *   <li>Малювання ігрової сцени та відображення повідомлень.</li>
 *   <li>Перевірку зіткнень та підрахунок очок.</li>
 * </ul>
 * </p>
 *
 * <h2>Особливості:</h2>
 * <ul>
 *   <li>Використовує {@link Timer} для циклу оновлення гри.</li>
 *   <li>Реалізує інтерфейси {@link ActionListener}, {@link MouseListener}, {@link KeyListener} для обробки подій.</li>
 *   <li>Має статичний екземпляр {@code gameInstance} для доступу з класу {@link GameRenderer}.</li>
 * </ul>
 *
 * <h2>Приклад запуску:</h2>
 * <pre>{@code
 * public static void main(String[] args) {
 *     FlappyBirdGame.gameInstance = new FlappyBirdGame();
 * }
 * }</pre>
 *
 */

public class FlappyBirdGame implements ActionListener, MouseListener, KeyListener {

    /**
     * Статичний екземпляр гри для доступу з інших класів.
     */
    public static FlappyBirdGame gameInstance;

    /**
     * Рендерер для відображення графіки.
     */
    private GameRenderer gameRenderer;

    /**
     * Пташка, представлена як прямокутник.
     */
    private Rectangle bird;

    /**
     * Лічильник тікiв таймера.
     * Вертикальний рух пташки.
     * Поточний рахунок гравця.
     */
    private int ticksCount, verticalMotion, score;

    /**
     * Список труб (перешкод).
     */
    private ArrayList<Rectangle> pipes;

    /**
     * Генератор випадкових чисел для висоти труб.
     */
    private Random randomGenerator;
    /**
     * Прапорець завершення гри.
     * Прапорець початку гри.
     */
    private boolean isGameOver, isGameStarted;

    /** Розміри вікна гри. */
    private final int GAME_WIDTH = 800, GAME_HEIGHT = 800;


    /**
     * Конструктор гри. Ініціалізує вікно, рендерер, пташку та труби.
     * Запускає таймер для оновлення стану гри.
     */
    public FlappyBirdGame() {

        JFrame gameWindow = new JFrame();
        Timer gameTimer = new Timer(20, this);
        gameRenderer = new GameRenderer();
        randomGenerator = new Random();

        gameWindow.add(gameRenderer);
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setSize(GAME_WIDTH, GAME_HEIGHT);
        gameWindow.addMouseListener(this);
        gameWindow.addKeyListener(this);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Flappy Bird");
        gameWindow.setVisible(true);

        bird = new Rectangle(GAME_WIDTH / 2 - 10, GAME_HEIGHT / 2 - 10, 20, 20);
        pipes = new ArrayList<Rectangle>();

        addPipe(true);
        addPipe(true);
        addPipe(true);
        addPipe(true);

        gameTimer.start();
    }


    /**
     * Додає нові труби у гру.
     *
     * @param isStartingGame якщо {@code true}, труби додаються для початкової сцени;
     *                       якщо {@code false}, додаються після проходження.
     */
    private void addPipe(boolean isStartingGame) {
        final int PIPE_GAP = 300;              // Відстань між верхньою та нижньою трубою
        final int PIPE_WIDTH = 100;            // Ширина труби
        final int PIPE_MIN_HEIGHT = 50;        // Мінімальна висота труби
        final int PIPE_MAX_RANDOM_HEIGHT = 300;// Додаткова випадкова висота
        final int GROUND_HEIGHT = 120;         // Висота землі
        final int PIPE_DISTANCE = 300;         // Відстань між трубами при старті
        final int PIPE_DISTANCE_AFTER = 600;   // Відстань між трубами після старту

        int pipeHeight = PIPE_MIN_HEIGHT + randomGenerator.nextInt(PIPE_MAX_RANDOM_HEIGHT);

        if (isStartingGame) {
            pipes.add(new Rectangle(
                    GAME_WIDTH + PIPE_WIDTH + pipes.size() * PIPE_DISTANCE,
                    GAME_HEIGHT - pipeHeight - GROUND_HEIGHT,
                    PIPE_WIDTH,
                    pipeHeight));

            pipes.add(new Rectangle(
                    GAME_WIDTH + PIPE_WIDTH + (pipes.size() - 1) * PIPE_DISTANCE,
                    0,
                    PIPE_WIDTH,
                    GAME_HEIGHT - pipeHeight - PIPE_GAP));
        } else {
            pipes.add(new Rectangle(
                    pipes.get(pipes.size() - 1).x + PIPE_DISTANCE_AFTER,
                    GAME_HEIGHT - pipeHeight - GROUND_HEIGHT,
                    PIPE_WIDTH,
                    pipeHeight));

            pipes.add(new Rectangle(
                    pipes.get(pipes.size() - 1).x,
                    0,
                    PIPE_WIDTH,
                    GAME_HEIGHT - pipeHeight - PIPE_GAP));
        }
    }

    /**
     * Малює трубу.
     *
     * @param graphics об’єкт {@link Graphics} для малювання
     * @param pipe прямокутник, що представляє трубу
     */
    public void drawPipe(Graphics graphics, Rectangle pipe) {

        graphics.setColor(Color.green.darker().darker());
        graphics.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
    }

    /**
     * Виконує стрибок пташки. Якщо гра завершена — перезапускає її.
     */
    public void jump() {
        final int BIRD_SIZE = 20;                  // Розмір пташки (ширина та висота)
        final int BIRD_OFFSET_X = 10;              // Зсув по X від центру
        final int BIRD_OFFSET_Y = 10;              // Зсув по Y від центру
        final int JUMP_STRENGTH = 10;              // Сила стрибка (наскільки зменшується yMotion)
        final int INITIAL_PIPES_COUNT = 4;         // Кількість труб при перезапуску

        if (isGameOver) {
            bird = new Rectangle(
                    GAME_WIDTH / 2 - BIRD_OFFSET_X,
                    GAME_HEIGHT / 2 - BIRD_OFFSET_Y,
                    BIRD_SIZE,
                    BIRD_SIZE);

            pipes.clear();
            verticalMotion = 0;
            score = 0;

            for (int i = 0; i < INITIAL_PIPES_COUNT; i++) {
                addPipe(true);
            }

            isGameOver = false;
        }

        if (!isGameStarted) {
            isGameStarted = true;
        } else if (!isGameOver) {
            if (verticalMotion > 0) {
                verticalMotion = 0;
            }
            verticalMotion -= JUMP_STRENGTH;
        }
    }

    /**
     * Основний цикл оновлення гри. Викликається таймером кожні 20 мс.
     *
     * @param event подія таймера
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        final int PIPE_SPEED = 10;                  // Швидкість руху труб
        final int GRAVITY_ACCELERATION = 2;         // Прискорення падіння пташки
        final int GRAVITY_TICK_INTERVAL = 2;        // Кожні 2 тики додається прискорення
        final int MAX_FALL_SPEED = 15;              // Максимальна швидкість падіння
        final int SCORE_ZONE_OFFSET = 10;           // Допуск для зони підрахунку очок
        final int GROUND_HEIGHT = 120;              // Висота землі

        ticksCount++;

        if (isGameStarted) {
            // Рух труб
            for (Rectangle pipe : pipes) {
                pipe.x -= PIPE_SPEED;
            }

            // Падіння пташки під дією "гравітації"
            if (ticksCount % GRAVITY_TICK_INTERVAL == 0 && verticalMotion < MAX_FALL_SPEED) {
                verticalMotion += GRAVITY_ACCELERATION;
            }

            // Видалення труб, що вийшли за межі
            for (int i = 0; i < pipes.size(); i++) {
                Rectangle pipe = pipes.get(i);
                if (pipe.x + pipe.width < 0) {
                    pipes.remove(pipe);
                    if (pipe.y == 0) {
                        addPipe(false);
                    }
                }
            }

            // Рух пташки
            bird.y += verticalMotion;

            // Перевірка зіткнень
            for (Rectangle pipe : pipes) {
                if (pipe.y == 0 &&
                        bird.x + bird.width / 2 > pipe.x + pipe.width / 2 - SCORE_ZONE_OFFSET &&
                        bird.x + bird.width / 2 < pipe.x + pipe.width / 2 + SCORE_ZONE_OFFSET) {
                    score++;
                }

                if (pipe.intersects(bird)) {
                    isGameOver = true;

                    if (bird.x <= pipe.x) {
                        bird.x = pipe.x - bird.width;
                    } else {
                        if (pipe.y != 0) {
                            bird.y = pipe.y - bird.height;
                        } else if (bird.y < pipe.height) {
                            bird.y = pipe.height;
                        }
                    }
                }
            }

            // Перевірка виходу за межі поля
            if (bird.y > GAME_HEIGHT - GROUND_HEIGHT || bird.y < 0) {
                isGameOver = true;
            }

            // Корекція положення пташки при падінні на землю
            if (bird.y + verticalMotion >= GAME_HEIGHT - GROUND_HEIGHT) {
                bird.y = GAME_HEIGHT - GROUND_HEIGHT - bird.height;
            }
        }
        gameRenderer.repaint();
    }


    /**
     * Малює всю сцену гри: фон, землю, труби, пташку та текстові повідомлення.
     *
     * @param graphics об’єкт {@link Graphics} для малювання
     */
    public void repaint(Graphics graphics) {
        graphics.setColor(Color.cyan);
        graphics.fillRect(0, 0, GAME_HEIGHT, GAME_WIDTH);

        graphics.setColor(Color.orange);
        graphics.fillRect(0, GAME_HEIGHT - 120, GAME_WIDTH, 120);

        graphics.setColor(Color.green);
        graphics.fillRect(0, GAME_HEIGHT - 120, GAME_WIDTH, 20);

        graphics.setColor(Color.red);
        graphics.fillRect(bird.x, bird.y, bird.height, bird.width);

        for (Rectangle column : pipes) {

            drawPipe(graphics, column);
        }

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Arial", 1, 80));

        if (!isGameStarted) {

            graphics.drawString("Click to Begin", 75, GAME_HEIGHT / 2 - 50);
        }

        if (isGameOver) {

            graphics.drawString("Game Over", 100, GAME_HEIGHT / 2 - 50);
        }

        if (!isGameOver && isGameStarted) {

            graphics.drawString(String.valueOf(score), GAME_WIDTH / 2 - 25, 100);
        }
    }

    /**
     * Обробка кліку миші — виконує стрибок.
     *
     * @param e подія миші
     */
    public void mouseClicked(MouseEvent e) {

        jump();
    }


    /**
     * Обробка натискання клавіші. Якщо натиснуто пробіл — виконує стрибок.
     *
     * @param e подія клавіатури
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump();
        }
    }


    public void keyTyped(KeyEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    /**
     * Точка входу у програму. Створює екземпляр гри.
     *
     * @param args аргументи командного рядка
     * @throws Exception якщо виникає помилка при запуску
     */
    public static void main(String[] args) throws Exception {

        gameInstance = new FlappyBirdGame();
    }
}