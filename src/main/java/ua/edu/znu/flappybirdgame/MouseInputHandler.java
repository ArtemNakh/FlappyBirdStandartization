package ua.edu.znu.flappybirdgame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInputHandler extends MouseAdapter {
    private final FlappyBirdGame game;

    public MouseInputHandler(FlappyBirdGame game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        game.jump();
    }
}
