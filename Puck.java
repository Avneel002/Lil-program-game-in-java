import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Puck {
    private double x, y, vx, vy;
    private final double radius;
    private final Random rand = new Random();

    public Puck(double x, double y, double radius) {
        this.x = x; this.y = y; this.radius = radius;
        reset(x, y);
    }

    public void reset(double startX, double startY) {
        this.x = startX; this.y = startY;
        vx = rand.nextDouble() * 6 - 3;
        vy = rand.nextDouble() * 6 - 3;
        if (Math.abs(vx) < 1) vx = 1.5;
        if (Math.abs(vy) < 1) vy = 1.5;
    }

    public void update(int width, int height, Paddle player, Paddle cpu, GameState state) {
        x += vx; y += vy;

        
        if (x - radius < 0 || x + radius > width) vx = -vx;

        
        int goalW = width / 2;
        int goalL = (width - goalW) / 2;
        if (y - radius <= 0 && x >= goalL && x <= goalL + goalW) {
            state.cpuScored();
            reset(width/2.0, height/2.0);
        }
        if (y + radius >= height && x >= goalL && x <= goalL + goalW) {
            state.cpuScored();
            reset(width/2.0, height/2.0);
        }

        
        if (player.intersects(x, y, radius) && vy > 0) vy = -Math.abs(vy);
        if (cpu.intersects(x, y, radius) && vy < 0) vy = Math.abs(vy);
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        Ellipse2D.Double shape = new Ellipse2D.Double(x - radius, y - radius, radius*2, radius*2);
        g.fill(shape);
        g.setColor(Color.WHITE);
        g.draw(shape);
    }

    public double getX() { return x; }
}
