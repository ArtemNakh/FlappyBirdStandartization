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


//public class MouseInputHandler implements MouseListener {
//    private final FlappyBirdGame game;
//
//    public MouseInputHandler(FlappyBirdGame game) {
//        this.game = game;
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) { game.jump(); }
//    @Override public void mouseEntered(MouseEvent e) {}
//    @Override public void mouseExited(MouseEvent e) {}
//    @Override public void mousePressed(MouseEvent e) {}
//    @Override public void mouseReleased(MouseEvent e) {}
//}
