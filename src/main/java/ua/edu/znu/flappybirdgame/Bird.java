package ua.edu.znu.flappybirdgame;

import java.awt.*;

public class Bird {
    private Rectangle shape;
    private int verticalMotion;

    public Bird(int x, int y, int size) {
        shape = new Rectangle(x, y, size, size);
    }

    public void jump() {
        if (verticalMotion > 0) verticalMotion = 0;
        verticalMotion -= 10;
    }

    public void fall(int gravity, int maxFallSpeed, int tick) {
        if (tick % 2 == 0 && verticalMotion < maxFallSpeed) {
            verticalMotion += gravity;
        }
        shape.y += verticalMotion;
    }

    public Rectangle getShape() {
        return new Rectangle(shape);
    }

    public void reset(int x, int y, int size) {
        shape = new Rectangle(x, y, size, size);
        verticalMotion = 0;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(shape.x, shape.y, shape.width, shape.height);
    }
}
