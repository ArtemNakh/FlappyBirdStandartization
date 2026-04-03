package ua.edu.znu.flappybirdgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**Клас {@code GameLoopHandler} реалізує інтерфейс {@link ActionListener}
 * і відповідає за обробку ігрового циклу у грі Flappy Bird.
 */
public class GameLoopHandler implements ActionListener {

    /** Посилання на основний екземпляр гри Flappy Bird. */
    private final FlappyBirdGame game;

    /**
     * Конструктор створює новий об'єкт {@code GameLoopHandler}.
     *
     * @param newGame екземпляр {@link FlappyBirdGame}, який потрібно оновлювати
     */
    public GameLoopHandler(final FlappyBirdGame newGame) {
        this.game = newGame;
    }

    /**
     * Викликається при кожному спрацьовуванні таймера.
     * Забезпечує оновлення стану гри та перемальовування вікна.
     *
     * @param event подія таймера, що запускає оновлення
     */
    @Override
    public void actionPerformed(final ActionEvent event) {
        game.updateGame();
    }
}
