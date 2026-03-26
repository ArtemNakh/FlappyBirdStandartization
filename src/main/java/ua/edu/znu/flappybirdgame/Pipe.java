package ua.edu.znu.flappybirdgame;

import java.awt.*;

public class Pipe {
    private final Rectangle shape;

    public Pipe(int x, int y, int width, int height) {
        shape = new Rectangle(x, y, width, height);
    }

    public void move(int speed) { shape.x -= speed; }
    public Rectangle getShape() { return shape; }

    public void draw(Graphics g) {
        g.setColor(Color.green.darker().darker());
        g.fillRect(shape.x, shape.y, shape.width, shape.height);
    }
}
