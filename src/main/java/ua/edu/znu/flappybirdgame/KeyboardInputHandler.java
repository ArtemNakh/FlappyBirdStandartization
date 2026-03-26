//package ua.edu.znu.flappybirdgame;
//
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//public class KeyboardInputHandler implements KeyListener {
//    private final FlappyBirdGame game;
//
//    public KeyboardInputHandler(FlappyBirdGame game) {
//        this.game = game;
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_SPACE) game.jump();
//    }
//    @Override public void keyTyped(KeyEvent e) {}
//    @Override public void keyReleased(KeyEvent e) {}
//}

package ua.edu.znu.flappybirdgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInputHandler extends KeyAdapter {
    private final FlappyBirdGame game;

    public KeyboardInputHandler(FlappyBirdGame game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            game.jump();
        }
    }
}
