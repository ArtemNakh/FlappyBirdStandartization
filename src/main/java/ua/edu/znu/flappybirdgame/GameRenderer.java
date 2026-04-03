package ua.edu.znu.flappybirdgame;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
/**Клас {@code GameRenderer}
 * відповідає за
 * відображення графічного стану гри Flappy Bird.
 */
public class GameRenderer extends JPanel implements Serializable {
    /** Ідентифікатор версії класу для серіалізації. */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Поточний стан гри, який відображається на панелі.
     * Позначений як {@code transient}, щоб уникнути серіалізації.
     */
    private final transient  GameState state;

    /**
     * Конструктор створює новий об'єкт {@code GameRenderer} з переданим станом гри.
     *
     * @param newState екземпляр {@link GameState}, який містить логіку та дані гри
     */
    public GameRenderer(final GameState newState) {
        super();
        this.state = newState;
    }

    /**
     * Перевизначений метод для відображення графіки гри.
     * Викликає метод {@link GameState#draw(Graphics)} для малювання елементів гри.
     *
     * @param graphics графічний контекст, який використовується для малювання
     */
    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        state.draw(graphics);
    }
}
