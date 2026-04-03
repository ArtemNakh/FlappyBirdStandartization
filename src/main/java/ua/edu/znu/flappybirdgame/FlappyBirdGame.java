//
//
//package ua.edu.znu.flappybirdgame;
//
//import java.awt.*;
//import javax.swing.*;
//
//public class FlappyBirdGame {
//
//    public static FlappyBirdGame gameInstance;
//    private final GameRenderer gameRenderer;
//    private final GameState gameState;
//    private final Timer gameTimer;
//
//    static final int gameWidth = 800;
//    static final int gameHeight = 800;
//
//    public FlappyBirdGame() {
//        JFrame gameWindow = new JFrame();
//        final int timerDelay = 20;
//        gameRenderer = new GameRenderer();
//        gameState = new GameState(gameWidth, gameHeight);
//
//        gameTimer = new Timer(timerDelay, new GameLoopHandler(this));
//
//        gameWindow.add(gameRenderer);
//        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        gameWindow.setSize(gameWidth, gameHeight);
//
//        gameWindow.addMouseListener(new MouseInputHandler(this));
//        gameWindow.addKeyListener(new KeyboardInputHandler(this));
//
//        gameWindow.setResizable(false);
//        gameWindow.setTitle("Flappy Bird");
//        gameWindow.setVisible(true);
//
//        gameTimer.start();
//    }
//
//    public static FlappyBirdGame getGameInstance() {
//        return gameInstance;
//    }
//
//    public void updateGame() {
//        gameState.update();
//        gameRenderer.repaint();
//    }
//
//    public void repaint(Graphics g) {
//        gameState.draw(g);
//    }
//
//    public void jump() {
//        gameState.jump();
//    }
//}


package ua.edu.znu.flappybirdgame;

public class FlappyBirdGame {

    private static FlappyBirdGame gameInstance;
    private final GameState gameState;
    private final GameWindow gameWindow;

    static final int gameWidth = 800;
    static final int gameHeight = 800;

    private FlappyBirdGame() {
        gameState = new GameState(gameWidth, gameHeight);
        gameWindow = new GameWindow(this, gameState);
    }

    public static synchronized FlappyBirdGame getInstance() {
        if (gameInstance == null) {
            gameInstance = new FlappyBirdGame();
        }
        return gameInstance;
    }

    public void updateGame() {
        gameState.update();
        gameWindow.repaint();
    }

    public void jump() {
        gameState.jump();
    }
}
