package ua.edu.znu.flappybirdgame;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private final JFrame frame;
    private final GameRenderer renderer;
    private final Timer gameTimer;

    public GameWindow(FlappyBirdGame game, GameState state) {
        frame = new JFrame("Flappy Bird");
        renderer = new GameRenderer(state);

        // таймер з окремим GameLoopHandler
        gameTimer = new Timer(20, new GameLoopHandler(game));

        frame.add(renderer);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setSize(FlappyBirdGame.gameWidth, FlappyBirdGame.gameHeight);
        frame.setSize(800, 800);

        // слухачі
        frame.addMouseListener(new MouseInputHandler(game));
        frame.addKeyListener(new KeyboardInputHandler(game));

        frame.setResizable(false);
        frame.setVisible(true);

        gameTimer.start();
    }

    public void repaint() {
        renderer.repaint();
    }
}
