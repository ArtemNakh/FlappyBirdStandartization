package ua.edu.znu.flappybirdgame;

import javax.swing.*;
import java.awt.*;

public class GameRenderer extends JPanel {
    private final GameState state;

    public GameRenderer(GameState state) {
        this.state = state;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        state.draw(g);
    }
}
