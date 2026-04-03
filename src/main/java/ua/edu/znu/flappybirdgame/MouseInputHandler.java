package ua.edu.znu.flappybirdgame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**Клас {@code MouseInputHandler} відповідає
 * за обробку подій миші у грі Flappy Bird.
 */
public class MouseInputHandler extends MouseAdapter {
    /** Посилання на основний екземпляр гри Flappy Bird. */
    private final FlappyBirdGame game;

    /**
     * Конструктор створює новий обробник миші.
     *
     * @param game екземпляр {@link FlappyBirdGame},
     *            який буде оновлюватися при кліку миші
     */
    public MouseInputHandler(final FlappyBirdGame game) {
        super();
        this.game = game;
    }

    /**
     * Викликається при кліку миші.
     * Забезпечує стрибок пташки у грі.
     *
     * @param event подія миші
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        game.jump();
    }
}
