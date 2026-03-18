package ua.edu.znu.flappybirdgame;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Клас {@code GameRenderer} відповідає за відображення графічних елементів гри Flappy Bird.
 * <p>
 * Він успадковує {@link JPanel} та перевизначає метод {@link #paintComponent(Graphics)},
 * щоб викликати метод рендерингу основного класу {@link FlappyBirdGame}.
 * </p>
 *
 * <h2>Призначення:</h2>
 * <ul>
 *   <li>Малювати фон, землю, труби та пташку.</li>
 *   <li>Відображати текстові повідомлення ("Click to Begin", "Game Over", рахунок).</li>
 *   <li>Забезпечувати оновлення графіки при кожному виклику {@code repaint()}.</li>
 * </ul>
 *
 * <h2>Особливості:</h2>
 * <ul>
 *   <li>Використовує статичний екземпляр {@code FlappyBirdGame.gameInstance} для доступу до логіки гри.</li>
 *
 * </ul>
 *
 */
public class GameRenderer extends JPanel {

    /**
     * Перевизначений метод для малювання графічних елементів гри.
     * <p>
     * Викликає метод {@link FlappyBirdGame#repaint(Graphics)} для відображення
     * поточного стану гри.
     * </p>
     *
     * @param graphics об’єкт {@link Graphics}, який використовується для малювання
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        FlappyBirdGame.gameInstance.repaint(graphics);
    }
}