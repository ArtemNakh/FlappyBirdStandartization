package ua.edu.znu.flappybirdgame;

/**Клас {@code ScoreManager} відповідає за керування очками у грі Flappy Bird.
 * <p>
 * Він зберігає поточний рахунок, дозволяє його збільшувати,
 * скидати до нуля та отримувати значення для відображення.
 * </p>
 */
public class ScoreManager {
    /** Поточний рахунок гри. */
    private int score;

    /**
     * Конструктор створює новий менеджер очок із початковим значенням 0.
     */
    public ScoreManager() {
        score = 0;
    }

    /**
     * Скидає рахунок до нуля.
     */
    public void reset() {
        score = 0;
    }

    /**
     * Збільшує рахунок на одиницю.
     */
    public void increment() {
        score++;
    }

    /**
     * Повертає поточний рахунок.
     *
     * @return кількість набраних очок
     */
    public int getScore() {
        return score;
    }
}
