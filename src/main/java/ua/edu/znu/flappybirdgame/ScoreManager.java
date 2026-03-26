package ua.edu.znu.flappybirdgame;

public class ScoreManager {
    private int score;

    public void reset() { score = 0; }
    public void increment() { score++; }
    public int getScore() { return score; }
}
