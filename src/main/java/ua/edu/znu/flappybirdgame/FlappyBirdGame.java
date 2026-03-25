/**
 * Пакет містить реалізацію гри Flappy Bird.
 */
package ua.edu.znu.flappybirdgame;



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
 *   <li>Реалізує інтерфейси {@link ActionListener}
 *   , {@link MouseListener}, {@link KeyListener} для обробки подій.</li>
 *   <li>Має статичний екземпляр {@code gameInstance}
 *   , для доступу з класу {@link GameRenderer}.</li>
 * </ul>
 * <h2>Приклад запуску:</h2>
 * <pre>{@code
 * public static void main(String[] args) {
 *     FlappyBirdGame.gameInstance = new FlappyBirdGame();
 * }
 * }</pre>
 */

public class FlappyBirdGame
        implements ActionListener, MouseListener, KeyListener {

    /**
     * Статичний екземпляр гри для доступу з інших класів.
     */
    private static FlappyBirdGame gameInstance;



    /**
     * Рендерер для відображення графіки.
     */
    private final GameRenderer gameRenderer;

    /**
     * Пташка, представлена як прямокутник.
     */
    private Rectangle bird;

    /**
     * Лічильник тікiв таймера.
     */
    private int ticksCount;
    /**
     * Вертикальний рух пташки.
     */
    private int verticalMotion;
    /**
     * Поточний рахунок гравця.
     */
    private int score;

    /**
     * Список труб (перешкод).
     */
    private final ArrayList<Rectangle> pipes;

    /**
     * Генератор випадкових чисел для висоти труб.
     */
    private final Random randomGenerator;
    /**
     * Прапорець завершення гри.
     */
    private boolean isGameOver;
    /**
     * Прапорець початку гри.
     */
    private boolean isGameStarted;

    /**
     * Розміри вікна гри.
     */
    private final int gameWidth = 800;
    /**
     * Розміри вікна гри.
     */
    private final int gameHeight = 800;


    /**
     * Конструктор гри. Ініціалізує вікно, рендерер, пташку та труби.
     * Запускає таймер для оновлення стану гри.
     */
    public FlappyBirdGame() {
        JFrame gameWindow = new JFrame();
        final int timerDelay = 20;
        Timer gameTimer = new Timer(timerDelay, this);
        gameRenderer = new GameRenderer();
        randomGenerator = new Random();

        gameWindow.add(gameRenderer);
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setSize(gameWidth, gameHeight);
        gameWindow.addMouseListener(this);
        gameWindow.addKeyListener(this);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Flappy Bird");
        gameWindow.setVisible(true);

        // Константи для розміру пташки
        final int birdWidth = 20;
        final int birdHeight = 20;
        final int birdOffset = 10;


        // Створення прямокутника
        bird = new Rectangle(
                gameWidth / 2 - birdOffset,
                gameHeight / 2 - birdOffset,
                birdWidth,
                birdHeight
        );

        pipes = new ArrayList<>();

        addPipe(true);
        addPipe(true);
        addPipe(true);
        addPipe(true);

        gameTimer.start();
    }


    /**
     * Повертає екземпляр гри FlappyBirdGame.
     *
     * @return поточний екземпляр гри
     */
    public static FlappyBirdGame getGameInstance() {
        return gameInstance;
    }

    /**
     * Встановлює екземпляр гри FlappyBirdGame.
     *
     * @param newGameInstance новий екземпляр гри
     */
    public void setGameInstance(final FlappyBirdGame newGameInstance) {
        this.gameInstance = newGameInstance;
    }



    /**
     * Додає нові труби у гру.
     *
     * @param isStartingGame якщо {@code true},
     *                      труби додаються для початкової сцени
     */
    private void addPipe(final boolean isStartingGame) {
        int pipeHeight = generatePipeHeight();

        if (isStartingGame) {
            addStartingPipes(pipeHeight);
        } else {
            addNextPipes(pipeHeight);
        }
    }

    /**
     * Генерує випадкову висоту труби.
     * @return випадкова висота труби у пікселях
     */
    private int generatePipeHeight() {
        final int pipeMinHEIGHT = 50;
        final int pipeMaxRandomHeight = 300;
        return pipeMinHEIGHT + randomGenerator.nextInt(pipeMaxRandomHeight);
    }

    /**
     * Додає труби для початкової сцени.
     * @param pipeHeight отримує висоту труби
     */
    private void addStartingPipes(final int pipeHeight) {
        final int pipeWIDTH = 100;
        final int pipeGAP = 300;
        final int groundHeight = 120;
        final int pipeDistance = 300;

        pipes.add(new Rectangle(
                gameWidth + pipeWIDTH + pipes.size() * pipeDistance,
                gameHeight - pipeHeight - groundHeight,
                pipeWIDTH,
                pipeHeight));

        pipes.add(new Rectangle(
                gameWidth + pipeWIDTH + (pipes.size() - 1) * pipeDistance,
                0,
                pipeWIDTH,
                gameHeight - pipeHeight - pipeGAP));
    }

    /**
     * Додає труби після старту гри.
     * @param pipeHeight отримує висоту труби
     */
    private void addNextPipes(final int pipeHeight) {
        final int pipeWIDTH = 100;
        final int pipeGAP = 300;
        final int groundHeight = 120;
        final int pipeDistanceAfter = 600;

        pipes.add(new Rectangle(
                pipes.getLast().x + pipeDistanceAfter,
                gameHeight - pipeHeight - groundHeight,
                pipeWIDTH,
                pipeHeight));

        pipes.add(new Rectangle(
                pipes.getLast().x,
                0,
                pipeWIDTH,
                gameHeight - pipeHeight - pipeGAP));
    }

    /**
     * Малює трубу.
     *
     * @param graphics об’єкт {@link Graphics} для малювання
     * @param pipe     прямокутник, що представляє трубу
     */
    public void drawPipe(final Graphics graphics, final Rectangle pipe) {

        graphics.setColor(Color.green.darker().darker());
        graphics.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
    }


    /**
            * Виконує стрибок пташки. Якщо гра завершена — перезапускає її.
            */
    public void jump() {
        if (isGameOver) {
            restartGame();
        }

        if (!isGameStarted) {
            isGameStarted = true;
        } else if (!isGameOver) {
            performJump();
        }
    }

    /**
     * Перезапускає гру після завершення.
     */
    private void restartGame() {
        bird = createBird();
        pipes.clear();
        verticalMotion = 0;
        score = 0;

        addInitialPipes();
        isGameOver = false;
    }

    /**
     * Створює нову пташку в центрі екрану.
     * @return прямокутник, що представляє пташку
     */
    private Rectangle createBird() {
        final int birdSize = 20;
        final int birdOffsetX = 10;
        final int birdOffsetY = 10;

        return new Rectangle(
                gameWidth / 2 - birdOffsetX,
                gameHeight / 2 - birdOffsetY,
                birdSize,
                birdSize
        );
    }

    /**
     * Додає початкові труби.
     */
    private void addInitialPipes() {
        final int initialPipesCount = 4;
        for (int i = 0; i < initialPipesCount; i++) {
            addPipe(true);
        }
    }

    /**
     * Виконує стрибок (зміна вертикального руху).
     */
    private void performJump() {
        final int jumpStrength = 10;
        if (verticalMotion > 0) {
            verticalMotion = 0;
        }
        verticalMotion -= jumpStrength;
    }

    /**
     * Основний цикл оновлення гри. Викликається таймером кожні 20 мс.
     *
     * @param event подія таймера
     */
    @Override
    public void actionPerformed(final ActionEvent event) {
        final int pipeSpeed = 10;                  // Швидкість руху труб
        final int gravityAcceleration = 2;         // Прискорення падіння пташки
        // Кожні 2 тики додається прискорення
        final int gravityTickInterval = 2;
        // Максимальна швидкість падіння
        final int maxFallSpeed = 15;
        // Допуск для зони підрахунку очок
        final int scoreZoneOffset = 10;
        final int groundHeight = 120;              // Висота землі

        ticksCount++;

        if (isGameStarted) {
            // Рух труб
            for (Rectangle pipe : pipes) {
                pipe.x -= pipeSpeed;
            }

            // Падіння пташки під дією "гравітації"
            if (ticksCount % gravityTickInterval == 0
                    && verticalMotion < maxFallSpeed) {
                verticalMotion += gravityAcceleration;
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
                if (bird.x + bird.width / 2 > pipe.x
                        + pipe.width / 2 - scoreZoneOffset
                        && bird.x + bird.width / 2 < pipe.x
                        + pipe.width / 2 + scoreZoneOffset) {
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
            if (bird.y > gameHeight - groundHeight || bird.y < 0) {
                isGameOver = true;
            }

            // Корекція положення пташки при падінні на землю
            if (bird.y + verticalMotion >= gameHeight - groundHeight) {
                bird.y = gameHeight - groundHeight - bird.height;
            }
        }
        gameRenderer.repaint();
    }


    /**
     * Малює всю сцену гри: фон, землю, труби, пташку та текстові повідомлення.
     *
     * @param graphics об’єкт {@link Graphics} для малювання
     */
    public void repaint(final Graphics graphics) {
        // Локальні константи для графіки
        final int groundHeight = 120;
        final int grassHeight = 20;
        final int fontSize = 80;
        final int clickTextX = 75;
        final int gameOverTextX = 100;
        final int textYOffset = 50;
        final int scoreXOffset = 25;
        final int scoreY = 100;

        graphics.setColor(Color.cyan);
        graphics.fillRect(0, 0, gameHeight, gameWidth);

        graphics.setColor(Color.orange);
        graphics.fillRect(0, gameHeight - groundHeight,
                gameWidth, groundHeight);

        graphics.setColor(Color.green);
        graphics.fillRect(0,
                gameHeight - groundHeight, gameWidth, grassHeight);

        graphics.setColor(Color.red);
        graphics.fillRect(bird.x, bird.y,
                bird.height, bird.width);

        for (Rectangle column : pipes) {
            drawPipe(graphics, column);
        }

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Arial",
                Font.BOLD, fontSize));

        if (!isGameStarted) {
            graphics.drawString("Click to Begin",
                    clickTextX, gameHeight / 2 - textYOffset);
        }

        if (isGameOver) {
            graphics.drawString("Game Over",
                    gameOverTextX, gameHeight / 2 - textYOffset);
        }

        if (!isGameOver && isGameStarted) {
            graphics.drawString(String.valueOf(score),
                    gameWidth / 2 - scoreXOffset, scoreY);
        }
    }

    /**
     * Обробка кліку миші — виконує стрибок.
     *
     * @param e подія миші
     */
    public void mouseClicked(final MouseEvent e) {

        jump();
    }


    /**
     * Обробка натискання клавіші. Якщо натиснуто пробіл — виконує стрибок.
     *
     * @param e подія клавіатури
     */
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump();
        }
    }

    /**
     * Викликається при введенні символу з клавіатури.
     *
     * @param e подія клавіатури
     */
    public void keyTyped(final KeyEvent e) {
    }

    /**
     * Викликається, коли курсор миші входить у компонент.
     *
     * @param e подія миші
     */
    public void mouseEntered(final MouseEvent e) {
    }

    /**
     * Викликається, коли курсор миші виходить з компонента.
     *
     * @param e подія миші
     */
    public void mouseExited(final MouseEvent e) {
    }

    /**
     * Викликається при натисканні кнопки миші.
     *
     * @param e подія миші
     */
    public void mousePressed(final MouseEvent e) {
    }

    /**
     * Викликається при відпусканні кнопки миші.
     *
     * @param e подія миші
     */
    public void mouseReleased(final MouseEvent e) {
    }

    /**
     * Викликається при відпусканні клавіші на клавіатурі.
     *
     * @param e подія клавіатури
     */
    public void keyReleased(final KeyEvent e) {
    }


    /**
     * Точка входу у програму. Створює екземпляр гри.
     *
     * @param args аргументи командного рядка
     * @throws Exception якщо виникає помилка при запуску
     */
    public static void main(final String[] args) throws Exception {

        gameInstance = new FlappyBirdGame();
    }
}
