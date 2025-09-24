import java.awt.*;

public class Paddle {
    private double x, y;
    private final double width, height;
    public Paddle(double x, double y, double w, double h) {
        this.x = x; 
        this.y = y; 
        this.width = w; 
        this.height = h;
    }

    public void PlayerMove(double dx, int panelWidth) {
        x = Math.max(10, Math.min(x + dx, panelWidth - width - 10));
    }

    public void CompMove(double targetX, int panelWidth) {
        double speed = 5;
        if (targetX > x) x += Math.min(speed, targetX - x);
        else x -= Math.min(speed, x - targetX);
        x = Math.max(10, Math.min(x, panelWidth - width - 10));
    }

    public boolean intersects(double cx, double cy, double r) {
        double closestX = Math.max(x, Math.min(cx, x + width));
        double closestY = Math.max(y, Math.min(cy, y + height));
        return dx*dx + dy*dy <= r*r;
    }

}