package ua.edu.znu.flappybirdgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Клас {@code KeyboardInputHandler}
 * відповідає за обробку подій клавіатури у грі Flappy Bird.
 */
public class KeyboardInputHandler extends KeyAdapter {
    /**
     * Посилання на основний екземпляр гри Flappy Bird.
     */
    private final FlappyBirdGame game;

    /**
     * Конструктор створює новий обробник клавіатури.
     *
     * @param flappyGame екземпляр {@link FlappyBirdGame},
     *                   який буде оновлюватися при натисканні клавіш
     */
    public KeyboardInputHandler(final FlappyBirdGame flappyGame) {
        super();
        this.game = flappyGame;
    }


    /**
     * Викликається при натисканні клавіші.
     * Якщо натиснуто пробіл, виконується стрибок пташки.
     *
     * @param event подія клавіатури
     */
    @Override
    public void keyPressed(final KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            game.jump();
        }
    }
}
