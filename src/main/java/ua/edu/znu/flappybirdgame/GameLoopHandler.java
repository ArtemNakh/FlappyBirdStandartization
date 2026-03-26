package ua.edu.znu.flappybirdgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoopHandler implements ActionListener {
    private final FlappyBirdGame game;

    public GameLoopHandler(FlappyBirdGame game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.updateGame();
    }
}
